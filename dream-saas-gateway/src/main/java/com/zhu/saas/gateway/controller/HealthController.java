package com.zhu.saas.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Create by: qym
 * @Date: 2021/11/1 14:09
 */
@RestController
@RequestMapping("/gateway")
public class HealthController {

    @RequestMapping("/health/check")
    public String healthCheck(){

        return "SUCCESS";
    }
}
