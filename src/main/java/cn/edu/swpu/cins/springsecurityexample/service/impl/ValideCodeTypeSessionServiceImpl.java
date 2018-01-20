package cn.edu.swpu.cins.springsecurityexample.service.impl;

import cn.edu.swpu.cins.springsecurityexample.enums.ValidateCodeType;
import cn.edu.swpu.cins.springsecurityexample.model.service.ValidateCode;
import cn.edu.swpu.cins.springsecurityexample.service.ValidateCodeService;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public class ValideCodeTypeSessionServiceImpl implements ValidateCodeService {

    private String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(request, validateCodeType), code);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(request, validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request, codeType));
    }

    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }
}


