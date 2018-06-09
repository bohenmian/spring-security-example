package cn.edu.swpu.cins.springsecurityexample.config.config;

import cn.edu.swpu.cins.springsecurityexample.authentication.MyAuthenticationFailureHandler;
import cn.edu.swpu.cins.springsecurityexample.authentication.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class FormAuthenticationConfig {

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/user/signIn")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
    }
}


