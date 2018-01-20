package cn.edu.swpu.cins.springsecurityexample.service;

import cn.edu.swpu.cins.springsecurityexample.enums.ValidateCodeType;
import cn.edu.swpu.cins.springsecurityexample.model.service.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeService {

    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    void remove(ServletWebRequest request, ValidateCodeType codeType);
}
