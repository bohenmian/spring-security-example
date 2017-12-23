package cn.edu.swpu.cins.springsecurityexample.generate.impl;

import cn.edu.swpu.cins.springsecurityexample.config.properties.SecurityProperties;
import cn.edu.swpu.cins.springsecurityexample.generate.ValidateCodeGenerator;
import cn.edu.swpu.cins.springsecurityexample.model.service.ValidateCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

@Component("smsCodeGenerator")
public class SmsCodeGeneratorImpl implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) throws IOException {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getImage().getExpireIn());
    }
    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
