package cn.edu.swpu.cins.springsecurityexample.generate;

import cn.edu.swpu.cins.springsecurityexample.model.service.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request) throws IOException;
}
