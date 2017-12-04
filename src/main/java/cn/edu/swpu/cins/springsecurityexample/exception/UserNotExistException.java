package cn.edu.swpu.cins.springsecurityexample.exception;


public class UserNotExistException extends ExampleException {


    public UserNotExistException() {
        super();
    }

    public UserNotExistException(String message) {
        super(message);
    }
}
