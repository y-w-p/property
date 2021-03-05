package com.ywp.services;

import com.ywp.entity.*;

import java.util.List;

/**
 * 管理员业务接口
 */
public interface AdminService {

    /**
     * 管理员登录
     * @param admin_name
     * @param admin_password
     * @return
     */
    public List<Admin> adminLogin(String admin_name,String admin_password);


    /**
     * 管理员个人信息更新
     * @param admin
     * @return
     */
    boolean admin_info_update(Admin admin);


    /**
     * 查找所有已发帖子
     * @return
     */
    List<Article> findArticleAll(String topic,String content);

    /**
     * 根据ID删除帖子
     * @param article_ids
     */
    void admin_delete_article(int[] article_ids);


    /**
     * 查找所有维修上报记录
     * @return
     */
    List<Repaired> findRepairedAll(String topic,String content,String location);


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
     * 管理员发布物业费用
     * @param admin_id
     * @param year
     * @param month
     * @param price
     * @return
     */
    boolean admin_property_publish(int admin_id,String year, String month, float price);


    /**
     * 所有物业账单详情
     * @param admin_id
     * @param user_name
     * @param year
     * @param month
     * @return
     */
    List<Property> getAllPropertyCost(int admin_id,String user_name,String year,String month);


    /**
     * 管理员查看所有用户
     * @param user_name
     * @return
     */
    List<User> getAllUserByName(String user_name,String user_idcard,String user_carnumber);


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
    List<Visitor> getAllVisitor(String visitor_name, String visitor_phonenumber, String visitor_carnumber);


    /**
     * 管理员根据ID删除游客
     * @param visitor_id
     */
    void admin_delete_visitor(int visitor_id);


    /**
     * 业主停车登记
     * @param user_park
     * @return
     */
    boolean admin_user_park(User_park user_park);


    /**
     * 游客停车登记
     * @param visitor_park
     * @return
     */
    boolean admin_visitor_park(Visitor_park visitor_park);


    /**
     * 正在停车的业主停车记录
     * @param park_location
     * @param user_carnumber
     * @return
     */
    List<User_park> getUserParking(String park_location, String user_carnumber);


    /**
     * 业主结束停车
     * @param park_id
     */
    void admin_user_stop_park(int park_id);


    /**
     * 业主缴纳停车费用
     * @param park_id
     */
    boolean admin_user_pay_park(int park_id);


    /**
     * 正在停车的游客停车记录
     * @param park_location
     * @param visitor_carnumber
     * @return
     */
    List<Visitor_park> getVisitorParking(String park_location, String visitor_carnumber);


    /**
     * 游客结束停车
     * @param park_id
     */
    void admin_visitor_stop_park(int park_id);


    /**
     * 业主缴纳停车费用
     * @param park_id
     * @return
     */
    boolean admin_visitor_pay_park(int park_id);


    /**
     * 业主停车账单页面
     * @param user_name
     * @param park_location
     * @param user_carnumber
     * @return
     */
    List<User_park> getUserParkCost(String user_name, String park_location, String user_carnumber);


    /**
     * 管理员删除业主停车记录
     * @param park_id
     */
    void admin_delete_user_park(int park_id);


    /**
     * 游客停车账单页面
     * @param visitor_name
     * @param park_location
     * @param visitor_carnumber
     * @return
     */
    List<Visitor_park> getVisitorParkCost(String visitor_name, String park_location, String visitor_carnumber);

    /**
     * 管理员删除游客停车记录
     * @param park_id
     */
    void admin_delete_visitor_park(int park_id);

    /**
     * 管理员发布通告
     * @param admin_id
     * @param topic
     * @param content
     * @return
     */
    boolean admin_publish_message(int admin_id, String topic, String content);


    /**
     * 通告详情
     * @param user_name
     * @param topic
     * @param content
     * @return
     */
    List<Message> getMessageList(String user_name, String topic, String content);


    /**
     * 管理员删除通告
     * @param message_ids
     */
    void admin_delete_message(int[] message_ids);
}
