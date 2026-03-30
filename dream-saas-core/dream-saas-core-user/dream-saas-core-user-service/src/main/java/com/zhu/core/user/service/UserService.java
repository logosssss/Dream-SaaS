package com.zhu.core.user.service;

import com.zhu.core.user.bean.eneity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhu.core.user.bean.request.LoginRequest;
import com.zhu.saas.common.constant.Response;

/**
 *
 */
public interface UserService extends IService<User> {
    /**
     * 用户登录请求
     *
     * @param loginRequest
     * @return
     */
    Response login(LoginRequest loginRequest);
}
