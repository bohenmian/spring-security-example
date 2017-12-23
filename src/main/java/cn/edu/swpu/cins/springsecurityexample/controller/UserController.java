package cn.edu.swpu.cins.springsecurityexample.controller;

import cn.edu.swpu.cins.springsecurityexample.constant.SecurityContants;
import cn.edu.swpu.cins.springsecurityexample.enums.SignInResultEnum;
import cn.edu.swpu.cins.springsecurityexample.exception.MissParamException;
import cn.edu.swpu.cins.springsecurityexample.exception.UserNotExistException;
import cn.edu.swpu.cins.springsecurityexample.generate.SmsCodeSenderGenerator;
import cn.edu.swpu.cins.springsecurityexample.generate.ValidateCodeGenerator;
import cn.edu.swpu.cins.springsecurityexample.model.http.Message;
import cn.edu.swpu.cins.springsecurityexample.model.http.SignInUser;
import cn.edu.swpu.cins.springsecurityexample.model.persistence.User;
import cn.edu.swpu.cins.springsecurityexample.model.service.ImageCode;
import cn.edu.swpu.cins.springsecurityexample.model.service.ValidateCode;
import cn.edu.swpu.cins.springsecurityexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static cn.edu.swpu.cins.springsecurityexample.enums.SignInResultEnum.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    private UserService userService;
    private ValidateCodeGenerator imageCodeGenerator;
    private ValidateCodeGenerator smsCodeGenerator;
    private SmsCodeSenderGenerator smsCodeSenderGenerator;


    @Autowired
    public UserController(UserService userService, ValidateCodeGenerator imageCodeGenerator,
                          ValidateCodeGenerator smsCodeGenerator, SmsCodeSenderGenerator smsCodeSenderGenerator) {
        this.userService = userService;
        this.imageCodeGenerator = imageCodeGenerator;
        this.smsCodeGenerator = smsCodeGenerator;
        this.smsCodeSenderGenerator = smsCodeSenderGenerator;
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

    @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        //TODO连接短信服务商
        smsCodeSenderGenerator.send(mobile, smsCode.getCode());
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
