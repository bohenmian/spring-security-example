package cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.configurer;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * MySpringSocialConfigurer继承了SpringSocialConfigurer就相当于继承了SpringSocialConfigurerAdapter,然后就就可以把自己的Filter加入当过滤器链上
 */
public class MySpringSocialConfigurer extends SpringSocialConfigurer {

    //自己定义的过滤路径,Spring Security默认的路径是/auth/qq
    private String filterProcessesUrl;

    public MySpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
