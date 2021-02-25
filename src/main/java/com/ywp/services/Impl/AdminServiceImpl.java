package com.ywp.services.Impl;

import com.ywp.dao.AdminDao;
import com.ywp.entity.Admin;
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
}
