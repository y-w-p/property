package com.ywp.services;

import com.ywp.entity.User;

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

}
