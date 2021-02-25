package com.ywp.eums;

public enum UserRegisteredEnum {

    USER_REGISTERED_SUCCESS("user_registered_success","业主注册成功"),
    USER_REGISTERED_FAIL("usre_registered_fail","业主注册失败");


    private String value;
    private String desc;

    UserRegisteredEnum(String value, String desc) {
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
