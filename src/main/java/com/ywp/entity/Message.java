package com.ywp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息实体类
 */
public class Message implements Serializable {

    private int message_id;
    private int admin_id;
    private String admin_name;
    private int user_id;
    private String user_name;
    private int message_text_id;
    private String topic;
    private String content;

    private String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publish_time;


    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getMessage_text_id() {
        return message_text_id;
    }

    public void setMessage_text_id(int message_text_id) {
        this.message_text_id = message_text_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Message{" +
                "message_id=" + message_id +
                ", admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", message_text_id=" + message_text_id +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", publish_time=" + publish_time +
                '}';
    }
}
