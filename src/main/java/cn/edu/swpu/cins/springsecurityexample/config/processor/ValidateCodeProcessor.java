package cn.edu.swpu.cins.springsecurityexample.config.processor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    void create(ServletWebRequest request) throws Exception;
}
