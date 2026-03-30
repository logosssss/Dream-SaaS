package com.zhu.bff.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 朱殿辉
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"com.zhu"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);
        app.run(args);
    }
}
