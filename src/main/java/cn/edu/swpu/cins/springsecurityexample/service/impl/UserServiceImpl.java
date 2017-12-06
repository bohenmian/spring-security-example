package cn.edu.swpu.cins.springsecurityexample.service.impl;

import cn.edu.swpu.cins.springsecurityexample.enums.SignInResultEnum;
import cn.edu.swpu.cins.springsecurityexample.exception.MissParamException;
import cn.edu.swpu.cins.springsecurityexample.exception.UserNotExistException;
import cn.edu.swpu.cins.springsecurityexample.model.http.SignInUser;
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
        User user = userRepository.getUserById(id);
        if (user == null) {
            throw new UserNotExistException();
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public SignInResultEnum signIn(SignInUser signUser) {
        if (signUser == null || signUser.getUsername() == null || signUser.getPassword() == null) {
            throw new MissParamException();
        }
        User user = this.loadUserByName(signUser.getUsername());
        boolean result = this.checkPassword(signUser.getPassword(), user.getPassword());
        if (result) {
            return SignInResultEnum.LOGIN_SUCCESS;
        } else {
            return SignInResultEnum.USERNAME_PASSWORD_NOT_MATCH;
        }
    }


    private User loadUserByName(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UserNotExistException();
        }
        return user;
    }

    private boolean checkPassword(String loginPassword, String savedPassword) {
        if (loginPassword.equals(savedPassword)) {
            return true;
        }
        return false;
    }
}
