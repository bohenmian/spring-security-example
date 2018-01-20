package cn.edu.swpu.cins.springsecurityexample.config.processor.inner;

import cn.edu.swpu.cins.springsecurityexample.config.processor.impl.AbstractValidateCodeProcessor;
import cn.edu.swpu.cins.springsecurityexample.model.service.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
