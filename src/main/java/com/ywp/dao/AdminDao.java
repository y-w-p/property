package com.ywp.dao;

import com.ywp.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 管理员持久层接口
 */
@Repository
public interface AdminDao {

    /**
     * 管理员登录
     * @param admin_name
     * @param admin_password
     * @return
     */
    public List<Admin> adminLogin(@Param("admin_name")String admin_name, @Param("admin_password")String admin_password);

    /**
     * 通过admin名称查找admin
     * @param admin_name
     * @return
     */
    List<Admin> findAdminByName(String admin_name);


    /**
     * 管理员个人信息更改（包括管理员名）
     * @param admin
     */
    void admin_info_update(Admin admin);


    /**
     * 管理员个人信息更改（不包括管理员名）
     * @param admin
     */
    void admin_info_save(Admin admin);


    /**
     * 查找所有已发帖子
     * @return
     */
    List<Article> findArticleAll(Article article);


    /**
     * 根据ID删除帖子
     * @param article_ids
     */
    void admin_delete_article(int[] article_ids);


    /**
     * 查找所有上报维修记录
     * @return
     */
    List<Repaired> findRepairedAll(Repaired repaired);


    /**
     * 根据维修ID删除上报维修
     * @param repaired_ids
     */
    void admin_delete_repaired(int[] repaired_ids);


    /**
     * 根据ID不通过报修审核
     * @param repaired_id
     */
    void admin_repaired_check_no(int repaired_id);


    /**
     * 根据ID通过报修审核
     * @param repaired_id
     */
    void admin_repaired_checked(int repaired_id);


    /**
     * 根据ID报修已维修
     * @param repaired_id
     */
    void admin_repaired_repaired(int repaired_id);

    /**
     * 查询所有已注册用户
     * @return
     */
    List<User> findUserAll();


    /**
     * 管理员发布物业费用
     * @param property
     */
    void admin_property_publish(Property property);


    /**
     * 所有物业账单详情
     * @return
     */
    List<Property> getAllPropertyCost(@Param("user_name") String user_name,@Param("year")String year,@Param("month")String month);


    /**
     * 管理员查看所有用户
     * @param user_name
     * @return
     */
    List<User> getAllUserByName(@Param("user_name")String user_name,@Param("user_idcard")String user_idcard,@Param("user_carnumber")String user_carnumber);


    /**
     * 管理员根据ID删除业主
     * @param user_id
     */
    void admin_delete_user(int user_id);



    /**
     * 管理员查看所有游客
     * @param visitor_name
     * @param visitor_phonenumber
     * @param visitor_carnumber
     * @return
     */
    List<Visitor> getAllVisitor(@Param("visitor_name") String visitor_name, @Param("visitor_phonenumber") String visitor_phonenumber,@Param("visitor_carnumber") String visitor_carnumber);



    /**
     * 管理员根据ID删除游客
     * @param visitor_id
     */
    void admin_delete_visitor(@Param("visitor_id") int visitor_id);



    /**
     * 业主停车登记
     * @param user_park
     * @return
     */
    int admin_user_park(User_park user_park);


    /**
     * 游客停车登记
     * @param visitor_park
     * @return
     */
    int admin_visitor_park(Visitor_park visitor_park);


    /**
     * 正在停车的业主停车记录
     * @param park_location
     * @param user_carnumber
     * @return
     */
    List<User_park> getUserParking(@Param("park_location") String park_location, @Param("user_carnumber") String user_carnumber);


    /**
     * 业主结束停车
     * @param park_id
     */
    void admin_user_stop_park(int park_id);


    /**
     * 通过停车单ID查找业主停车
     * @param park_id
     * @return
     */
    User_park findUserParkByID(@Param("park_id") int park_id);


    /**
     * 业主缴纳停车费用
     * @param user_park
     */
    void admin_user_pay_park(User_park user_park);


    /**
     * 正在停车的游客停车记录
     * @param park_location
     * @param visitor_carnumber
     * @return
     */
    List<Visitor_park> getVisitorParking(@Param("park_location")String park_location, @Param("visitor_carnumber")String visitor_carnumber);


    /**
     * 游客结束停车
     * @param park_id
     */
    void admin_visitor_stop_park(int park_id);


    /**
     * 通过停车单ID查找游客停车
     * @param park_id
     * @return
     */
    Visitor_park findVisitorParkByID(int park_id);


    /**
     * 游客缴纳停车费用
     * @param visitor_park
     */
    void admin_visitor_pay_park(Visitor_park visitor_park);


    /**
     * 业主停车账单页面
     * @param user_name
     * @param park_location
     * @param user_carnumber
     * @return
     */
    List<User_park> getUserParkCost(@Param("user_name") String user_name,@Param("park_location")String park_location, @Param("user_carnumber") String user_carnumber);

    /**
     * 管理员删除业主停车记录
     * @param park_id
     */
    void admin_delete_user_park(@Param("park_id")int park_id);


    /**
     * 游客停车账单页面
     * @param visitor_name
     * @param park_location
     * @param visitor_carnumber
     * @return
     */
    List<Visitor_park> getVisitorParkCost(@Param("visitor_name")String visitor_name, @Param("park_location")String park_location, @Param("visitor_carnumber")String visitor_carnumber);



    /**
     * 管理员删除游客停车记录
     * @param park_id
     */
    void admin_delete_visitor_park(@Param("park_id")int park_id);


    /**
     * 先插入到信息内容表
     * @param message
     * @return
     */
    int admin_publish_message_text(Message message);


    /**
     * 管理员发布通告
     * @param message
     */
    void admin_publish_message(Message message);


    /**
     * 通告详情
     * @param user_name
     * @param topic
     * @param content
     * @return
     */
    List<Message> getMessageList(@Param("user_name") String user_name, @Param("topic")String topic, @Param("content")String content);


    /**
     * 管理员删除通告
     * @param message_ids
     */
    void admin_delete_message(int[] message_ids);
}
