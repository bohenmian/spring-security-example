package cn.edu.swpu.cins.springsecurityexample.controller;

import cn.edu.swpu.cins.springsecurityexample.exception.UserNotExistException;
import cn.edu.swpu.cins.springsecurityexample.model.http.Message;
import cn.edu.swpu.cins.springsecurityexample.model.persistence.User;
import cn.edu.swpu.cins.springsecurityexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUser() {
        return userService.getUser();
    }

    @GetMapping("/{id:\\d+}")
    public User getUserInfo(@PathVariable("id") Long id) {
        return userService.getUserInfo(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Message handleNotFoundException(UserNotExistException exception) {
        return new Message(exception.getMessage());
    }
}
