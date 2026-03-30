package com.zhu.core.user.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: FeignConfiguration
 * @package: com.zhu.api.user.config
 * @description:
 * @datetime: 2023/9/1   16:12
 * @projectName: IntelliJ IDEA
 * @author: 朱殿辉
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public UserClientFallbackFactory userClientFallbackFactory(){
        return new UserClientFallbackFactory();
    }
}
