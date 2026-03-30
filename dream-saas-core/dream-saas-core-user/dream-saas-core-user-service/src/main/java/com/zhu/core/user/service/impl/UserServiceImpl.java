package com.zhu.core.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.core.user.bean.eneity.User;
import com.zhu.core.user.bean.request.LoginRequest;
import com.zhu.core.user.service.UserService;
import com.zhu.core.user.mapper.UserMapper;
import com.zhu.saas.common.constant.Response;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    /**
     * 用户登录请求
     *
     * @param loginRequest
     * @return
     */
    @Override
    public Response login(LoginRequest loginRequest) {
        return null;
    }
}




