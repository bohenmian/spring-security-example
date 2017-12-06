package cn.edu.swpu.cins.springsecurityexample.model.http;

import org.springframework.http.HttpStatus;

public class HttpResult {

    private String msg;
    private HttpStatus status;

    public HttpResult(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
