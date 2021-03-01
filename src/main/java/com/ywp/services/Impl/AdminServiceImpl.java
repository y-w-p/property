package com.ywp.services.Impl;

import com.ywp.dao.AdminDao;
import com.ywp.entity.*;
import com.ywp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 管理员业务实现层
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    /**
     * 管理员登录
     * @param admin_name
     * @param admin_password
     * @return
     */
    @Override
    public List<Admin> adminLogin(String admin_name, String admin_password) {
        return adminDao.adminLogin(admin_name,admin_password);
    }


    /**
     * 管理员个人信息更新
     * @param admin
     * @return
     */
    @Override
    public boolean admin_info_update(Admin admin) {
        //查看管理员名称是否已占用
       List<Admin> adminByName = adminDao.findAdminByName(admin.getAdmin_name());
        for(Admin admin1:adminByName){
          if(admin1 != null){
              //用户名有，不更改用户名
              adminDao.admin_info_save(admin);
              return true;
          }
      }
        adminDao.admin_info_update(admin);
       return true;
    }


    /**
     * 查找所有已发帖子
     * @return
     */
    @Override
    public List<Article> findArticleAll() {
        return adminDao.findArticleAll();
    }


    /**
     * 根据ID删除帖子
     * @param article_ids
     */
    @Override
    public void admin_delete_article(int[] article_ids) {
        adminDao.admin_delete_article(article_ids);
    }


    /**
     * 查找所有维修上报记录
     * @return
     */
    @Override
    public List<Repaired> findRepairedAll() {
        List<Repaired> repairedAll = adminDao.findRepairedAll();

        for(Repaired repaired:repairedAll){
           if(repaired.getStatus().equals("0")){
               repaired.setStatus("未审核");
           }
           if(repaired.getStatus().equals("1")){
             repaired.setStatus("未通过审核");
          }
           if(repaired.getStatus().equals("2")){
              repaired.setStatus("通过审核，等待维修");
           }
           if(repaired.getStatus().equals("3")){
              repaired.setStatus("已维修");
           }
        }

        return repairedAll;
    }


    /**
     * 根据维修ID删除上报维修
     * @param repaired_ids
     */
    @Override
    public void admin_delete_repaired(int[] repaired_ids) {
        adminDao.admin_delete_repaired(repaired_ids);
    }


    /**
     * 根据ID不通过报修审核
     * @param repaired_id
     */
    @Override
    public void admin_repaired_check_no(int repaired_id) {
        adminDao.admin_repaired_check_no(repaired_id);
    }

    /**
     * 根据ID不通过报修审核
     * @param repaired_id
     */
    @Override
    public void admin_repaired_checked(int repaired_id) {
        adminDao.admin_repaired_checked(repaired_id);
    }

    /**
     * 根据ID报修已维修
     * @param repaired_id
     */
    @Override
    public void admin_repaired_repaired(int repaired_id) {
        adminDao.admin_repaired_repaired(repaired_id);
    }


    /**
     * 管理员发布物业费用
     * @param year
     * @param month
     * @param price
     * @return
     */
    @Override
    public boolean admin_property_publish(String admin_name,String year, String month, float price) {
        List<User> userAll = adminDao.findUserAll();
        Property property = new Property();
        float money;
        for (User user:userAll){
            //计算金额
            money = Float.valueOf(user.getUser_area()) * price;

            property.setAdmin_name(admin_name);
            property.setUser_id(user.getUser_id());
            property.setYear(year);
            property.setMonth(month);
            property.setMoney(Float.toString(money));
            property.setStatus("0");

            adminDao.admin_property_publish(property);
        }
        return true;
    }


    /**
     * 所有物业账单详情
     * @return
     */
    @Override
    public List<Property> getAllPropertyCost(String user_name,String year,String month) {
        List<Property> allPropertyCost = adminDao.getAllPropertyCost(user_name,year,month);
        for (Property property:allPropertyCost){
            if(property.getStatus().equals("0")){
                property.setStatus("未缴费");
            }
            if(property.getStatus().equals("1")){
                property.setStatus("已缴费");
            }
        }
        return allPropertyCost;
    }


    /**
     * 管理员查看所有用户
     * @param user_name
     * @return
     */
    @Override
    public List<User> getAllUserByName(String user_name,String user_idcard,String user_carnumber) {

        return adminDao.getAllUserByName(user_name,user_idcard,user_carnumber);
    }


    /**
     * 管理员根据ID删除业主
     * @param user_id
     */
    @Override
    public void admin_delete_user(int user_id) {
        adminDao.admin_delete_user(user_id);
    }


    /**
     * 管理员查看所有游客
     * @param visitor_name
     * @param visitor_phonenumber
     * @param visitor_carnumber
     * @return
     */
    @Override
    public List<Visitor> getAllVisitor(String visitor_name, String visitor_phonenumber, String visitor_carnumber) {
        return adminDao.getAllVisitor(visitor_name,visitor_phonenumber,visitor_carnumber);
    }


    /**
     * 管理员根据ID删除游客
     * @param visitor_id
     */
    @Override
    public void admin_delete_visitor(int visitor_id) {
        adminDao.admin_delete_visitor(visitor_id);
    }


    /**
     * 业主停车登记
     * @param user_park
     * @return
     */
    @Override
    public boolean admin_user_park(User_park user_park) {
        int park_id = adminDao.admin_user_park(user_park);
        if(!String.valueOf(park_id).equals("")){
            return true;
        }
        return false;
    }


    /**
     * 游客停车登记
     * @param visitor_park
     * @return
     */
    @Override
    public boolean admin_visitor_park(Visitor_park visitor_park) {
        int park_id = adminDao.admin_visitor_park(visitor_park);
        if(!String.valueOf(park_id).equals("")){
           return true;
        }
       return false;
    }


    /**
     * 正在停车的业主停车记录
     * @param park_location
     * @param user_carnumber
     * @return
     */
    @Override
    public List<User_park> getUserParking(String park_location, String user_carnumber) {
        List<User_park> user_parks = adminDao.getUserParking(park_location,user_carnumber);
        for(User_park user_park:user_parks){
            if(user_park.getStatus().equals("0") ){
                if(user_park.getPark_end_time() == null){
                    //尚未结束停车
                    user_park.setStatus("正在停车中..");
                }else {
                    //已结束停车,计算金额
                    user_park.setStatus("待结算中..");

                    long start_time = user_park.getPark_start_time().getTime();//这样得到的差值是微秒级别
                    long end_time = user_park.getPark_end_time().getTime();
                    //计算天
                    long days = (end_time-start_time)/(1000*60*60*24);
                    long other = (end_time-start_time)%(1000*60*60*24);

                    if(other > 0){
                        //过了一天，不满另一天
                        user_park.setCost((days+1)*5);
                    }else {
                        user_park.setCost(days*5);
                    }


                }

            }
        }
        return user_parks;
    }


    /**
     * 业主结束停车
     * @param park_id
     */
    @Override
    public void admin_user_stop_park(int park_id) {
        adminDao.admin_user_stop_park(park_id);
    }


    /**
     * 业主缴纳停车费用
     * @param park_id
     */
    @Override
    public boolean admin_user_pay_park(int park_id) {
        //查找停车单
       User_park user_park =adminDao.findUserParkByID(park_id);
       if(user_park.getPark_end_time() == null){
           return false;
       }
       //已结束停车
        long start_time = user_park.getPark_start_time().getTime();//这样得到的差值是微秒级别
        long end_time = user_park.getPark_end_time().getTime();
        //计算天
        long days = (end_time-start_time)/(1000*60*60*24);
        long other = (end_time-start_time)%(1000*60*60*24);
        //设置金额
        if(other > 0){
            //过了一天，不满另一天
            user_park.setCost((days+1)*5);
        }else {
            user_park.setCost(days*5);
        }
        user_park.setStatus("1");

        //完成缴费
        adminDao.admin_user_pay_park(user_park);

       return true;
    }


    /**
     * 正在停车的游客停车记录
     * @param park_location
     * @param visitor_carnumber
     * @return
     */
    @Override
    public List<Visitor_park> getVisitorParking(String park_location, String visitor_carnumber) {
        List<Visitor_park> visitor_parks = adminDao.getVisitorParking(park_location,visitor_carnumber);
         for(Visitor_park visitor_park:visitor_parks){
             if(visitor_park.getStatus().equals("0") ){
                 if(visitor_park.getPark_end_time() == null){
                     //尚未结束停车
                     visitor_park.setStatus("正在停车中..");
                 }else {
                     //已结束停车,计算金额
                     visitor_park.setStatus("待结算中..");

                     long start_time = visitor_park.getPark_start_time().getTime();//这样得到的差值是微秒级别
                     long end_time = visitor_park.getPark_end_time().getTime();
                     //计算小时
                     long hours = (end_time-start_time)/(1000*60*60);
                     long other = (end_time-start_time)%(1000*60*60);

                     if(other > 0){
                         //过了一小时，不满另一小时
                         visitor_park.setCost((hours+1)*5);
                     }else {
                         visitor_park.setCost(hours*5);
                     }


                 }

             }
         }
         return visitor_parks;
    }


    /**
     * 游客结束停车
     * @param park_id
     */
    @Override
    public void admin_visitor_stop_park(int park_id) {
        adminDao.admin_visitor_stop_park(park_id);
    }


    /**
     * 业主缴纳停车费用
     * @param park_id
     * @return
     */
    @Override
    public boolean admin_visitor_pay_park(int park_id) {
        //查找停车单
         Visitor_park visitor_park =adminDao.findVisitorParkByID(park_id);
         if(visitor_park.getPark_end_time() == null){
             return false;
         }
         //已结束停车
          long start_time = visitor_park.getPark_start_time().getTime();//这样得到的差值是微秒级别
          long end_time = visitor_park.getPark_end_time().getTime();
          //计算天
          long hours = (end_time-start_time)/(1000*60*60);
          long other = (end_time-start_time)%(1000*60*60);
          //设置金额
          if(other > 0){
              //过了一小时，不满另一小时
              visitor_park.setCost((hours+1)*5);
          }else {
              visitor_park.setCost(hours*5);
          }
        visitor_park.setStatus("1");

          //完成缴费
          adminDao.admin_visitor_pay_park(visitor_park);

         return true;
    }


}
