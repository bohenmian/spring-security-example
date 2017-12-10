package cn.edu.swpu.cins.springsecurityexample.config;

import cn.edu.swpu.cins.springsecurityexample.authentication.MyAuthenticationFailureHandler;
import cn.edu.swpu.cins.springsecurityexample.authentication.MyAuthenticationSuccessHandler;
import cn.edu.swpu.cins.springsecurityexample.config.filter.ValidateCodeFilter;
import cn.edu.swpu.cins.springsecurityexample.config.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    private SecurityProperties securityProperties;
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public BrowserSecurityConfig(SecurityProperties securityProperties, MyAuthenticationSuccessHandler authenticationSuccessHandler,
                                 MyAuthenticationFailureHandler authenticationFailureHandler) {
        this.securityProperties = securityProperties;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ValidateCodeFilter validateCodeFilterBeam() {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return new ValidateCodeFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilterBeam(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require", securityProperties.getBrowserProperties().getLoginPage(), "/user/code/image")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
