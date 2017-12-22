package cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ;

import cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.adapter.QQAdapter;
import cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.provider.QQServiceProvider;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQService> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
