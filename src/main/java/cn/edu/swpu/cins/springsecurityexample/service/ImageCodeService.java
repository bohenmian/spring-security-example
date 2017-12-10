package cn.edu.swpu.cins.springsecurityexample.service;

import cn.edu.swpu.cins.springsecurityexample.model.service.ImageCode;

import javax.servlet.http.HttpServletRequest;

public interface ImageCodeService {

    ImageCode createImageCode(HttpServletRequest request);
}
