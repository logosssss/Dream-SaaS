package com.zhu.bff.user.controller;


import com.zhu.core.user.api.user.UserClientApi;
import com.zhu.saas.common.constant.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ybw
 * @date 2025年2月21日
 */
@RestController
@RequestMapping("/client/user/login")
@Slf4j
public class LoginController {

    @Autowired
    private UserClientApi userClientApi;

    @PostMapping("/getLoginCode")
    public Response loginCode() {
        log.info("SUCCESS");
        return userClientApi.feignTest();
    }
}
