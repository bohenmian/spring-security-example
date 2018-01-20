package cn.edu.swpu.cins.springsecurityexample.config.processor.inner;

import cn.edu.swpu.cins.springsecurityexample.config.processor.impl.AbstractValidateCodeProcessor;
import cn.edu.swpu.cins.springsecurityexample.generate.SmsCodeSenderGenerator;
import cn.edu.swpu.cins.springsecurityexample.model.service.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSenderGenerator sender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws ServletRequestBindingException {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        sender.send(mobile, validateCode.getCode());
    }
}
