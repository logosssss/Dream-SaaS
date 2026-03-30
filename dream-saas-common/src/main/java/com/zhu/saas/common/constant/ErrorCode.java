package com.zhu.saas.common.constant;

import lombok.Getter;

/**
 * ClassName:    ErrorCode
 * Package:    com.zhu.saas.common.constant
 * Description:
 * Datetime:    2026/3/18   15:55
 * Author:   朱殿辉
 */
@Getter
public enum ErrorCode {
    SUCCESS(0, "操作成功!"),
    TOKEN_FAIL(1, "token失效"),
    LOGIN_PASSWORD_ERROR(2, "登录密码错误"),
    FAIL(3, "操作失败!"),
    PARAM_IS_NULL(4, "参数为空"),
    STATE_ERROR(5, "当前数据状态出错,请刷新重试"),
    REQ_ERROR(400, "请求错误!"),
    USER_STOP(801, "该用户已停用，请联系管理员！"),
    SERVER_ERROR(499, "服务维护中，请稍后再试"),
    CONSOLE_SERVICE_ERROR(488, "对应服务异常"),
    SERVICE_ERROR(500, "网络异常，请联系管理员"),
    OBJECT_COPY_ERROR(501, "对象复制错误!"),
    AUTH_FAIL(401, "授权失败或超时,请登录访问!"),
    RESULT_IS_NULL(502, "数据不存在"),
    ORIGINAL_PASSWORD_ERROR(503, "原始密码错误"),
    USER_NAME_EXISTS(504, "用户名已存在"),
    TIMEOUT(408, "请求超时!"),
    REQ_PARAMS_ERROR(406, "请求参数错误!"),
    REQ_PARAMS_NULL(407, "请求参数为空!"),
    TP_CLINET_IS_NULL(408, "第三方客户端为空"),
    ONE_LOGIN(420, "您已经在另一台手机登录"),
    SENS_WORD(421, "提交的数据中包含敏感字符，请重试！"),
    NO_AUTH_REQUEST(422, "无效请求,校验错误"),
    SMS_NOT_EXIST_OR_EXPIRE(423, "短信验证码不存在或已过期"),
    SMS_CODE_INVALIDE(424, "短信验证码不正确"),
    SERVICE_STOP(800, "该服务已暂时停用！"),
    SERVER_EXCEPTION(801, "服务异常");

    private Integer code;
    private String message;

    private ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ErrorCode(Integer code) {
        this.code = code;
    }

    public static String getMsg(int code) {
        for(ErrorCode errorCode : values()) {
            if (code == errorCode.getCode()) {
                return errorCode.getMessage();
            }
        }

        return null;
    }

    public static ErrorCode getSysError(int code) {
        for(ErrorCode errorCode : values()) {
            if (code == errorCode.getCode()) {
                return errorCode;
            }
        }

        return SERVICE_ERROR;
    }
}
