package com.ywp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.bind.v2.runtime.output.Encoded;
import com.ywp.data.ResultData;
import com.ywp.data.TableData;
import com.ywp.entity.*;
import com.ywp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 管理员控制层
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    /**
     * 管理员个人信息更新
     * @return
     */
    @RequestMapping("admin_info_update")
    public String admin_info_update(String admin_name, String admin_password, HttpSession session, HttpServletRequest request){
        if(admin_name.equals("") || admin_password.equals("")){
             session.setAttribute("update_info_msg","更改信息失败，请重新更改");
             return "admin/admin_info";
        }
        int admin_id = (int) request.getSession().getAttribute("id");
        Admin admin = new Admin();
        admin.setAdmin_id(admin_id);
        admin.setAdmin_name(admin_name);
        admin.setAdmin_password(admin_password);

        boolean flag = adminService.admin_info_update(admin);

         if(flag == true){
             session.setAttribute("admin",admin);
         }
         session.setAttribute("update_info_msg","更改信息成功");
         return "admin/admin_info";
    }


    /**
    * 所有已发帖子详情
    * @param page
    * @param limit
    * @return
    */
   @ResponseBody
   @RequestMapping("/admin_article_list")
   public TableData admin_article_list(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,@RequestParam(value = "topic",required = false,defaultValue = "") String topic,@RequestParam(value = "content",required = false,defaultValue = "")String content){
      PageHelper.startPage(page,limit);
      List<Article> articleList = adminService.findArticleAll(topic,content);
      PageInfo<Article> pageInfo = new PageInfo<>(articleList);

      TableData tableData = new TableData();
      tableData.setCode(0);
      tableData.setMsg("成功");
      tableData.setCount(pageInfo.getTotal());
      tableData.setData(pageInfo.getList());
      return tableData;
   }


    /**
    * 根据ID删除帖子
    * @param article_ids
    * @return
    */
   @ResponseBody
   @RequestMapping("/admin_delete_article")
   public ResultData admin_delete_article(@RequestParam(value="article_id",required = false) int[] article_ids){
       adminService.admin_delete_article(article_ids);
       ResultData resultData = new ResultData();
       resultData.setMessage("成功");
       resultData.setStatus(true);
       return resultData;
   }


    /**
     * 查找所有维修上报记录
     * @param page
     * @param limit
     * @param topic
     * @param content
     * @param location
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin_repaired_list")
    public TableData admin_repaired_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "topic",required = false,defaultValue = "") String topic,@RequestParam(value = "content",required = false,defaultValue = "")String content,@RequestParam(value = "location",required = false,defaultValue = "")String location){

      PageHelper.startPage(page,limit);
      List<Repaired> AllRepairedList = adminService.findRepairedAll(topic,content,location);
      PageInfo<Repaired> pageInfo = new PageInfo<>(AllRepairedList);
      TableData tableData = new TableData();
      tableData.setCode(0);
      tableData.setMsg("成功");
      tableData.setCount(pageInfo.getTotal());
      tableData.setData(pageInfo.getList());
      return tableData;
    }



    /**
       * 根据维修ID删除上报维修
       * @param repaired_ids
       * @return
       */
      @ResponseBody
      @RequestMapping("/admin_delete_repaired")
      public ResultData admin_delete_repaired(@RequestParam(value="repaired_id",required = false) int[] repaired_ids){
          adminService.admin_delete_repaired(repaired_ids);
          ResultData resultData = new ResultData();
          resultData.setMessage("成功");
          resultData.setStatus(true);
          return resultData;
      }


    /**
     * 根据ID不通过报修审核
     * @param repaired_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin_repaired_check_no")
    public ResultData admin_repaired_check_no(@RequestParam("repaired_id") int repaired_id){
        adminService.admin_repaired_check_no(repaired_id);
        ResultData resultData = new ResultData();
        resultData.setMessage("成功");
        resultData.setStatus(true);
        return resultData;
    }

    /**
     * 根据ID通过报修审核
     * @param repaired_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin_repaired_checked")
    public ResultData admin_repaired_checked(@RequestParam("repaired_id") int repaired_id){
        adminService.admin_repaired_checked(repaired_id);
        ResultData resultData = new ResultData();
        resultData.setMessage("成功");
        resultData.setStatus(true);
        return resultData;
    }



    /**
     * 根据ID报修已维修
     * @param repaired_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin_repaired_repaired")
    public ResultData admin_repaired_repaired(@RequestParam("repaired_id") int repaired_id){
        adminService.admin_repaired_repaired(repaired_id);
        ResultData resultData = new ResultData();
        resultData.setMessage("成功");
        resultData.setStatus(true);
        return resultData;
    }


    /**
     * 管理员发布物业费用
     * @param year
     * @param month
     * @param price
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/admin_property_publish")
    public String admin_property_publish(String year, String month,float price,HttpServletRequest request,HttpSession session){
         int admin_id = (int) request.getSession().getAttribute("id");
        //输入了数据
       if(!year.equals("") && !month.equals("") && !Float.toString(price).equals("")){
             boolean flag = adminService.admin_property_publish(admin_id,year, month, price);
             if(flag){
                 //发布成功
                 session.setAttribute("property_publish_msg","发布成功，请转至物业账单详情页面查看具体详情");
                 return "admin/admin_publish_property";
             }
       }

       //发布失败，返回发布页面
       session.setAttribute("property_publish_msg","发布失败，请重新发布");
       return "admin/admin_publish_property";
    }


    /**
     * 所有物业账单详情,注意：method = RequestMethod.GET，前后一致，页面也是get
     * @param page
     * @param limit
     * @param user_name
     * @param year
     * @param month
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin_property_cost_list",method = RequestMethod.GET)
    public TableData admin_property_cost_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "user_name",required = false,defaultValue = "") String user_name,@RequestParam(value = "year",required = false,defaultValue = "")String year,@RequestParam(value = "month",required = false,defaultValue = "")String month,HttpSession session,HttpServletRequest request){
        //移出发布成功提示信息
        session.removeAttribute("property_publish_msg");

        int admin_id = (int) request.getSession().getAttribute("id");
        PageHelper.startPage(page,limit);
        List<Property> AllPropertyCostList = adminService.getAllPropertyCost(admin_id,user_name,year,month);
        PageInfo<Property> pageInfo = new PageInfo<>(AllPropertyCostList);
        TableData tableData = new TableData();
        tableData.setCode(0);
        tableData.setMsg("成功");
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
    }


    /**
     * 管理员业主管理
     * @param page
     * @param limit
     * @param user_name
     * @param user_idcard
     * @param user_carnumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin_user_manage",method = RequestMethod.GET)
    public TableData admin_user_manage(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "user_name",required = false,defaultValue = "") String user_name,@RequestParam(value = "user_idcard",required = false,defaultValue = "") String user_idcard,@RequestParam(value = "user_carnumber",required = false,defaultValue = "")String user_carnumber){
       PageHelper.startPage(page,limit);
       List<User> allUserByName = adminService.getAllUserByName(user_name,user_idcard,user_carnumber);
       PageInfo<User> pageInfo = new PageInfo<>(allUserByName);
       TableData tableData = new TableData();
       tableData.setCode(0);
       tableData.setMsg("成功");
       tableData.setCount(pageInfo.getTotal());
       tableData.setData(pageInfo.getList());
       return tableData;
   }



    /**
     * 管理员根据ID删除业主
     * @param user_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin_delete_user")
    public ResultData admin_delete_user(@RequestParam("user_id") int user_id){
        adminService.admin_delete_user(user_id);
        ResultData resultData = new ResultData();
        resultData.setMessage("成功");
        resultData.setStatus(true);
        return resultData;
    }


    /**
     * 管理员游客管理
     * @param page
     * @param limit
     * @param visitor_name
     * @param visitor_phonenumber
     * @param visitor_carnumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin_visitor_manage",method = RequestMethod.GET)
    public TableData admin_visitor_manage(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "visitor_name",required = false,defaultValue = "") String visitor_name,@RequestParam(value = "visitor_phonenumber",required = false,defaultValue = "") String visitor_phonenumber,@RequestParam(value = "visitor_carnumber",required = false,defaultValue = "")String visitor_carnumber){
       PageHelper.startPage(page,limit);
       List<Visitor> allVisitor = adminService.getAllVisitor(visitor_name, visitor_phonenumber, visitor_carnumber);
       PageInfo<Visitor> pageInfo = new PageInfo<>(allVisitor);
       TableData tableData = new TableData();
       tableData.setCode(0);
       tableData.setMsg("成功");
       tableData.setCount(pageInfo.getTotal());
       tableData.setData(pageInfo.getList());
       return tableData;
   }



    /**
    * 管理员根据ID删除游客
    * @param visitor_id
    * @return
    */
   @ResponseBody
   @RequestMapping("/admin_delete_visitor")
   public ResultData admin_delete_visitor(@RequestParam("visitor_id") int visitor_id){
       adminService.admin_delete_visitor(visitor_id);
       ResultData resultData = new ResultData();
       resultData.setMessage("成功");
       resultData.setStatus(true);
       return resultData;
   }


    /**
     * 停车登记
     * @param id
     * @param role
     * @param carnumber
     * @param park_location
     * @param session
     * @return
     */
    @RequestMapping("/admin_parking")
    public String admin_parking(@RequestParam("id") String id, @RequestParam("role") String role,@RequestParam("carnumber") String carnumber, @RequestParam("park_location") String park_location,HttpSession session){
        //输入了数据
       if(!id.equals("") && !role.equals("") && !carnumber.equals("") && !park_location.equals("")){
           boolean flag = false;
            if(role.equals("业主")){
                User_park user_park = new User_park();
                user_park.setUser_id(Integer.valueOf(id));
                user_park.setPark_location(park_location);
                user_park.setUser_carnumber(carnumber);
                user_park.setStatus("0");
                flag = adminService.admin_user_park(user_park);
                if(flag){
                    //发布成功
                    session.setAttribute("park_msg","登记成功，请转至业主停车详情页面查看具体详情");
                    return "admin/admin_parking";
                }
            }
            if(role.equals("游客")){
                Visitor_park visitor_park = new Visitor_park();
                visitor_park.setVisitor_id(Integer.valueOf(id));
                visitor_park.setPark_location(park_location);
                visitor_park.setVisitor_carnumber(carnumber);
                visitor_park.setStatus("0");
                flag = adminService.admin_visitor_park(visitor_park);
                if(flag){
                    //发布成功
                    session.setAttribute("park_msg","登记成功，请转至游客停车详情页面查看具体详情");
                    return "admin/admin_parking";
                }
            }

       }

       //发布失败，返回发布页面
       session.setAttribute("park_msg","登记失败，请重新登记");
       return "admin/admin_parking";
    }


    /**
     * 业主停车详情页面
     * @param page
     * @param limit
     * @param park_location
     * @param user_carnumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin_user_park_list",method = RequestMethod.GET)
    public TableData admin_user_park_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "park_location",required = false,defaultValue = "") String park_location,@RequestParam(value = "user_carnumber",required = false,defaultValue = "")String user_carnumber){
       PageHelper.startPage(page,limit);
       List<User_park> userParking = adminService.getUserParking(park_location, user_carnumber);
       PageInfo<User_park> pageInfo = new PageInfo<>(userParking);
       TableData tableData = new TableData();
       tableData.setCode(0);
       tableData.setMsg("成功");
       tableData.setCount(pageInfo.getTotal());
       tableData.setData(pageInfo.getList());
       return tableData;
   }


    /**
     * 业主结束停车
     * @param park_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin_user_stop_park")
    public ResultData admin_user_stop_park(@RequestParam("park_id") int park_id){
        adminService.admin_user_stop_park(park_id);
        ResultData resultData = new ResultData();
        resultData.setMessage("成功");
        resultData.setStatus(true);
        return resultData;
    }



    /**
    * 业主缴纳停车费用
    * @param park_id
    * @return
    */
   @ResponseBody
   @RequestMapping("/admin_user_pay_park")
   public ResultData admin_user_pay_park(@RequestParam("park_id") int park_id){
       boolean flag = adminService.admin_user_pay_park(park_id);
       if(flag){
          ResultData resultData = new ResultData();
          resultData.setMessage("成功");
          resultData.setStatus(true);
          return resultData;
       }
       ResultData resultData = new ResultData();
       resultData.setMessage("失败");
       resultData.setStatus(false);
       return resultData;

   }




    /**
    * 游客停车详情页面
    * @param page
    * @param limit
    * @param park_location
    * @param visitor_carnumber
    * @return
    */
   @ResponseBody
   @RequestMapping(value = "/admin_visitor_park_list",method = RequestMethod.GET)
   public TableData admin_visitor_park_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "park_location",required = false,defaultValue = "") String park_location,@RequestParam(value = "visitor_carnumber",required = false,defaultValue = "")String visitor_carnumber){
      PageHelper.startPage(page,limit);
      List<Visitor_park> visitorParking = adminService.getVisitorParking(park_location, visitor_carnumber);
      PageInfo<Visitor_park> pageInfo = new PageInfo<>(visitorParking);
      TableData tableData = new TableData();
      tableData.setCode(0);
      tableData.setMsg("成功");
      tableData.setCount(pageInfo.getTotal());
      tableData.setData(pageInfo.getList());
      return tableData;
  }




    /**
    * 游客结束停车
    * @param park_id
    * @return
    */
   @ResponseBody
   @RequestMapping("/admin_visitor_stop_park")
   public ResultData admin_visitor_stop_park(@RequestParam("park_id") int park_id){
       adminService.admin_visitor_stop_park(park_id);
       ResultData resultData = new ResultData();
       resultData.setMessage("成功");
       resultData.setStatus(true);
       return resultData;
   }





    /**
    * 游客缴纳停车费用
    * @param park_id
    * @return
    */
   @ResponseBody
   @RequestMapping("/admin_visitor_pay_park")
   public ResultData admin_visitor_pay_park(@RequestParam("park_id") int park_id){
       boolean flag = adminService.admin_visitor_pay_park(park_id);
       if(flag){
          ResultData resultData = new ResultData();
          resultData.setMessage("成功");
          resultData.setStatus(true);
          return resultData;
       }
       ResultData resultData = new ResultData();
       resultData.setMessage("失败");
       resultData.setStatus(false);
       return resultData;

   }


    /**
     * 业主停车账单页面
     * @param page
     * @param limit
     * @param user_name
     * @param park_location
     * @param user_carnumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin_user_park_cost",method = RequestMethod.GET)
    public TableData admin_user_park_cost(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "user_name",required = false,defaultValue = "") String user_name,@RequestParam(value = "park_location",required = false,defaultValue = "") String park_location,@RequestParam(value = "user_carnumber",required = false,defaultValue = "")String user_carnumber){
       PageHelper.startPage(page,limit);
       List<User_park> userParkCost = adminService.getUserParkCost(user_name,park_location, user_carnumber);
       PageInfo<User_park> pageInfo = new PageInfo<>(userParkCost);
       TableData tableData = new TableData();
       tableData.setCode(0);
       tableData.setMsg("成功");
       tableData.setCount(pageInfo.getTotal());
       tableData.setData(pageInfo.getList());
       return tableData;
   }





    /**
    * 管理员删除业主停车记录
    * @param park_id
    * @return
    */
   @ResponseBody
   @RequestMapping("/admin_delete_user_park")
   public ResultData admin_delete_user_park(@RequestParam("park_id") int park_id){

      adminService.admin_delete_user_park(park_id);
      ResultData resultData = new ResultData();
      resultData.setMessage("成功");
      resultData.setStatus(true);
      return resultData;
   }


    /**
     * 游客停车账单页面
     * @param page
     * @param limit
     * @param visitor_name
     * @param park_location
     * @param visitor_carnumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin_visitor_park_cost",method = RequestMethod.GET)
    public TableData admin_visitor_park_cost(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "visitor_name",required = false,defaultValue = "") String visitor_name,@RequestParam(value = "park_location",required = false,defaultValue = "") String park_location,@RequestParam(value = "visitor_carnumber",required = false,defaultValue = "")String visitor_carnumber){
       PageHelper.startPage(page,limit);
       List<Visitor_park> userParkCost = adminService.getVisitorParkCost(visitor_name,park_location, visitor_carnumber);
       PageInfo<Visitor_park> pageInfo = new PageInfo<>(userParkCost);
       TableData tableData = new TableData();
       tableData.setCode(0);
       tableData.setMsg("成功");
       tableData.setCount(pageInfo.getTotal());
       tableData.setData(pageInfo.getList());
       return tableData;
   }




   /**
    * 管理员删除游客停车记录
    * @param park_id
    * @return
    */
   @ResponseBody
   @RequestMapping("/admin_delete_visitor_park")
   public ResultData admin_delete_visitor_park(@RequestParam("park_id") int park_id){

      adminService.admin_delete_visitor_park(park_id);
      ResultData resultData = new ResultData();
      resultData.setMessage("成功");
      resultData.setStatus(true);
      return resultData;
   }


    /**
     * 管理员发布通告
     * @param topic
     * @param content
     * @param request
     * @return
     */
   @RequestMapping("/admin_publish_message")
    public String admin_publish_message(@RequestParam("topic")String topic,@RequestParam("content") String content,HttpServletRequest request,HttpSession session){
       if(!topic.equals("") && !content.equals("")){
           int admin_id = (int) request.getSession().getAttribute("id");
           boolean flag = adminService.admin_publish_message(admin_id,topic,content);
           if(flag){
             //发布成功
              session.setAttribute("publish_message_msg","发布通告成功，具体详情至公告详情页面查看");
             return "admin/admin_publish_message";
           }
       }
       session.setAttribute("publish_message_msg","发布通告失败，请重新发布");
       return "admin/admin_publish_message";
    }


    /**
     * 通告详情
     * @param page
     * @param limit
     * @param user_name
     * @param topic
     * @param content
     * @return
     */
   @ResponseBody
   @RequestMapping(value = "/admin_message_list")
   public TableData admin_message_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,@RequestParam(value = "user_name",required = false,defaultValue = "") String user_name,@RequestParam(value = "topic",required = false,defaultValue = "") String topic,@RequestParam(value = "content",required = false,defaultValue = "")String content){
      PageHelper.startPage(page,limit);
      List<Message> messageList = adminService.getMessageList(user_name,topic,content);
      PageInfo<Message> pageInfo = new PageInfo<>(messageList);
      TableData tableData = new TableData();
      tableData.setCode(0);
      tableData.setMsg("成功");
      tableData.setCount(pageInfo.getTotal());
      tableData.setData(pageInfo.getList());
      return tableData;
  }


    /**
     * 管理员删除通告
     * @param message_ids
     * @return
     */
   @ResponseBody
   @RequestMapping("/admin_delete_message")
   public ResultData admin_delete_message(@RequestParam("message_id") int[] message_ids){
      adminService.admin_delete_message(message_ids);
      ResultData resultData = new ResultData();
      resultData.setMessage("成功");
      resultData.setStatus(true);
      return resultData;
   }





    /**
     * 去管理员个人信息页面
     * @return
     */
    @RequestMapping("/toAdminInfo")
    public String toAdminInfo(){
        return "admin/admin_info";
    }

    /**
     * 去管理帖子页面
     * @return
     */
    @RequestMapping("/toAdminArticleManage")
    public String toAdminArticleManage(){
        return "admin/admin_article_manage";
    }

    /**
     * 去管理维修页面
     * @return
     */
    @RequestMapping("/toAdminRepairedManage")
    public String toAdminRepairedManage(){
        return "admin/admin_repaired_manage";
    }



    /**
    * 去发布物业费用页面
    * @return
    */
   @RequestMapping("/toAdminPublishProperty")
   public String toAdminPublishProperty(){
       return "admin/admin_publish_property";
   }



    /**
    * 去发布物业费用页面
    * @return
    */
   @RequestMapping("/toAdminPropertyCostList")
   public String toAdminPropertyCostList(){
       return "admin/admin_property_cost_list";
   }


    /**
     * 去业主管理页面
     * @return
     */
   @RequestMapping("/toAdminUserManage")
   public String toAdminUserManage(){
       return "admin/admin_user_manage";
   }



    /**
     * 去游客管理页面
     * @return
     */
   @RequestMapping("/toAdminVisitorManage")
   public String toAdminVisitorManage(){
       return "admin/admin_visitor_manage";
   }


    /**
     * 去停车登记页面
     * @return
     */
   @RequestMapping("/toParking")
   public String toParking(){
       return "admin/admin_parking";
   }



    /**
     * 去业主停车详情页面
     * @return
     */
   @RequestMapping("/toAdminUserPark")
   public String toAdminUserPark(){

       return "admin/admin_user_park";
   }


    /**
     * 去游客停车详情页面
     * @return
     */
   @RequestMapping("/toAdminVisitorPark")
   public String toAdminVisitorPark(){

       return "admin/admin_visitor_park";
   }



    /**
    * 业主停车账单详情页面
    * @return
    */
    @RequestMapping("/toAdminUserParkCost")
    public String toAdminUserParkCost(){

        return "admin/admin_user_park_cost";
    }


    /**
    * 游客停车账单详情页面
    * @return
    */
    @RequestMapping("/toAdminVisitorParkCost")
    public String toAdminVisitorParkCost(){

        return "admin/admin_visitor_park_cost";
    }



    /**
    * 发布通告页面
    * @return
    */
    @RequestMapping("/toAdminPublishMessage")
    public String toAdminPublishMessage(){

        return "admin/admin_publish_message";
    }



    /**
    * 通告详情页面
    * @return
    */
    @RequestMapping("/toAdminPublishMessageList")
    public String toAdminPublishMessageList(){

        return "admin/admin_message_manage";
    }


}
