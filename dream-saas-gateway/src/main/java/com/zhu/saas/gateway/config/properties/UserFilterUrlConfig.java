package com.zhu.saas.gateway.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName:    FilterUrlConfig
 * Package:    com.zhu.gateway.config
 * Description:
 * Author:   朱殿辉
 */
@Component
@ConfigurationProperties(prefix = "dream.user")
public class UserFilterUrlConfig {

    /** 限流路径*/
    private List<String> limitedUrls;
    /** 免登录路径*/
    private List<String> noLoginUrls;
    /** 可能登录地址*/
    private List<String> possibleLoginUrls;

    public List<String> getLimitedUrls() {
        return limitedUrls;
    }

    public void setLimitedUrls(List<String> limitedUrls) {
        this.limitedUrls = limitedUrls;
    }

    public List<String> getNoLoginUrls() {
        return noLoginUrls;
    }

    public void setNoLoginUrls(List<String> noLoginUrls) {
        this.noLoginUrls = noLoginUrls;
    }

    public List<String> getPossibleLoginUrls() {
        return possibleLoginUrls;
    }

    public void setPossibleLoginUrls(List<String> possibleLoginUrls) {
        this.possibleLoginUrls = possibleLoginUrls;
    }
}
