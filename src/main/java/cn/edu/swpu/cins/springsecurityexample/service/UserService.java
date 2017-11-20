package cn.edu.swpu.cins.springsecurityexample.service;

import cn.edu.swpu.cins.springsecurityexample.model.persistence.User;

import java.util.List;

public interface UserService {

    List<User> getUser();
}
