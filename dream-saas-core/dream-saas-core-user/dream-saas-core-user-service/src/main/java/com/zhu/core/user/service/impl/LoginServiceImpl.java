package com.zhu.core.user.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.zhu.core.user.bean.eneity.User;
import com.zhu.core.user.bean.request.LoginRequest;
import com.zhu.core.user.mapper.UserMapper;
import com.zhu.core.user.service.LoginService;
import com.zhu.saas.common.constant.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * service业务处理层
 *
 * @author code_generator
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
}
