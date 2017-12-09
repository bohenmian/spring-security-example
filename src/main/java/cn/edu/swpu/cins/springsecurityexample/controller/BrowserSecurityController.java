package cn.edu.swpu.cins.springsecurityexample.controller;

import cn.edu.swpu.cins.springsecurityexample.model.http.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(BrowserSecurityController.class);

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Message requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String target = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是：" + target);
            if (StringUtils.endsWithIgnoreCase(target, ".html")) {
                redirectStrategy.sendRedirect(request, response, "");
            }
        }
        return new Message("没有权限");
    }
}
