package com.zhu.message.rocket.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:    RocketMQConfig
 * Package:    com.jp.platform.saas.user.config
 * Description:
 * Datetime:    2025/3/28   15:49
 * Author:   朱殿辉
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMQConfig {
    private String nameServer;
}
