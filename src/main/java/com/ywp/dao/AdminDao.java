package com.ywp.dao;

import com.ywp.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

}
