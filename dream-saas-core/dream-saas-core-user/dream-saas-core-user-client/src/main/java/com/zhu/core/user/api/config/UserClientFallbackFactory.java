package com.zhu.core.user.api.config;

import com.zhu.core.user.api.user.UserClientApi;
import com.zhu.saas.common.constant.ApiConstant;
import com.zhu.saas.common.constant.ErrorCode;
import com.zhu.saas.common.constant.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @className: UserClientFallbackFactory
 * @package: com.zhu.api.user.hystrix
 * @description:
 * @datetime: 2023/9/1   16:13
 * @projectName: IntelliJ IDEA
 * @author: 朱殿辉
 */

@Slf4j
@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClientApi> {
    @Override
    public UserClientApi create(Throwable cause) {
        log.error("【{}服务不可用】", ApiConstant.USER_SERVICE_APP_NAME);
        return () -> new Response(ErrorCode.CONSOLE_SERVICE_ERROR,ErrorCode.CONSOLE_SERVICE_ERROR);
    }
}
