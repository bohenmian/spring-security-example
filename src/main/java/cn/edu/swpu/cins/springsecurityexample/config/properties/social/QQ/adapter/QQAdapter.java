package cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.adapter;

import cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.QQService;
import cn.edu.swpu.cins.springsecurityexample.model.service.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

public class QQAdapter implements ApiAdapter<QQService> {

    @Override
    public boolean test(QQService api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQService api, ConnectionValues values) {
        try {
            QQUserInfo userInfo = api.getUserInfo();
            values.setDisplayName(userInfo.getNickname());
            values.setImageUrl(userInfo.getFigureurl_qq_1());
            values.setProfileUrl(null);
            values.setProviderUserId(userInfo.getOpenId());
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败");
        }
    }

    @Override
    public UserProfile fetchUserProfile(QQService api) {
        return null;
    }

    @Override
    public void updateStatus(QQService api, String message) {

    }
}
