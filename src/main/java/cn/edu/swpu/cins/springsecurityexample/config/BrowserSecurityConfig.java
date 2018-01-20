package cn.edu.swpu.cins.springsecurityexample.config;

import cn.edu.swpu.cins.springsecurityexample.config.config.FormAuthenticationConfig;
import cn.edu.swpu.cins.springsecurityexample.config.config.ValidateCodeSecurityConfig;
import cn.edu.swpu.cins.springsecurityexample.config.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    private SecurityProperties securityProperties;
    private DataSource dataSource;
    private UserDetailsService userDetailsService;
    private FormAuthenticationConfig formAuthenticationConfig;
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    private SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    public BrowserSecurityConfig(SecurityProperties securityProperties, DataSource dataSource,
                                 UserDetailsService userDetailsService, FormAuthenticationConfig formAuthenticationConfig,
                                 ValidateCodeSecurityConfig validateCodeSecurityConfig, SpringSocialConfigurer springSocialConfigurer) {
        this.securityProperties = securityProperties;
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
        this.formAuthenticationConfig = formAuthenticationConfig;
        this.validateCodeSecurityConfig = validateCodeSecurityConfig;
        this.springSocialConfigurer = springSocialConfigurer;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .csrf().disable()
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
