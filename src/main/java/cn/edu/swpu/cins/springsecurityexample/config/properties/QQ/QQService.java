package cn.edu.swpu.cins.springsecurityexample.config.properties.QQ;

import cn.edu.swpu.cins.springsecurityexample.model.service.QQUserInfo;

import java.io.IOException;

public interface QQService {

    QQUserInfo getUserInfo() throws IOException;
}
