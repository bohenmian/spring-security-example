package cn.edu.swpu.cins.springsecurityexample.exception;

import org.springframework.http.HttpStatus;

public class ExampleException extends RuntimeException {
    private HttpStatus httpStatus;

    public ExampleException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "ExampleException{" +
                "httpStatus=" + httpStatus +
                '}';
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
