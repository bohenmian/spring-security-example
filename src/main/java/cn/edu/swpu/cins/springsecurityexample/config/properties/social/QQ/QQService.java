package cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ;

import cn.edu.swpu.cins.springsecurityexample.model.service.QQUserInfo;

import java.io.IOException;

//API
public interface QQService {

    QQUserInfo getUserInfo() throws IOException;
}
