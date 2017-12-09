package cn.edu.swpu.cins.springsecurityexample.service.impl;


import cn.edu.swpu.cins.springsecurityexample.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(MyUserDetailServiceImpl.class);

    private PasswordService passwordService;

    @Autowired
    public MyUserDetailServiceImpl(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录认证");
        return new User(username, passwordService.encode("123456"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

}
