package com.ywp.entity;

import java.io.Serializable;

/**
 * 游客实体类
 */
public class Visitor implements Serializable {

    private Object visitor_id;
    private String visitor_name;
    private String visitor_password;
    private String visitor_phonenumber;
    private String visitor_carnumber;

    public Object getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(Object visitor_id) {
        this.visitor_id = visitor_id;
    }

    public String getVisitor_name() {
        return visitor_name;
    }

    public void setVisitor_name(String visitor_name) {
        this.visitor_name = visitor_name;
    }

    public String getVisitor_password() {
        return visitor_password;
    }

    public void setVisitor_password(String visitor_password) {
        this.visitor_password = visitor_password;
    }

    public String getVisitor_phonenumber() {
        return visitor_phonenumber;
    }

    public void setVisitor_phonenumber(String visitor_phonenumber) {
        this.visitor_phonenumber = visitor_phonenumber;
    }

    public String getVisitor_carnumber() {
        return visitor_carnumber;
    }

    public void setVisitor_carnumber(String visitor_carnumber) {
        this.visitor_carnumber = visitor_carnumber;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "visitor_id=" + visitor_id +
                ", visitor_name='" + visitor_name + '\'' +
                ", visitor_password='" + visitor_password + '\'' +
                ", visitor_phonenumber='" + visitor_phonenumber + '\'' +
                ", visitor_carnumber='" + visitor_carnumber + '\'' +
                '}';
    }
}
