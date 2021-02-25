package com.ywp.controller;

import com.ywp.entity.Admin;
import com.ywp.entity.User;
import com.ywp.entity.Visitor;
import com.ywp.services.AdminService;
import com.ywp.services.UserService;
import com.ywp.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 通用控制层
 */
@Controller
@RequestMapping("/main")
public class MainController  {

    @Autowired
    private UserService userService;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private AdminService adminService;

    /**
     * 去系统界面
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "redirect:/index.jsp";
    }

    /**
     * 去登录页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }



    /**
     * 去业主注册页面
     * @return
     */
    @RequestMapping("/toUserRegistered")
        public String toUserRegistered(){
            return "user/user_registered";
        }

    /**
     * 去游客注册界面
      * @return
     */
    @RequestMapping("/toVisitorRegistered")
    public String toVisitorRegistered(){
        return "visitor/visitor_registered";
    }


    /**
    * 去欢迎界面
    * @return
    */
   @RequestMapping("/toWelcome")
   public String toWelcome(){
       return "welcome";
   }


    /**
     * 登录
     * @param name
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/mainLogin")
    public String mainLogin(String name, String password, HttpServletRequest request, HttpSession session) {
        String role = request.getParameter("role"); //获取已选择登录的角色
        if (!name.equals("") && !password.equals("")) {
            if (role.equals("业主")) {
                List<User> list = userService.userLogin(name, password);
                for (User user : list) {
                    if (user != null) {
                        session.setAttribute("user",user);
                      session.setAttribute("name",user.getUser_name());
                        request.getSession().setAttribute("id",user.getUser_id());
                        return "user/user";
                    } else {
                        return "login";
                    }
                }
            }else if (role.equals("游客")){
               List<Visitor> list = visitorService.visitorLogin(name, password);
               for (Visitor visitor : list) {
                   if (visitor != null) {
                       session.setAttribute("visitor",visitor);
                      session.setAttribute("name",visitor.getVisitor_name());
                       request.getSession().setAttribute("id",visitor.getVisitor_id());
                       return "visitor/visitor";
                   } else {
                       return "login";
                   }
               }
            }else if(role.equals("管理员")){
                List<Admin> list = adminService.adminLogin(name,password);
                for(Admin admin : list){
                    if(admin != null){
                        session.setAttribute("admin",admin);
                        session.setAttribute("name",admin.getAdmin_name());
                        request.getSession().setAttribute("id",admin.getAdmin_id());
                        return "admin/admin";
                    }else {
                        return "login";
                    }
                }
            }
        }
        return "login";
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/doLogout")
       public String doLogout(HttpSession session){
           try {
               session.removeAttribute("name");
               session.removeAttribute("id");
           }catch (Exception e){
           }

           return "redirect:/index.jsp";
       }

    /**
     * 切换账号
     * @param session
     * @return
     */
    @RequestMapping("/doChange")
    public String doChange(HttpSession session){
           try {
                 session.removeAttribute("name");
                 session.removeAttribute("id");
             }catch (Exception e){
             }
             return "login";
       }



}
