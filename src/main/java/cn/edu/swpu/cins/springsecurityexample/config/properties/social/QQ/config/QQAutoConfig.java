package cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.config;

import cn.edu.swpu.cins.springsecurityexample.config.properties.SecurityProperties;
import cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.QQConnectionFactory;
import cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.properties.QQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "demo.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
    }

}
