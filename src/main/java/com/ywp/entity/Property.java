package com.ywp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 物业账单表
 */
public class Property implements Serializable {
    private int property_id;
    private String admin_name;
    private int user_id;
    private String year;
    private String month;
    private String money;
    private String status;

    private String user_name;
    private BigDecimal user_area;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publish_time;


    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public BigDecimal getUser_area() {
        return user_area;
    }

    public void setUser_area(BigDecimal user_area) {
        this.user_area = user_area;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }


    @Override
    public String toString() {
        return "Property{" +
                "property_id=" + property_id +
                ", admin_name='" + admin_name + '\'' +
                ", user_id=" + user_id +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", money='" + money + '\'' +
                ", status='" + status + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_area=" + user_area +
                ", publish_time=" + publish_time +
                '}';
    }
}
