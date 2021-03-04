package com.ywp.controller;


import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywp.dao.VisitorDao;
import com.ywp.data.ResultData;
import com.ywp.data.TableData;
import com.ywp.entity.Article;
import com.ywp.entity.Visitor;
import com.ywp.entity.Visitor_park;
import com.ywp.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 * 游客控制层
 */
@Controller
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;



    /**
     * 游客注册
     * @param visitor_name
     * @param visitor_password
     * @return
     */
    @RequestMapping("/visitor_registered")
       public String visitor_registered(String visitor_name, String visitor_password, String visitor_phonenumber, String visitor_carnumber) {

        boolean flag = visitorService.visitorRegistered(visitor_name, visitor_password, visitor_phonenumber, visitor_carnumber);
        if (flag) {
            return "login";
        }
        return "visitor/visitor_registered";
    }

    /**
     * 游客信息更新
     * @param visitor_name
     * @param visitor_password
     * @param visitor_phonenumber
     * @param visitor_carnumber
     * @return
     */
    @RequestMapping("/visitor_info_update")
    public String visitor_info_update(String visitor_name, String visitor_password, String visitor_phonenumber, String visitor_carnumber, HttpSession session, HttpServletRequest request){
        if(visitor_name.equals("") || visitor_password.equals("") || visitor_phonenumber.equals("") || visitor_carnumber.equals("")){
            session.setAttribute("update_info_msg","更改信息失败，请重新更改");
            return "visitor/visitor_info";
        }
        int visitor_id = (int)request.getSession().getAttribute("id");
        Visitor visitor = new Visitor();
        visitor.setVisitor_id(visitor_id);
        visitor.setVisitor_name(visitor_name);
        visitor.setVisitor_password(visitor_password);
        visitor.setVisitor_phonenumber(visitor_phonenumber);
        visitor.setVisitor_carnumber(visitor_carnumber);
        boolean flag = visitorService.visitor_info_update(visitor);

        if(flag == true){
            session.setAttribute("visitor",visitor);
        }
        session.setAttribute("update_info_msg","更改信息成功");
        return "visitor/visitor_info";
    }

    /**
     * 游客停车详情
     * @param page
     * @param limit
     * @param
     */
    @ResponseBody
    @RequestMapping("/visitor_park_list")
    public TableData visitor_park_list(@RequestParam("page")Integer page,  @RequestParam("limit")Integer limit,@RequestParam(value = "visitor_carnumber",required = false,defaultValue = "")String visitor_carnumber,@RequestParam(value = "park_location",required = false,defaultValue = "")String park_location,HttpServletRequest request) throws Exception{
        int visitor_id = (int)request.getSession().getAttribute("id");
        PageHelper.startPage(page,limit);
        List<Visitor_park> visitorParkList = visitorService.getVisitorPark(visitor_id,visitor_carnumber,park_location);
        PageInfo<Visitor_park> pageInfo = new PageInfo<>(visitorParkList);

        TableData tableData = new TableData();
        tableData.setCode(0);
        tableData.setMsg("成功");
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
    }


    /**
     * 游客停车账单详情
     * @param page
     * @param limit
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/visitor_park_cost_list")
    public TableData visitor_park_cost_list(@RequestParam(value = "page",required = false,defaultValue = "1") Integer page, @RequestParam(value = "limit",required = false,defaultValue = "10") Integer limit,@RequestParam(value = "park_id",required = false,defaultValue = "")String park_id,@RequestParam(value = "visitor_carnumber",required = false,defaultValue = "")String visitor_carnumber, HttpServletRequest request) throws Exception{
        int visitor_id = (int)request.getSession().getAttribute("id");
        PageHelper.startPage(page,limit);
        List<Visitor_park> visitorParkCostList = visitorService.getVisitorParkCost(visitor_id,park_id,visitor_carnumber);
        PageInfo<Visitor_park> pageInfo = new PageInfo<>(visitorParkCostList);
        TableData tableData = new TableData();
        tableData.setCode(0);
        tableData.setMsg("成功");
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;

    }




    /**
     * 游客缴纳停车费
     * @return
     */
    @ResponseBody
    @RequestMapping("/delivery_park")
    public ResultData delivery_park(@RequestParam("park_id") int park_id){
        visitorService.delivery_park(park_id);
        ResultData resultData = new ResultData();
        resultData.setMessage("成功");
        resultData.setStatus(true);
        return resultData;
    }


    /**
     * 游客浏览帖子
     */
    @RequestMapping("/visitor_article_look")
    public String visitor_article_look(HttpSession session){
        List<Article> articles = visitorService.findArticleAll();
        session.setAttribute("visitor_articles",articles);
        //发布成功后，不再提示，发布信息
        session.removeAttribute("publish_msg");
        return "visitor/visitor_platform";
    }

    /**
     * 游客发表帖子
     * @param topic
     * @param content
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/visitor_article_publish")
    public String visitor_article_publish(String topic, String content,HttpServletRequest request,HttpSession session){
       int people_id = (int)request.getSession().getAttribute("id");
       String poeple_name = (String) request.getSession().getAttribute("name");
        boolean flag = visitorService.visitor_article_publish(people_id, poeple_name, topic, content);
        if(flag){
            //发布成功
            session.setAttribute("publish_msg","发布成功，请转至小区贴吧查看具体详情");
            return "visitor/visitor_publish";
        }
        //发布失败，返回发布页面
        session.setAttribute("publish_msg","发布失败，请重新发布");
        return "visitor/visitor_publish";
    }


    /**
     * 游客已发帖子详情
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping("/visitor_article_list")
    public TableData visitor_article_list(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,@RequestParam(value = "topic",required = false,defaultValue = "") String topic,@RequestParam(value = "content",required = false,defaultValue = "")String content,HttpServletRequest request){
       int people_id = (int) request.getSession().getAttribute("id");
       String people_name = (String) request.getSession().getAttribute("name");

       PageHelper.startPage(page,limit);
       List<Article> articleList = visitorService.findArticleByIDAndName(people_id, people_name,topic,content);
       PageInfo<Article> pageInfo = new PageInfo<>(articleList);

       TableData tableData = new TableData();
       tableData.setCode(0);
       tableData.setMsg("成功");
       tableData.setCount(pageInfo.getTotal());
       tableData.setData(pageInfo.getList());
       return tableData;
    }


    /**
     * 删除帖子
     * @param article_ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/visitor_delete_article")
    public ResultData visitor_delete_article(@RequestParam(value="article_id",required = false) int[] article_ids){
        visitorService.visitor_delete_article(article_ids);
        ResultData resultData = new ResultData();
        resultData.setMessage("成功");
        resultData.setStatus(true);
        return resultData;
    }


    /**
     * 去游客个人信息页面
     * @return
     */
    @RequestMapping("/toVisitorInfo")
    public String toVisitorInfo(){
        return "visitor/visitor_info";
    }

    /**
     * 去停车详情页面
     * @return
     */
    @RequestMapping("/toVisitorParking")
       public String toVisitorParking(){
           return "visitor/visitor_parking";
       }

    /**
     * 去账单详情页面
     * @return
     */
    @RequestMapping("/toVisitorCosting")
       public String toVisitorCosting(){
           return "visitor/visitor_cost_list";
       }

    /**
     * 去论坛页面
     * @return
     */
    @RequestMapping("/toVisitorPlatform")
       public String toVisitorPlatform(){
        return "visitor/visitor_platform";
       }

    /**
     * 去游客发表帖子页面
     * @return
     */
    @RequestMapping("/toVisitorPublish")
       public String toVisitorPublish(){
        return "visitor/visitor_publish";
       }

    /**
     * 去游客帖子管理页面
      * @return
     */
    @RequestMapping("toVisitorArticleManage")
       public String toVisitorArticleManage(){
        return "visitor/visitor_article_manage";
       }




}
