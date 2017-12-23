package cn.edu.swpu.cins.springsecurityexample.controller;

import cn.edu.swpu.cins.springsecurityexample.config.processor.ValidateCodeProcessor;
import cn.edu.swpu.cins.springsecurityexample.enums.SignInResultEnum;
import cn.edu.swpu.cins.springsecurityexample.exception.MissParamException;
import cn.edu.swpu.cins.springsecurityexample.exception.UserNotExistException;
import cn.edu.swpu.cins.springsecurityexample.model.http.Message;
import cn.edu.swpu.cins.springsecurityexample.model.http.SignInUser;
import cn.edu.swpu.cins.springsecurityexample.model.persistence.User;
import cn.edu.swpu.cins.springsecurityexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static cn.edu.swpu.cins.springsecurityexample.enums.SignInResultEnum.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private Map<String, ValidateCodeProcessor> validateCodeProcessorHolderMap;


    @Autowired
    public UserController(UserService userService, Map<String, ValidateCodeProcessor> validateCodeProcessorHolderMap) {
        this.userService = userService;
        this.validateCodeProcessorHolderMap = validateCodeProcessorHolderMap;
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

    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody SignInUser signInUser) {
        SignInResultEnum signInResultEnum = userService.signIn(signInUser);
        if (signInResultEnum == LOGIN_SUCCESS) {
            return new ResponseEntity<>(new Message(signInResultEnum.getMsg()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message(signInResultEnum.getMsg()), HttpStatus.FORBIDDEN);
    }

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) throws Exception {
        validateCodeProcessorHolderMap.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
    }


    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Message handleNotFoundException() {
        return new Message(USER_NOT_FOUND.getMsg());
    }

    @ExceptionHandler(MissParamException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message handleMissParamException() {
        return new Message(MSG_NOT_COMPLETE.getMsg());
    }

}
