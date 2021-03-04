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
     * @param visitor_carnumber
     * @param park_location
     * @return
     */
    @Override
    public List<Visitor_park> getVisitorPark(int visitor_id,String visitor_carnumber,String park_location) {
        Visitor_park visitor_park = new Visitor_park();
        visitor_park.setVisitor_id(visitor_id);
        visitor_park.setVisitor_carnumber(visitor_carnumber);
        visitor_park.setPark_location(park_location);
        List<Visitor_park> visitor_park_list = visitorDao.findVisitorParkByID(visitor_park);
        return visitor_park_list;
    }

    /**
     * 游客停车账单详情
     * @param visitor_id
     * @param park_id
     * @param visitor_carnumber
     * @return
     */
    @Override
    public List<Visitor_park> getVisitorParkCost(int visitor_id,String park_id,String visitor_carnumber) {
        //搜索条件不包括停车单号
        if(park_id.equals("")){
            Visitor_park visitor_park1 = new Visitor_park();
            visitor_park1.setVisitor_id(visitor_id);
            visitor_park1.setVisitor_carnumber(visitor_carnumber);
            List<Visitor_park> visitorParkCostList = visitorDao.findVisitorCostByID(visitor_park1);
            //计算时长和金额
            for(Visitor_park visitor_park:visitorParkCostList){
                      if(visitor_park != null){
                        //计算分钟
                        int minute = (int) visitor_park.getPeriod();
                        visitor_park.setPeriod(minute);
                        //计算金额
                        int time1 = minute/60;
                        int time2 = minute%60;
                        //不足一小时
                        if(time1 == 0){
                            visitor_park.setCost(5);
                            //未缴费
                            if(visitor_park.getStatus().equals("0")){
                             //将计算出来的金额插入数据库中
                               visitorDao.updateVisitorParkCost(visitor_park.getPark_id(),5);
                            }

                        }else {
                            if(time2 > 0){
                                //超过1小时，不足2小时，算2小时
                                time1 = time1+1;
                            }
                            //5元/h
                            visitor_park.setCost(time1*5);
                            //未缴费
                            if(visitor_park.getStatus().equals("0")){
                             //将计算出来的金额插入数据库中
                              visitorDao.updateVisitorParkCost(visitor_park.getPark_id(),time1*5);
                            }
                        }
                        if(visitor_park.getStatus().equals("1")){
                            visitor_park.setStatus("已缴费");
                        }else {
                            visitor_park.setStatus("未缴费");
                        }
                      }
                  }
            return visitorParkCostList;
        }
        //搜索条件包括停车单号
        Visitor_park visitor_park1 = new Visitor_park();
        visitor_park1.setVisitor_id(visitor_id);
        visitor_park1.setPark_id(Integer.parseInt(park_id.trim()));//同上的区别，包括停车单号，表示停车单号存在
        visitor_park1.setVisitor_carnumber(visitor_carnumber);
        List<Visitor_park> visitorParkCostList = visitorDao.findVisitorCostByParkID(visitor_park1);
        //计算时长和金额
        for(Visitor_park visitor_park:visitorParkCostList){
                   if(visitor_park != null){
                     //计算分钟
                     int minute = (int) visitor_park.getPeriod();
                     visitor_park.setPeriod(minute);
                     //计算金额
                     int time1 = minute/60;
                     int time2 = minute%60;
                     //不足一小时
                     if(time1 == 0){
                         visitor_park.setCost(5);
                         //未缴费
                         if(visitor_park.getStatus().equals("0")){
                          //将计算出来的金额插入数据库中
                            visitorDao.updateVisitorParkCost(visitor_park.getPark_id(),5);
                         }

                     }else {
                         if(time2 > 0){
                             //超过1小时，不足2小时，算2小时
                             time1 = time1+1;
                         }
                         //5元/h
                         visitor_park.setCost(time1*5);
                         //未缴费
                         if(visitor_park.getStatus().equals("0")){
                          //将计算出来的金额插入数据库中
                           visitorDao.updateVisitorParkCost(visitor_park.getPark_id(),time1*5);
                         }
                     }
                     if(visitor_park.getStatus().equals("1")){
                         visitor_park.setStatus("已缴费");
                     }else {
                         visitor_park.setStatus("未缴费");
                     }
                   }
               }
        return visitorParkCostList;
    }


    /**
     * 游客缴纳停车费
     * @param park_id
     */
    @Override
    public void delivery_park(int park_id) {
        visitorDao.delivery_park(park_id);
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
     * @param topic
     * @param content
     * @return
     */
    @Override
    public List<Article> findArticleByIDAndName(int people_id, String people_name,String topic,String content) {
        Article article = new Article();
        article.setPeople_id(people_id);
        article.setPeople_name(people_name);
        article.setTopic(topic);
        article.setContent(content);
        return visitorDao.findArticleByIDAndName(article);
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
