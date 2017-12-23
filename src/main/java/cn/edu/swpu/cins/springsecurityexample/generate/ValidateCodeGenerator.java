package cn.edu.swpu.cins.springsecurityexample.generate;

import cn.edu.swpu.cins.springsecurityexample.model.service.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request) throws IOException;
}
