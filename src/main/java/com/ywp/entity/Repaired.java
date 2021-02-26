package com.ywp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 维修表
 */
public class Repaired implements Serializable {

    private int repaired_id;
    private int user_id;
    private String topic;
    private String user_name;
    private String location;
    private String content;
    private String picture_path;
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publish_time;

    public int getRepaired_id() {
        return repaired_id;
    }

    public void setRepaired_id(int repaired_id) {
        this.repaired_id = repaired_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }


    @Override
    public String toString() {
        return "Repaired{" +
                "repaired_id=" + repaired_id +
                ", user_id=" + user_id +
                ", topic='" + topic + '\'' +
                ", user_name='" + user_name + '\'' +
                ", location='" + location + '\'' +
                ", content='" + content + '\'' +
                ", picture_path='" + picture_path + '\'' +
                ", status='" + status + '\'' +
                ", publish_time=" + publish_time +
                '}';
    }
}
