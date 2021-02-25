package com.ywp.services;

import com.ywp.entity.Admin;

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
}
