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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

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
   public TableData user_park_list(@RequestParam("page")Integer page, @RequestParam("limit")Integer limit, HttpServletRequest request) throws Exception{
       int user_id = (int)request.getSession().getAttribute("id");
       PageHelper.startPage(page,limit);
       List<User_park> userParkList = userService.getUserPark(user_id);
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
   public TableData user_park_cost_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit, HttpServletRequest request) throws Exception{
       int user_id = (int)request.getSession().getAttribute("id");
        PageHelper.startPage(page,limit);
        List<User_park> userParkCostList = userService.getUserParkCost(user_id);

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
    public TableData user_property_cost_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit, HttpServletRequest request){
        int user_id = (int)request.getSession().getAttribute("id");
        PageHelper.startPage(page,limit);
        List<Property> userPropertyCostList = userService.getUserPropertyCost(user_id);
        PageInfo<Property> pageInfo = new PageInfo<>(userPropertyCostList);
        TableData tableData = new TableData();
        tableData.setCode(0);
        tableData.setMsg("成功");
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
    }

    /**
     * 业主交物业费用
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
     * 去个人信息页面
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
}
