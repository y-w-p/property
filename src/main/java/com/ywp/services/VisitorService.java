package com.ywp.services;

import com.github.pagehelper.PageInfo;
import com.ywp.entity.Article;
import com.ywp.entity.Visitor;
import com.ywp.entity.Visitor_park;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 游客业务层接口
 */
public interface VisitorService {


    /**
     * 游客登录
     * @param visitor_name
     * @param visitor_password
     * @return
     */
    public List<Visitor> visitorLogin(String visitor_name, String visitor_password);


    /**
     * 游客注册
     * @param visitor_name
     * @param visitor_password
     * @param visitor_phonenumber
     * @param visitor_carnumber
     */
    public boolean visitorRegistered(String visitor_name,String visitor_password, String visitor_phonenumber,String visitor_carnumber);


    /**
     * 游客停车详情
     * @param visitor_id
     * @return
     */
    public List<Visitor_park> getVisitorPark(int visitor_id);



    /**
     * 游客停车账单详情
     * @param visitor_id
     * @return
     */
    public List<Visitor_park> getVisitorParkCost(int visitor_id);

    /**
     * 游客信息更新
     * @param visitor
     * @return
     */
    public boolean visitor_info_update(Visitor visitor);

    /**
     * 查找所有文章
     * @return
     */
    public List<Article> findArticleAll();

    /**
     * 游客发布帖子
     * @param people_id
     * @param people_name
     * @param topic
     * @param content
     * @return
     */
    public boolean visitor_article_publish(int people_id,String people_name,String topic,String content);


    /**
     * 通过游客id和name查找游客已发帖子
     * @param people_id
     * @param people_name
     * @return
     */
    public List<Article> findArticleByIDAndName(int people_id, String people_name);

    /**
     * 根据帖子id，删帖子
     * @param article_ids
     */
    void visitor_delete_article(int[] article_ids);
}
