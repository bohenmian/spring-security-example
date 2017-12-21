package cn.edu.swpu.cins.springsecurityexample.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ValidateCodeService {

    void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
