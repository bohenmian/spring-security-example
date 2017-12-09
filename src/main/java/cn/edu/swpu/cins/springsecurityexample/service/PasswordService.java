package cn.edu.swpu.cins.springsecurityexample.service;

public interface PasswordService {
    String encode(String password);

    boolean matches(String password, String encodedPassword);
}
