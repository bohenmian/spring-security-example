package cn.edu.swpu.cins.springsecurityexample.service.impl;

import cn.edu.swpu.cins.springsecurityexample.model.persistence.User;
import cn.edu.swpu.cins.springsecurityexample.repository.UserRepository;
import cn.edu.swpu.cins.springsecurityexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserInfo(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
