package com.ywp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 停车记录，停车账单
 */
public class User_park implements Serializable {
   private int park_id;
   private int user_id;
   private long cost;
   private long period;
   private String user_name;
   private String user_carnumber;
   private String park_location;
   private String status;

   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
   private Date park_start_time;

   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
   private Date park_end_time;

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_carnumber() {
        return user_carnumber;
    }

    public void setUser_carnumber(String user_carnumber) {
        this.user_carnumber = user_carnumber;
    }

    public String getPark_location() {
        return park_location;
    }

    public void setPark_location(String park_location) {
        this.park_location = park_location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPark_start_time() {
        return park_start_time;
    }

    public void setPark_start_time(Date park_start_time) {
        this.park_start_time = park_start_time;
    }

    public Date getPark_end_time() {
        return park_end_time;
    }

    public void setPark_end_time(Date park_end_time) {
        this.park_end_time = park_end_time;
    }


    @Override
    public String toString() {
        return "User_park{" +
                "park_id=" + park_id +
                ", user_id=" + user_id +
                ", cost=" + cost +
                ", period=" + period +
                ", user_name='" + user_name + '\'' +
                ", user_carnumber='" + user_carnumber + '\'' +
                ", park_location='" + park_location + '\'' +
                ", status='" + status + '\'' +
                ", park_start_time=" + park_start_time +
                ", park_end_time=" + park_end_time +
                '}';
    }
}
