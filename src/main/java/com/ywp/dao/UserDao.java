package com.ywp.dao;

import com.ywp.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 业主持久层接口
 */
@Repository
public interface UserDao {

    /**
     * 业主登录
     * @param user_name
     * @param user_password
     * @return
     */
    public List<User> userLogin(@Param("user_name")String user_name, @Param("user_password")String user_password);


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
    public Integer userRegistered(@Param("user_name") String user_name,@Param("user_password")String user_password,@Param("user_idcard")String user_idcard,
                                  @Param("user_phonenumber")String user_phonenumber,@Param("user_address")String user_address,
                                  @Param("user_area")String user_area,@Param("user_carnumber")String user_carnumber);



    /**
       * 通过业主名称查找业主
       * @param user_name
       * @return
       */
      public List<User> findUserByName(@Param("user_name")String user_name);


}
