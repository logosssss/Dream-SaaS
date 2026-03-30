package com.zhu.saas.common.constant;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型
 *
 * @author qiuchaofan
 * @date 2024年12月17日
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    /**
     * 平台用户
     */
    PLATFORM("PLATFORM", "平台"),
    /**
     * 商户用户
     */
    BUSINESS("BUSINESS", "商户"),
    /**
     * 客户端用户
     */
    CLIENT("CLIENT", "客户"),
    ;

    private final String userType;

    private final String userTypeDesc;

    public static UserTypeEnum getByUserType(String userType) {
        if (StrUtil.isBlank(userType)) {
            return null;
        }

        for (UserTypeEnum userTypeEnum : values()) {
            if (userTypeEnum.getUserType().equals(userType)) {
                return userTypeEnum;
            }
        }

        return null;
    }
}
