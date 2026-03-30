package com.zhu.core.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 朱殿辉
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"com.zhu"})
@MapperScan({"com.zhu.core.user.mapper"})
@EntityScan(value = "com.zhu.core.user.bean.eneity")
public class UserCoreApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserCoreApplication.class);
        app.run(args);
    }
}
