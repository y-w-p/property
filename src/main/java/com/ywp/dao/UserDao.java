package com.ywp.dao;

import com.ywp.entity.*;
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



    /**
     * 业主个人信息更改（包括用户名）
     * @param user
     */
    public void user_info_update(User user);


    /**
     * 业主个人信息保存（不包括用户名）
     * @param user
     */
    public void user_info_save(User user);


    /**
     * 业主停车详情
     * @param user_park
     * @return
     */
    List<User_park> findUserParkByID(User_park user_park);


    /**
     *  通过业主ID查找业主停车账单(搜索条件不包含停车单号)
     * @param user_park
     * @return
     */
    public List<User_park> findUserParkCostByID(User_park user_park);


    /**
     * 通过业主ID查找业主停车账单(搜索条件包含停车单号)
     * @param user_park1
     * @return
     */
    List<User_park> findUserParkCostByParkID(User_park user_park1);



    /**
     * 更新业主停车费用
     * @param park_id
     * @param i
     */
    void updateUserParkCost(@Param("park_id")int park_id, @Param("cost")int i);

    /**
     * 通过业主ID查找业主物业账单
     * @param property
     * @return
     */
    List<Property> findUserPropertyCostByID(Property property);


    /**
     * 业主缴纳物业费用
     * @param property_id
     */
    void delivery_property(int property_id);

    /**
     * 业主缴纳停车费
     * @param park_id
     */
    void delivery_park(int park_id);

    /**
     * 通过业主id查找业主维修上报记录
     * @param repaired
     * @return
     */
    List<Repaired> findUserRepairedByID(Repaired repaired);


    /**
     * 业主上报维修
     * @param repaired
     * @return
     */
    public Integer user_repaired(Repaired repaired);

    /**
     * 业主删除维修单
     * @param repaired_ids
     */
    void user_delete_repaired(int[] repaired_ids);


    /**
     * 业主通告详情
     * @param message
     * @return
     */
    List<Message> getUserMessageList(Message message);


    /**
     * 业主阅读通告
     * @param message_id
     */
    void user_message_look(@Param("message_id") int message_id);


    /**
     * 业主删除通告
     * @param message_id
     */
    void user_delete_message(@Param("message_id")int message_id);


}
