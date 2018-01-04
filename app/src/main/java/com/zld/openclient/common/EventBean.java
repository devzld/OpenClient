package com.zld.openclient.common;

/**
 * Created by lingdong on 2018/1/4.
 */

public class EventBean {

    private String code;
    private String msg;

    public EventBean(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
