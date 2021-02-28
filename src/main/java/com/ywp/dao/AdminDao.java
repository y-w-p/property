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
    List<Article> findArticleAll();


    /**
     * 根据ID删除帖子
     * @param article_ids
     */
    void admin_delete_article(int[] article_ids);


    /**
     * 查找所有上报维修记录
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
    List<Property> getAllPropertyCost();
}
