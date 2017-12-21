package cn.edu.swpu.cins.springsecurityexample.config.properties.QQ.impl;

import cn.edu.swpu.cins.springsecurityexample.model.service.QQUserInfo;
import cn.edu.swpu.cins.springsecurityexample.config.properties.QQ.QQService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

public class QQServiceImpl extends AbstractOAuth2ApiBinding implements QQService {

    //请求认证服务器获取OpenId的路径
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    //请求认证服务器获取用户基本信息的路径
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    //Json转换成对象
    private ObjectMapper objectMapper = new ObjectMapper();

    public QQServiceImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        //对OpenId的路径进行拼接
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }


    /**
     * 返回从QQ服务器得到的用户信息
     * @return QQUserInfo
     * @throws IOException
     */
    @Override
    public QQUserInfo getUserInfo() throws IOException {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        return objectMapper.readValue(result, QQUserInfo.class);
    }
}
