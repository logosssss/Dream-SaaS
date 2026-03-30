package com.zhu.core.user.api.user;

import com.zhu.saas.common.constant.ApiConstant;
import com.zhu.saas.common.constant.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:    UserApi
 * Package:    com.zhu.core.user.api.user
 * Description:
 * Datetime:    2026/3/25   11:40
 * Author:   朱殿辉
 */
@FeignClient(value = ApiConstant.USER_SERVICE_APP_NAME,path = ApiConstant.USER_CLIENT_API_PATH_PREFIX)
public interface UserClientApi {
    /**
     * feign 测试方法
     * @return
     */
    @RequestMapping("/feignTest")
    Response feignTest();
}
