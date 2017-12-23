package cn.edu.swpu.cins.springsecurityexample.config.processor.impl;

import cn.edu.swpu.cins.springsecurityexample.config.processor.ValidateCodeProcessor;
import cn.edu.swpu.cins.springsecurityexample.generate.ValidateCodeGenerator;
import cn.edu.swpu.cins.springsecurityexample.model.service.ValidateCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        send(request, validateCode);
    }

    private C generate(ServletWebRequest request) throws IOException {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(type + "CodeGenerator");
        return (C) validateCodeGenerator.generate(request);
    }

    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }

    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    }

}
