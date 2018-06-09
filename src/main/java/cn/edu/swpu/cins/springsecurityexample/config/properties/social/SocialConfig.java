package cn.edu.swpu.cins.springsecurityexample.config.properties.social;

import cn.edu.swpu.cins.springsecurityexample.config.properties.SecurityProperties;
import cn.edu.swpu.cins.springsecurityexample.config.properties.social.QQ.configurer.MySpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    private DataSource dataSource;
    private SecurityProperties securityProperties;

    @Autowired
    public SocialConfig(DataSource dataSource, SecurityProperties securityProperties) {
        this.dataSource = dataSource;
        this.securityProperties = securityProperties;
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }

    @Bean
    public SpringSocialConfigurer springSocialConfigurerBean() {
        //传入自己定义的拦截路径
        //TODO QQ的回调地址默认为80端口,需要将应用的端口改为80
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        return new MySpringSocialConfigurer(filterProcessesUrl);
    }
}
