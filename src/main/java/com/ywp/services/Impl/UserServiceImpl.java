package com.ywp.services.Impl;

import com.ywp.dao.UserDao;
import com.ywp.entity.*;
import com.ywp.eums.UserRegisteredEnum;
import com.ywp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    /**
     * 业主个人信息更改
     * @param user
     * @return
     */
    @Override
    public boolean user_info_update(User user) {
        //查看游客名称是否已占用
           List<User> userByName = userDao.findUserByName(user.getUser_name());
            for(User user1:userByName){
              if(user1 != null){
                  //用户名有，不更改用户名
                  userDao.user_info_save(user);
                  return true;
              }
          }
           userDao.user_info_update(user);
           return true;
    }

    /**
     * 业主停车详情
     * @param user_id
     * @return
     */
    @Override
    public List<User_park> getUserPark(int user_id) {
        List<User_park> user_park_list = userDao.findUserParkByID(user_id);
        return user_park_list;
    }


    /**
     * 业主停车账单详情
     * @param user_id
     * @return
     */
    @Override
    public List<User_park> getUserParkCost(int user_id) {
        List<User_park> userParkCostList = userDao.findUserParkCostByID(user_id);
        //计算时长和金额
       for(User_park user_park:userParkCostList){
           if(user_park != null){
               //计算分钟
               long minute = user_park.getPeriod();
               user_park.setPeriod(minute);
               //计算金额
               int time1 = (int)(minute /(60*24));
               int time2 = (int)(minute /(60*24));
               //不足一小时
               if(time1 == 0){
                   user_park.setCost(5);
               }else {
                   if(time2>0){
                       //超过半天，不足一天，算一天
                       time1 = time1+1;
                   }
                   //2元/12h
                   user_park.setCost(time1*5);
               }
             if(user_park.getStatus().equals("1")){
                 user_park.setStatus("已缴费");
             }else {
                 user_park.setStatus("未缴费");
             }
           }
       }
        return userParkCostList;
    }

    /**
     * 业主物业账单详情
     * @param user_id
     * @return
     */
    @Override
    public List<Property> getUserPropertyCost(int user_id) {
        List<Property> userPropertyCostList = userDao.findUserPropertyCostByID(user_id);
        for (Property property:userPropertyCostList){
           //1平方2元
           BigDecimal user_area = property.getUser_area();
           BigDecimal num = new BigDecimal(Double.toString(2)); //1平方价格
           BigDecimal money = num.multiply(user_area);
           property.setMoney(money.stripTrailingZeros().toPlainString());//stripTrailingZeros去除末尾的".0"，toPlainString表示不使用科学计数法（toString会使用科学计数法）

           //缴费状态
           if(property.getStatus().equals("1")){
               property.setStatus("已缴费");
           }else{
               property.setStatus("未缴费");
           }
       }
        return userPropertyCostList;
    }

    /**
     * 业主缴纳物业费用
     * @param property_id
     */
    @Override
    public void delivery_property(int property_id) {
        userDao.delivery_property(property_id);
    }


}
