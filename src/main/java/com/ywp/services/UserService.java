package com.ywp.services;

import com.ywp.entity.Property;
import com.ywp.entity.User;
import com.ywp.entity.User_park;

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
}
