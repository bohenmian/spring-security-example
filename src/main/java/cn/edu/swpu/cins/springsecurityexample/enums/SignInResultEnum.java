package cn.edu.swpu.cins.springsecurityexample.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SignInResultEnum {
    MSG_NOT_COMPLETE("miss username or password"),
    USER_NOT_FOUND("user not found"),
    USERNAME_PASSWORD_NOT_MATCH("username and password not match"),
    LOGIN_SUCCESS("login success"),
    UNAUTHORIZED("unauthorized");

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    SignInResultEnum(String msg) {
        this.msg = msg;
    }
}
