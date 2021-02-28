package com.ywp.services;

import com.ywp.entity.Admin;
import com.ywp.entity.Article;
import com.ywp.entity.Property;
import com.ywp.entity.Repaired;

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
    List<Article> findArticleAll();

    /**
     * 根据ID删除帖子
     * @param article_ids
     */
    void admin_delete_article(int[] article_ids);


    /**
     * 查找所有维修上报记录
     * @return
     */
    List<Repaired> findRepairedAll();


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
     * @param year
     * @param month
     * @param price
     * @return
     */
    boolean admin_property_publish(String admin_name,String year, String month, float price);


    /**
     * 所有物业账单详情
     * @return
     */
    List<Property> getAllPropertyCost();
}
