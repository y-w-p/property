package com.ywp.entity;

import java.io.Serializable;

/**
 * 业主实体类
 */
public class User implements Serializable {

    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_idcard;
    private String user_phonenumber;
    private String user_address;
    private String user_area;
    private String user_carnumber;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_idcard() {
        return user_idcard;
    }

    public void setUser_idcard(String user_idcard) {
        this.user_idcard = user_idcard;
    }

    public String getUser_phonenumber() {
        return user_phonenumber;
    }

    public void setUser_phonenumber(String user_phonenumber) {
        this.user_phonenumber = user_phonenumber;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_area() {
        return user_area;
    }

    public void setUser_area(String user_area) {
        this.user_area = user_area;
    }

    public String getUser_carnumber() {
        return user_carnumber;
    }

    public void setUser_carnumber(String user_carnumber) {
        this.user_carnumber = user_carnumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_idcard='" + user_idcard + '\'' +
                ", user_phonenumber='" + user_phonenumber + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_area='" + user_area + '\'' +
                ", user_carnumber='" + user_carnumber + '\'' +
                '}';
    }
}
