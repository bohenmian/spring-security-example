package cn.edu.swpu.cins.springsecurityexample.config.properties;

import cn.edu.swpu.cins.springsecurityexample.config.properties.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "demo.security")
public class SecurityProperties {

    private BrowserProperties browserProperties = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

    public BrowserProperties getBrowserProperties() {
        return browserProperties;
    }

    public void setBrowserProperties(BrowserProperties browserProperties) {
        this.browserProperties = browserProperties;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
