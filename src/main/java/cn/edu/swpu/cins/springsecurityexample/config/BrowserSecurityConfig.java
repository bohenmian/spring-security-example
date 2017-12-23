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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.servlet.ServletException;
import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    private SecurityProperties securityProperties;
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    private DataSource dataSource;
    private UserDetailsService userDetailsService;
    private SpringSocialConfigurer springSocialConfigurer;

    @SuppressWarnings("SpringJavaAutowiringinspect")
    @Autowired
    public BrowserSecurityConfig(SecurityProperties securityProperties, MyAuthenticationSuccessHandler authenticationSuccessHandler,
                                 MyAuthenticationFailureHandler authenticationFailureHandler, DataSource dataSource,
                                 UserDetailsService userDetailsService, SpringSocialConfigurer springSocialConfigurer) {
        this.securityProperties = securityProperties;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
        this.springSocialConfigurer = springSocialConfigurer;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ValidateCodeFilter validateCodeFilterBean() throws ServletException {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        return validateCodeFilter;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepositoryBean() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        //设置启动时是否创建表,如果表已经存在会抛出异常
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(validateCodeFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepositoryBean())
                .tokenValiditySeconds(securityProperties.getBrowserProperties().getRememberMe())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require", securityProperties.getBrowserProperties().getLoginPage(), "/user/code/*")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
