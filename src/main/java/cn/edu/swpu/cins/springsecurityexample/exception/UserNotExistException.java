package cn.edu.swpu.cins.springsecurityexample.exception;

import org.springframework.http.HttpStatus;

public class UserNotExistException extends ExampleException {

    public UserNotExistException() {
        super("user not exist", HttpStatus.NOT_FOUND);
    }
}
