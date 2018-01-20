package cn.edu.swpu.cins.springsecurityexample.config.processor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public interface ValidateCodeProcessor {

    void create(ServletWebRequest request) throws Exception;

    void validate(ServletWebRequest request);
}
