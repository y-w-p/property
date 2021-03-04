package com.ywp.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.ywp.dao.UserDao;
import com.ywp.data.ResultData;
import com.ywp.data.TableData;
import com.ywp.entity.*;
import com.ywp.eums.UserRegisteredEnum;
import com.ywp.services.UserService;
import netscape.javascript.JSObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * 业主控制层
 */

@Controller
@RequestMapping("/user")
public class UserController {



    @Autowired
    private UserService userService;



    /**
     * 用户注册
     * @param user_name
     * @param user_password
     * @return
     */
    @RequestMapping("/user_registered")
    public String user_registered(String user_name, String user_password, String user_idcard,
                                  String user_phonenumber, String user_address,
                                  String user_area, String user_carnumber){
        boolean flag = userService.userRegistered(user_name, user_password, user_idcard, user_phonenumber, user_address, user_area, user_carnumber);
        if(flag){
            return "login";
        }
        return "user/user_registered";
    }


    /**
     * 业主个人信息修改
     * @param user_name
     * @param user_password
     * @param user_idcard
     * @param user_phonenumber
     * @param user_address
     * @param user_area
     * @param user_carnumber
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/user_info_update")
    public String user_info_update(String user_name, String user_password, String user_idcard, String user_phonenumber,String user_address, String user_area,String user_carnumber, HttpSession session, HttpServletRequest request){
           if(user_name.equals("") || user_password.equals("") || user_idcard.equals("") || user_phonenumber.equals("") || user_address.equals("") || user_area.equals("") ||user_carnumber.equals("")){
              session.setAttribute("update_info_msg","更改信息失败，请重新更改");
              return "user/user_info";
           }
           int user_id = (int)request.getSession().getAttribute("id");
           User user =new User();
           user.setUser_id(user_id);
           user.setUser_name(user_name);
           user.setUser_password(user_password);
           user.setUser_idcard(user_idcard);
           user.setUser_phonenumber(user_phonenumber);
           user.setUser_address(user_address);
           user.setUser_area(user_area);
           user.setUser_carnumber(user_carnumber);

           boolean flag = userService.user_info_update(user);

           if(flag == true){
               session.setAttribute("user",user);
           }
           session.setAttribute("update_info_msg","更改信息成功");
           return "user/user_info";
       }


    /**
    * 业主停车详情
    * @param page
    * @param limit
    * @param
    */
   @ResponseBody
   @RequestMapping("/user_park_list")
   public TableData user_park_list(@RequestParam("page")Integer page, @RequestParam("limit")Integer limit,@RequestParam(value = "user_carnumber",required = false,defaultValue = "")String user_carnumber,@RequestParam(value = "park_location",required = false,defaultValue = "")String park_location, HttpServletRequest request) throws Exception{
       int user_id = (int)request.getSession().getAttribute("id");
       PageHelper.startPage(page,limit);
       List<User_park> userParkList = userService.getUserPark(user_id,user_carnumber,park_location);
       PageInfo<User_park> pageInfo = new PageInfo<>(userParkList);

       TableData tableData = new TableData();
       tableData.setCode(0);
       tableData.setMsg("成功");
       tableData.setCount(pageInfo.getTotal());
       tableData.setData(pageInfo.getList());
       return tableData;
   }


