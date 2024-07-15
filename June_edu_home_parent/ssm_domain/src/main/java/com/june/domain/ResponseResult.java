package com.june.domain;

/**
 * 响应结果封装对象
 * */
public class ResponseResult {

    private Boolean success;//是否响应成功
    private Integer state;//状态码
    private String message;//响应信息
    private Object content;//响应数据

    public ResponseResult() {
    }

    public ResponseResult(Boolean success, Integer state, String message, Object content) {
        this.success = success;
        this.state = state;
        this.message = message;
        this.content = content;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
