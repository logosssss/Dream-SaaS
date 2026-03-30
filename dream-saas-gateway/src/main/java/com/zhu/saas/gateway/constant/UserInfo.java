package com.zhu.saas.gateway.constant;

import com.zhu.saas.common.constant.UserTypeEnum;
import lombok.Data;

/**
 * ClassName:    UserInfo
 * Package:    com.jp.platform.saas.common.constants
 * Description:
 * Datetime:    2025/2/8   16:21
 * Author:   朱殿辉
 */
@Data
public class UserInfo {

    /** 用户id*/
    private String userId;

    /** 用户角色*/
    private UserTypeEnum role;

    /** 渠道编码*/
    private String businessCode;

    /** 主账户:MASTER_ACCOUNT,子账户:SUB_ACCOUNT*/
    private String accountType;
}
