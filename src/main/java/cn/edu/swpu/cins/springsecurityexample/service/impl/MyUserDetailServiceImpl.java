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
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(MyUserDetailServiceImpl.class);

    private PasswordService passwordService;

    @Autowired
    public MyUserDetailServiceImpl(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: 18-5-30 find user from database and match it, and authenticate it authentication
        log.info("表单登录用户名:" + username);
        return buildUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("登录Id" + userId);
        return buildUser(userId);
    }

    private SocialUser buildUser(String username) {
        return new SocialUser(username, passwordService.encode("123456"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
