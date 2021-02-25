package com.ywp.dao;

import com.ywp.entity.Article;
import com.ywp.entity.Visitor;
import com.ywp.entity.Visitor_park;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 游客持久层接口
 */
@Repository
public interface VisitorDao {

    /**
     * 游客登录
     * @param visitor_name
     * @param visitor_password
     * @return
     */
    public List<Visitor> visitorLogin(@Param("visitor_name")String visitor_name, @Param("visitor_password")String visitor_password);


    /**
     * 游客注册
     * @param visitor_name
     * @param visitor_password
     * @param visitor_phonenumber
     * @param visitor_carnumber
     */
    public Integer visitorRegistered(@Param("visitor_name") String visitor_name,@Param("visitor_password")String visitor_password,@Param("visitor_phonenumber")String visitor_phonenumber,@Param("visitor_carnumber")String visitor_carnumber);


    /**
     * 通过游客名称查找游客
     * @param visitor_name
     * @return
     */
    public List<Visitor> findVisitorByName(@Param("visitor_name")String visitor_name);


    /**
     * 通过游客ID查找游客停车记录
     * @param
     * @return
     */
    public List<Visitor_park> findVisitorParkByID(@Param("visitor_id")int visitor_id);

    /**
     *  通过游客ID查找游客停车账单
     * @param visitor_id
     * @return
     */
    public List<Visitor_park> findVisitorCostByID(@Param("visitor_id")int visitor_id);

    /**
     * 游客个人信息更新（包括用户名）
     * @param visitor
     * @return
     */
    public void visitor_info_update(Visitor visitor);

    /**
     * 游客个人信息更新（不包括用户名）
     * @param visitor
     */
    public  void  visitor_info_save(Visitor visitor);

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
    public Integer visitor_article_publish(@Param("people_id") int people_id,@Param("people_name") String people_name,@Param("topic") String topic,@Param("content") String content);

    /**
     * 通过游客id和name查找游客已发帖子
     * @param people_id
     * @param people_name
     * @return
     */
    public List<Article> findArticleByIDAndName(@Param("people_id") int people_id,@Param("people_name") String people_name);

    /**
     * 根据帖子Id，删除帖子
     * @param article_ids
     */
    void visitor_delete_article(int[] article_ids);
}
