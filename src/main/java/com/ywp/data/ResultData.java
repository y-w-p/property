package com.ywp.data;

/**
 * 游客删除帖子，所需封装类
 */
public class ResultData {

    private boolean status;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
