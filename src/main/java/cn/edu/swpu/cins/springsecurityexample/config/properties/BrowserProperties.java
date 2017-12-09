package cn.edu.swpu.cins.springsecurityexample.config.properties;

public class BrowserProperties {

    private String loginPage = "/signIn.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
