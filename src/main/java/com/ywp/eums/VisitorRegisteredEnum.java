package com.ywp.eums;

public enum VisitorRegisteredEnum {

    VISITOR_REGISTERED_SUCCESS("visitor_registered_success","游客注册成功"),
    VISITOR_REGISTERED_FAIL("visitor_registered_fail","游客注册失败");


    private String value;
    private String desc;

    VisitorRegisteredEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
