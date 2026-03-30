package com.zhu.core.user.bean.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @author ybw
 * @date 2025年2月21日
 */
@Data
public class LoginRequest {

    //邮箱、账户
    private String loginName;

    //区号+手机号
    private String regionCode;
    private String phoneNum;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "商户号不能为空")
    private String businessCode;

    //用户ID
    @NotBlank(message = "UserId不能为空")
    private String userId;

    //无需求 预留字段
    private boolean rememberMe = false;


    //验证码【检查账户-短信登入和忘记密码场景需要】
    private String code;
}
