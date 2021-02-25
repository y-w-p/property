package com.ywp.services.Impl;

import com.ywp.dao.UserDao;
import com.ywp.entity.User;
import com.ywp.eums.UserRegisteredEnum;
import com.ywp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业主业务层实现
 */


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 业主登录
     * @param user_name
     * @param user_password
     * @return
     */
    @Override
    public List<User> userLogin(String user_name, String user_password) {
        return userDao.userLogin(user_name,user_password);
    }


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
    @Override
    public boolean userRegistered(String user_name, String user_password, String user_idcard, String user_phonenumber, String user_address, String user_area, String user_carnumber) {
        //各项信息不为空
        if(!user_name.equals("") && !user_password.equals("") && !user_idcard.equals("") && !user_phonenumber.equals("") && !user_address.equals("") && !user_area.equals("")){
            //判断业主名是否存在
            List<User> users = userDao.findUserByName(user_name);
            for(User user:users){
                System.out.println(user);
                if (user != null){
                    return false;
                }
            }
            if(user_carnumber.equals("")){
                user_carnumber = "无";
            }
           Integer userid = userDao.userRegistered(user_name, user_password, user_idcard, user_phonenumber, user_address, user_area, user_carnumber);
           if(userid != null) {
               System.out.println(userid);
               return true;
           }
       }
        return false;
    }


}
