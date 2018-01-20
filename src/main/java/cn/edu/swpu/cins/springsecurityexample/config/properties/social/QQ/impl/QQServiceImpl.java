package cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.impl;

import cn.edu.swpu.cins.springsecurityexample.model.service.QQUserInfo;
import cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.QQService;
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

    //String转换为对象
    private ObjectMapper objectMapper = new ObjectMapper();

    public QQServiceImpl(String accessToken, String appId) {
        //会自动将AccessToken作为查询参数
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        //对openId的路径进行拼接
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() throws IOException {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败");
        }
    }
}
