package cn.edu.swpu.cins.springsecurityexample.config.properties.social;

import cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.properties.QQProperties;

public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
