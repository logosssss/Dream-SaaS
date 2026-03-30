package com.zhu.saas.common.constant.redis;

/**
 * ClassName:    RedisKey
 * Package:    com.jp.platform.saas.common.constants.redis
 * Description:
 * Datetime:    2025/2/20   19:59
 * Author:   朱殿辉
 */
public class RedisKey {
    /** 5分钟*/
    public final static Long EXPIRE_5_MINUTE = 300L;

    /** 1小时到期时间*/
    public final static Long EXPIRE_HOUR = 3600L;

    /** 一天到期时间*/
    public final static Long EXPIRE_DAY = 86400L;

    /** 一周*/
    public final static Long EXPIRE_WEEK=  7 * 24 * 60 * 60L;

    /** 系统*/
    public static final String PLATFORM_SAAS = "platform_saas:";

    /** 用户服务*/
    public static final String USER = PLATFORM_SAAS + "user:";

    /** admin服务*/
    public static final String ADMIN = PLATFORM_SAAS + "admin:";

    /** 商品服务*/
    public static final String PRODUCT = PLATFORM_SAAS + "product:";

    /** 表单服务*/
    public static final String WORKFORM = PLATFORM_SAAS + "workform:";

    public static final String BUSINESS = PLATFORM_SAAS + "business:";

    public static final String ROCKET_MQ = PLATFORM_SAAS + "rocket:";

    /** 用户信息*/
    public static final String USER_INFO = USER + "user_info:%s";

    public static final String COUNTRY = PLATFORM_SAAS + "country:";

    /** 幂等处理：必须用业务id不能用消息id*/
    /** topic key topic:topicName:businessId*/
    public static final String TOPIC_KEY = ROCKET_MQ + "topic:%s:%s";

    /** 幂等处理：必须用业务id不能用消息id*/
    /** topic key topic:topicName:tag:businessId*/
    public static final String TOPIC_TAG_KEY = ROCKET_MQ + "topic:%S:%s:%s";

    /** 用户权限*/
    public static final String PRE_PERMISSION = "PERMISSION_BUSINESS:%s";

    /** 字典*/
    public static final String DICTIONARY_KEY = PLATFORM_SAAS + "dictionary:%s";

    public static final String DICTIONARY_CODE_KEY = PLATFORM_SAAS + "dictionaryCode:%s_%s";

    public static final String DICTIONARY_KEY_MAP = PLATFORM_SAAS + "dictionaryMap:%s";

    /** 显示配置：业务类型:用户id*/
    public static final String DISPLAY_USER_KEY = PLATFORM_SAAS + "displayUser:%s:%s";
    /** 显示配置：业务类型*/
    public static final String DISPLAY_KEY = PLATFORM_SAAS + "display:%s";

    /**
     * 标准key
     */
    public final static String REP_STANDARD = "REP_STANDARD:";
    public final static String TM_SAVE_CASE = "TM_SAVE_CASE:";

    public final static String ADMIN_ACCOUNT = "Admin_account:";

    public final static String VAT_GET_DECLARE_RECEIPT_KEY = "GET_DECLARE_RECEIPT:";

    public final static String VAT_GET_DECLARE_RECEIPT_ALL_KEY = "VAT_GET_DECLARE_RECEIPT_ALL_KEY:";

    public static final String PRODUCT_TYPE_NAME_KEY = PRODUCT + "productTypeName";

    /** 组件：详情：组件id*/
    public static final String COMPONENT_DETAIL = WORKFORM + "componentDetail:%s";

    /** 组件：所有*/
    public static final String COMPONENT_ALL = WORKFORM + "componentAll";

    /** 表单详情：code*/
    public static final String PROCESS_DETAIL = WORKFORM + "processDetail:%s";

    public static final String COUNTRY_SORT_LIST_KEY = COUNTRY + "COUNTRY_SORT_LIST_KEY";

    /** 国家:cn:中文名称*/
    public static final String COUNTRY_CN_NAME = COUNTRY + "cn:%s";

    public static final String BUSINESS_NAME_MAP_KEY = BUSINESS +"nameMap";


}
