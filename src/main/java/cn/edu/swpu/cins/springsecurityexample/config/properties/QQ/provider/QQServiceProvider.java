package cn.edu.swpu.cins.springsecurityexample.config.properties.QQ.provider;


import cn.edu.swpu.cins.springsecurityexample.config.properties.QQ.QQService;
import cn.edu.swpu.cins.springsecurityexample.config.properties.QQ.impl.QQServiceImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQService> {

    //将用户导入认证认证服务器(QQ)的URL地址,因为请求的地址不变,所以可以不做成可配置的
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    //携带授权码向认证服务器申请AccessToken的地址
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    //QQ分发的appId
    private String appId;

    public QQServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public QQService getApi(String accessToken) {
        return new QQServiceImpl(accessToken, appId);
    }
}
