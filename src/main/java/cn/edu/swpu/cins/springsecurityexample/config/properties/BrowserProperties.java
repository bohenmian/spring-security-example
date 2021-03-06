package cn.edu.swpu.cins.springsecurityexample.config.properties;

import cn.edu.swpu.cins.springsecurityexample.enums.LoginType;

public class BrowserProperties {

    private String loginPage = "/signIn.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMe = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(int rememberMe) {
        this.rememberMe = rememberMe;
    }
}
