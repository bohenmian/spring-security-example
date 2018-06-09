package cn.edu.swpu.cins.springsecurityexample.config.processor.impl;

import cn.edu.swpu.cins.springsecurityexample.config.processor.ValidateCodeProcessor;
import cn.edu.swpu.cins.springsecurityexample.enums.ValidateCodeType;
import cn.edu.swpu.cins.springsecurityexample.exception.ValidateCodeException;
import cn.edu.swpu.cins.springsecurityexample.generate.ValidateCodeGenerator;
import cn.edu.swpu.cins.springsecurityexample.model.service.ValidateCode;
import cn.edu.swpu.cins.springsecurityexample.service.ValidateCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    //依赖查找,spring容器启动时会去收集ValidateCodeGenerator的实现
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) throws IOException {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(type + "CodeGenerator");
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }

    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }

    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeService.save(request, code, getValidateCodeType(request));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType codeType = getValidateCodeType(request);

        C codeInSession = (C) validateCodeService.get(request, codeType);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(codeType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            validateCodeService.remove(request, codeType);
            throw new ValidateCodeException(codeType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码不匹配");
        }

        validateCodeService.remove(request, codeType);
    }
}
