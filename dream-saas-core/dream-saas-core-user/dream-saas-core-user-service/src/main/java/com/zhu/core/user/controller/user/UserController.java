package com.zhu.core.user.controller.user;

import com.zhu.saas.common.constant.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:    UserController
 * Package:    com.zhu.core.user.controller.user
 * Description:
 * Datetime:    2026/3/25   11:44
 * Author:   朱殿辉
 */
@RestController
@RequestMapping(value = "/client/user/")
@Slf4j
public class UserController {
    /**
     * feign 测试方法
     * @return
     */
    @RequestMapping("/feignTest")
    Response feignTest(){
        log.info("feignTest");
        return new Response();
    }

    @PostMapping()
    public Response login(@RequestBody LoginRequest loginRequest) {
        return iLoginService.login(loginRequest);
    }
}
