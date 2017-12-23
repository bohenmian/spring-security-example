package cn.edu.swpu.cins.springsecurityexample.config;

import cn.edu.swpu.cins.springsecurityexample.config.properties.SecurityProperties;
import cn.edu.swpu.cins.springsecurityexample.generate.ValidateCodeGenerator;
import cn.edu.swpu.cins.springsecurityexample.generate.impl.ImageCodeGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGeneratorImpl")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGeneratorImpl codeGenerator = new ImageCodeGeneratorImpl();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

}
