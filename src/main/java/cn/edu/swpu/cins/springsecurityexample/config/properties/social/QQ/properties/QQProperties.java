package cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
