package com.ywp.services.Impl;
import com.ywp.dao.VisitorDao;
import com.ywp.entity.Article;
import com.ywp.entity.Visitor;
import com.ywp.entity.Visitor_park;
import com.ywp.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 游客业务层实现
 */
@Service("visitorService")
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorDao visitorDao;

    /**
     * 游客登录
     * @param visitor_name
     * @param visitor_password
     * @return
     */
    @Override
    public List<Visitor> visitorLogin(String visitor_name, String visitor_password) {
        return visitorDao.visitorLogin(visitor_name,visitor_password);
    }


    /**
     * 游客注册
     * @param visitor_name
     * @param visitor_password
     * @param visitor_phonenumber
     * @param visitor_carnumber
     * @return
     */
    @Override
    public boolean visitorRegistered(String visitor_name, String visitor_password, String visitor_phonenumber, String visitor_carnumber) {

        if(!visitor_name.equals("") && !visitor_password.equals("") && !visitor_phonenumber.equals("") && !visitor_carnumber.equals("") ){
            //查看游客名称是否已被注册
            List<Visitor> visitors = visitorDao.findVisitorByName(visitor_name);
            for(Visitor visitor : visitors){
                if(visitor != null){
                    //游客名称已注册
                    return false;
                }
            }
            //业主名称没有被注册，可以继续注册
           Integer id = visitorDao.visitorRegistered(visitor_name, visitor_password, visitor_phonenumber, visitor_carnumber);
           if(id != null){
               return true;
           }
        }
        return false;
    }


    /**
     * 游客停车详情
     * @param visitor_id
     * @return
     */
    @Override
    public List<Visitor_park> getVisitorPark(int visitor_id) {
        List<Visitor_park> visitor_park_list = visitorDao.findVisitorParkByID(visitor_id);
        return visitor_park_list;
    }

    /**
     * 游客停车账单详情
     * @param visitor_id
     * @return
     */
    @Override
    public List<Visitor_park> getVisitorParkCost(int visitor_id) {
        List<Visitor_park> visitor_park_cost_list = visitorDao.findVisitorCostByID(visitor_id);
               return visitor_park_cost_list;
    }

    /**
     * 游客信息更新
     * @param visitor
     * @return
     */
    @Override
    public boolean visitor_info_update(Visitor visitor) {
        //查看游客名称是否已占用
        List<Visitor> visitorByName = visitorDao.findVisitorByName(visitor.getVisitor_name());
       for(Visitor visitor1:visitorByName){
           if(visitor1 != null){
               visitorDao.visitor_info_save(visitor);
           }
       }
        visitorDao.visitor_info_update(visitor);
        return true;
    }

    /**
     * 查找所有文章
     * @return
     */
    @Override
    public List<Article> findArticleAll() {
        return visitorDao.findArticleAll();
    }

    /**
     * 游客发布帖子
     * @param people_id
     * @param people_name
     * @param topic
     * @param content
     * @return
     */
    @Override
    public boolean visitor_article_publish(int people_id, String people_name, String topic, String content) {
        if(!people_name.equals("") && !topic.equals("") && !content.equals("")){
            Integer article_id = visitorDao.visitor_article_publish(people_id, people_name, topic, content);
            if (article_id != null){
                return true;
            }
        }
        return false;
    }

    /**
     * 通过游客id和name查找游客已发帖子
     * @param people_id
     * @param people_name
     * @return
     */
    @Override
    public List<Article> findArticleByIDAndName(int people_id, String people_name) {
        return visitorDao.findArticleByIDAndName(people_id,people_name);
    }

    /**
     * t根据帖子id，删除帖子
     * @param article_ids
     */
    @Override
    public void visitor_delete_article(int[] article_ids) {
        visitorDao.visitor_delete_article(article_ids);
    }


}