    /**
    * 业主停车账单详情
    * @param page
    * @param limit
    * @param
    */
   @ResponseBody
   @RequestMapping("/user_park_cost_list")
   public TableData user_park_cost_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "park_id",required = false,defaultValue = "")String park_id,@RequestParam(value = "user_carnumber",required = false,defaultValue = "")String user_carnumber, HttpServletRequest request) throws Exception{
       int user_id = (int)request.getSession().getAttribute("id");
       PageHelper.startPage(page,limit);

       List<User_park> userParkCostList = userService.getUserParkCost(user_id,park_id,user_carnumber);

        PageInfo<User_park> pageInfo = new PageInfo<>(userParkCostList);
        TableData tableData = new TableData();
        tableData.setCode(0);
        tableData.setMsg("成功");
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
   }


    /**
     * 业主物业账单详情
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/user_property_cost_list")
    public TableData user_property_cost_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "year",required = false,defaultValue = "")String year,@RequestParam(value = "month",required = false,defaultValue = "")String month, HttpServletRequest request){
        int user_id = (int)request.getSession().getAttribute("id");
        PageHelper.startPage(page,limit);
        List<Property> userPropertyCostList = userService.getUserPropertyCost(user_id,year,month);
        PageInfo<Property> pageInfo = new PageInfo<>(userPropertyCostList);
        TableData tableData = new TableData();
        tableData.setCode(0);
        tableData.setMsg("成功");
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
    }

    /**
     * 业主缴纳物业费
     * @return
     */
    @ResponseBody
    @RequestMapping("/delivery_property")
    public ResultData delivery_property(@RequestParam("property_id") int property_id){
        userService.delivery_property(property_id);
        ResultData resultData = new ResultData();
        resultData.setMessage("成功");
        resultData.setStatus(true);
        return resultData;
    }



    /**
     * 业主缴纳停车费
     * @return
     */
    @ResponseBody
    @RequestMapping("/delivery_park")
    public ResultData delivery_park(@RequestParam("park_id") int park_id){
        userService.delivery_park(park_id);
        ResultData resultData = new ResultData();
        resultData.setMessage("成功");
        resultData.setStatus(true);
        return resultData;
    }


    /**
     * 业主上报维修，切记不能使用 @ResponseBody，这个函数（有毒）
     * @param repaired
     * @param pictureFile
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/user_repaired")
    public String user_repaired(Repaired repaired, @RequestParam("file") MultipartFile pictureFile, HttpServletRequest request,HttpSession session) throws IOException {
        int user_id = (int) request.getSession().getAttribute("id");
        //使用UUID给图片重命名，并去掉四个“-”
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        //获取文件的扩展名
        String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
        //设置图片上传路径
        String url = request.getSession().getServletContext().getRealPath("/upload");
        System.out.println(url);
        //以绝对路径保存重名命后的图片
        pictureFile.transferTo(new File(url+"/"+name + "." + ext));


        //把图片存储路径保存到数据库
        repaired.setPicture_path("upload/"+name + "." + ext);
        repaired.setUser_id(user_id);
        repaired.setStatus("0");

        boolean flag = userService.user_repaired(repaired);
        if(flag){
            session.setAttribute("repaired_msg","上报成功，具体详情请转至维修详情查看！");
            return "user/user_repair";
        }

        session.setAttribute("repaired_msg","上报失败，请重新上报");
        return "user/user_repair";
    }



    /**
        * 业主上报维修记录详情
        * @param page
        * @param limit
        * @param request
        * @return
        */
       @ResponseBody
       @RequestMapping("/user_repaired_list")
       public TableData user_repaired_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "topic",required = false,defaultValue = "") String topic,@RequestParam(value = "content",required = false,defaultValue = "")String content, HttpServletRequest request,HttpSession session){
           int user_id = (int)request.getSession().getAttribute("id");
           //上报成功后，不再提示上报成功信息
           session.removeAttribute("repaired_msg");

           PageHelper.startPage(page,limit);
           List<Repaired> userRepairedList = userService.getUserRepairedList(user_id,topic,content);
           PageInfo<Repaired> pageInfo = new PageInfo<>(userRepairedList);
           TableData tableData = new TableData();
           tableData.setCode(0);
           tableData.setMsg("成功");
           tableData.setCount(pageInfo.getTotal());
           tableData.setData(pageInfo.getList());
           return tableData;
       }


    /**
       * 删除上报维修
       * @param repaired_ids
       * @return
       */
      @ResponseBody
      @RequestMapping("/user_delete_repaired")
      public ResultData user_delete_repaired(@RequestParam(value="repaired_id",required = false) int[] repaired_ids){
          userService.user_delete_repaired(repaired_ids);
          ResultData resultData = new ResultData();
          resultData.setMessage("成功");
          resultData.setStatus(true);
          return resultData;
      }




    /**
     * 业主通告详情
     * @param page
     * @param limit
     * @param topic
     * @param content
     * @return
     */
   @ResponseBody
   @RequestMapping(value = "/user_message_list")
   public TableData user_message_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "topic",required = false,defaultValue = "") String topic,@RequestParam(value = "content",required = false,defaultValue = "")String content,HttpServletRequest request){
      int user_id = (int) request.getSession().getAttribute("id");
      PageHelper.startPage(page,limit);
      List<Message> messageList = userService.getUserMessageList(user_id,topic,content);
      PageInfo<Message> pageInfo = new PageInfo<>(messageList);
      TableData tableData = new TableData();
      tableData.setCode(0);
      tableData.setMsg("成功");
      tableData.setCount(pageInfo.getTotal());
      tableData.setData(pageInfo.getList());
      return tableData;
  }




  /**
   * 业主阅读通告
   * @param message_id
   * @return
   */
  @ResponseBody
  @RequestMapping("/user_message_look")
  public ResultData user_message_look(@RequestParam(value="message_id",required = false) int message_id,@RequestParam(value="status",required = false)String status){
      //已阅读，直接返回
      if(status.equals("已阅读")){
          ResultData resultData = new ResultData();
          resultData.setMessage("成功");
          resultData.setStatus(true);
          return resultData;
      }
      //未阅读情况，更改状态
      userService.user_message_look(message_id);
      ResultData resultData = new ResultData();
      resultData.setMessage("成功");
      resultData.setStatus(true);
      return resultData;
  }


    /**
   * 业主删除通告
   * @param message_id
   * @return
   */
  @ResponseBody
  @RequestMapping("/user_delete_message")
  public ResultData user_delete_message(@RequestParam(value="message_id",required = false) int message_id,@RequestParam(value="status",required = false)String status){
      //未阅读，不能删除
      if(status.equals("未阅读")){
          ResultData resultData = new ResultData();
          resultData.setMessage("成功");
          resultData.setStatus(true);
          return resultData;
      }
      //已阅读才可以删除通告
      userService.user_delete_message(message_id);
      ResultData resultData = new ResultData();
      resultData.setMessage("成功");
      resultData.setStatus(true);
      return resultData;
  }











    /**
     * 去业主个人信息页面
     * @return
     */
    @RequestMapping("/toUserInfo")
    public String toUserInfo(){
        return "user/user_info";
    }



    /**
     * 去停车详情页面
     * @return
     */
    @RequestMapping("/toUserParking")
    public String toUserParking(){
           return "user/user_parking";
       }



    /**
     * 去业主停车账单详情页面
     * @return
     */
    @RequestMapping("/toUserParkCost")
    public String toUserParkCost(){
           return "user/user_park_cost_list";
       }

    /**
     * 去业主物业账单详情页面
     * @return
     */
    @RequestMapping("/toUserPropertyCost")
    public String toUserPropertyCost(){
        return "user/user_property_cost_list";
       }



    /**
     * 去业主上报维修详情页面
     * @return
     */
    @RequestMapping("/toUserRepairedList")
    public String toUserRepairedList(){
        return "user/user_repair_list";
       }


    /**
     * 去业主上报维修页面
     * @return
     */
    @RequestMapping("/toUserRepaired")
    public String toUserRepaired(){
        return "user/user_repair";
       }





    /**
     * 去业主消息通知页面
     * @return
     */
    @RequestMapping("/toUserMessageList")
    public String toUserMessageList(){
        return "user/user_message_list";
       }

}
