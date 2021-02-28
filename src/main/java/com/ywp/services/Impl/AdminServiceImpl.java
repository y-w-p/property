package com.ywp.services.Impl;

import com.ywp.dao.AdminDao;
import com.ywp.entity.*;
import com.ywp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Property> getAllPropertyCost() {
        List<Property> allPropertyCost = adminDao.getAllPropertyCost();
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


}
