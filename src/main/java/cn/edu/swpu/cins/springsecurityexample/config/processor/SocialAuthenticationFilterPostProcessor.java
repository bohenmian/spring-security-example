package cn.edu.swpu.cins.springsecurityexample.config.processor;

import org.springframework.social.security.SocialAuthenticationFilter;

public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
