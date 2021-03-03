package com.ywp.services;

import com.ywp.entity.*;

import java.util.List;

/**
 * 业主业务层接口
 */

public interface UserService {

    /**
     * 业主登录
     * @param user_name
     * @param user_password
     * @return
     */
    public List<User> userLogin(String user_name,String user_password);


    /**
     * 业主注册
     * @param user_name
     * @param user_password
     * @param user_idcard
     * @param user_phonenumber
     * @param user_address
     * @param user_area
     * @param user_carnumber
     * @return
     */
    public boolean userRegistered(String user_name,String user_password,String user_idcard,
                                     String user_phonenumber,String user_address,
                                     String user_area,String user_carnumber);


    /**
     * 业主个人信息更改
     * @param user
     */
    public boolean user_info_update(User user);


    /**
     * 业主停车详情
     * @param user_id
     * @return
     */
    public List<User_park> getUserPark(int user_id);

    /**
     * 业主停车账单详情
     * @param user_id
     * @return
     */
    List<User_park> getUserParkCost(int user_id);

    /**
     * 业主物业账单详情
     * @param user_id
     * @return
     */
    List<Property> getUserPropertyCost(int user_id);

    /**
     * 业主缴纳物业费
     * @param property_id
     */
    void delivery_property(int property_id);


    /**
        * 业主缴纳停车费
        * @param park_id
        */
    void delivery_park(int park_id);

    /**
     * 业主上报维修
     * @param repaired
     * @return
     */
    public boolean user_repaired(Repaired repaired);

    /**
     * 通过业主ID得到业主上报维修记录
     * @param user_id
     */
    List<Repaired> getUserRepairedList(int user_id);

    /**
     * 业主删除维修单
     * @param repaired_ids
     */
    void user_delete_repaired(int[] repaired_ids);


    /**
     * 业主通告详情
     * @param user_id
     * @param topic
     * @param content
     * @return
     */
    List<Message> getUserMessageList(int user_id,String topic, String content);


    /**
     * 业主阅读通告
     * @param message_id
     */
    void user_message_look(int message_id);


    /**
     * 业主删除通告
     * @param message_id
     */
    void user_delete_message(int message_id);
}
