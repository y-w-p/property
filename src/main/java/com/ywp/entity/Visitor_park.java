package com.ywp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Visitor_park implements Serializable {
    private int park_id;
    private int visitor_id;
    private long cost;
    private long period;
    private String visitor_name;
    private String visitor_carnumber;
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

    public int getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(int visitor_id) {
        this.visitor_id = visitor_id;
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

    public String getVisitor_name() {
        return visitor_name;
    }

    public void setVisitor_name(String visitor_name) {
        this.visitor_name = visitor_name;
    }

    public String getVisitor_carnumber() {
        return visitor_carnumber;
    }

    public void setVisitor_carnumber(String visitor_carnumber) {
        this.visitor_carnumber = visitor_carnumber;
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
        return "Visitor_park{" +
                "park_id=" + park_id +
                ", visitor_id=" + visitor_id +
                ", cost=" + cost +
                ", period=" + period +
                ", visitor_name='" + visitor_name + '\'' +
                ", visitor_carnumber='" + visitor_carnumber + '\'' +
                ", park_location='" + park_location + '\'' +
                ", status='" + status + '\'' +
                ", park_start_time=" + park_start_time +
                ", park_end_time=" + park_end_time +
                '}';
    }
}
