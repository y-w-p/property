package com.ywp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywp.data.ResultData;
import com.ywp.data.TableData;
import com.ywp.entity.Admin;
import com.ywp.entity.Article;
import com.ywp.entity.Property;
import com.ywp.entity.Repaired;
import com.ywp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
   public TableData admin_article_list(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
      PageHelper.startPage(page,limit);
      List<Article> articleList = adminService.findArticleAll();
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
     */
  @ResponseBody
  @RequestMapping("/admin_repaired_list")
  public TableData admin_repaired_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit){

      PageHelper.startPage(page,limit);
      List<Repaired> AllRepairedList = adminService.findRepairedAll();
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
        String admin_name = (String) request.getSession().getAttribute("name");
        //输入了数据
       if(!year.equals("") && !month.equals("") && !Float.toString(price).equals("")){
             boolean flag = adminService.admin_property_publish(admin_name,year, month, price);
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
     * 所有物业账单详情
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin_property_cost_list")
    public TableData admin_property_cost_list(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit, @RequestParam(value = "user_name",required = false,defaultValue = "") String user_name,HttpServletRequest request){
        PageHelper.startPage(page,limit);
        System.out.println(user_name);
        List<Property> AllPropertyCostList = adminService.getAllPropertyCost();
        PageInfo<Property> pageInfo = new PageInfo<>(AllPropertyCostList);
        TableData tableData = new TableData();
        tableData.setCode(0);
        tableData.setMsg("成功");
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
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


}
