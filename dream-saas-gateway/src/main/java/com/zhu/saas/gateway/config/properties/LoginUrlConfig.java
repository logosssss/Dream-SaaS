package com.zhu.saas.gateway.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName:    LoginUrlConfig
 * Package:    com.zhu.gateway.config
 * Description:
 * Author:   朱殿辉
 */
@Component
@ConfigurationProperties(prefix = "dream.gateway")
public class LoginUrlConfig {
    List<String> loginUrls;

    public List<String> getLoginUrls() {
        return loginUrls;
    }

    public void setLoginUrls(List<String> loginUrls) {
        this.loginUrls = loginUrls;
    }
}
