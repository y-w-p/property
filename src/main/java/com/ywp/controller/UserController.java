package com.ywp.controller;


import com.ywp.entity.User;
import com.ywp.eums.UserRegisteredEnum;
import com.ywp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * 用户退出
     * @param session
     * @return
     */
    @RequestMapping("/doLogout")
    public String doLogout(HttpSession session){
        try {
            session.removeAttribute("user");
        }catch (Exception e){
        }
        try {
            session.removeAttribute("warn");

        }catch (Exception e){
        }
        return "";
    }
}
