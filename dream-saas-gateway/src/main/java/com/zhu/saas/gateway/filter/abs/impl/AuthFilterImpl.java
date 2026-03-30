package com.zhu.saas.gateway.filter.abs.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.zhu.saas.cache.redis.componet.RedisUtil;
import com.zhu.saas.common.constant.ErrorCode;
import com.zhu.saas.common.constant.Response;
import com.zhu.saas.common.constant.UserTypeEnum;
import com.zhu.saas.common.constant.redis.RedisKey;
import com.zhu.saas.gateway.config.properties.UserFilterUrlConfig;
import com.zhu.saas.gateway.constant.Constants;
import com.zhu.saas.gateway.constant.UserInfo;
import com.zhu.saas.gateway.filter.abs.AbstractAuthFilter;
import com.zhu.saas.gateway.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

/**
 * @className: AuthFilterImpl
 * @package: com.zhu.gateway.filter.abs.impl
 * @description: 鉴权过滤器实现类
 * @projectName: IntelliJ IDEA
 * @author: 朱殿辉
 */
@Slf4j
@RefreshScope
@Component
public class AuthFilterImpl extends AbstractAuthFilter {

    @Autowired
    private UserFilterUrlConfig userFilterUrlConfig;

    @Autowired
    private RedisUtil redisUtil;

    private static final String TOKEN_PREFIX = "ST-";

    @Override
    protected Response<ServerWebExchange> handle(ServerWebExchange exchange) {
        String path = this.getURL(exchange);
        // 无需验证的路径直接放行
        if (isNoLoginPath(path)) {
            return new Response<>(exchange);
        }
        if (isPossibleLoginPath(path)) {
            return handlePossibleLogin(path, exchange);
        }
        return handleLogin(path, exchange);
    }

    private boolean isNoLoginPath(String path) {
        // 使用Stream API检查路径是否在免登录列表中
        return userFilterUrlConfig.getNoLoginUrls().stream().anyMatch(s -> s.equals(path));
    }

    private boolean isPossibleLoginPath(String path) {
        // 使用Stream API检查路径是否在免登录列表中
        return userFilterUrlConfig.getPossibleLoginUrls().stream().anyMatch(s -> s.equals(path));
    }

    private Response handlePossibleLogin(String path, ServerWebExchange exchange) {
        try {
            String token = getToken(exchange);
            if (StrUtil.isBlank(token)) {
                return new Response<>(exchange);
            }
            if (!TokenUtil.verifyToken(token)) {
                return new Response<>(exchange);
            }
            String userInfoStr = this.getUserInfoByToken(token);
            if (ObjectUtil.isNull(userInfoStr)) {
                return new Response<>(exchange);
            }
            UserInfo userInfo = JSONUtil.toBean(userInfoStr, UserInfo.class);
            if (!checkUserPerm(path, userInfo)) {
                return Response.error(ErrorCode.AUTH_FAIL,"暂无访问权限");
            }
            // 设置用户信息到请求头
            this.setUser(exchange, userInfo);
            return new Response<>(exchange);
        } catch (Exception e) {
            log.error("Token解析异常", e);
            return new Response<>(exchange);
        }
    }

    private Response handleLogin(String path, ServerWebExchange exchange) {
        //校验是否携带token
        String token = getToken(exchange);
        if (StrUtil.isBlank(token)) {
            return Response.error(ErrorCode.TOKEN_FAIL, "token 不能为空");
        }
        //往下传递用户信息
        try {
            // 验证Token
            if (!TokenUtil.verifyToken(token)) {
                return Response.error(ErrorCode.TOKEN_FAIL, "token已失效，请重新登录");
            }
            String userInfoStr = this.getUserInfoByToken(token);
            if (ObjectUtil.isNull(userInfoStr)) {
                return Response.error(ErrorCode.TOKEN_FAIL, "用户信息已失效，请重新登录");
            }
            UserInfo userInfo = JSONUtil.toBean(userInfoStr, UserInfo.class);
            if (!checkUserPerm(path, userInfo)) {
                return Response.error(ErrorCode.AUTH_FAIL,"暂无访问权限");
            }
            // 设置用户信息到请求头
            this.setUser(exchange, userInfo);
        } catch (Exception e) {
            log.error("Token解析异常", e);
            return Response.error(ErrorCode.TOKEN_FAIL, "请登录后操作");
        }
        return new Response<>(exchange);
    }

    /**
     * 设置用户信息
     *
     * @param exchange
     * @param userInfo
     */
    private ServerWebExchange setUser(ServerWebExchange exchange, UserInfo userInfo) {
        // 获取当前请求
        ServerHttpRequest request = exchange.getRequest();
        // 创建一个新的请求，添加自定义请求头
        ServerHttpRequest mutatedRequest = request.mutate().header(Constants.USER_INFO_HEAD, JSONUtil.toJsonStr(userInfo))
                .build();
        return exchange.mutate().request(mutatedRequest).build();
    }

    /**
     * @param path
     * @param userInfo
     * @return
     */
    private boolean checkUserPerm(String path, UserInfo userInfo) {
        //获取用户角色
        UserTypeEnum userTypeEnum = userInfo.getRole();
        String userId = userInfo.getUserId();
        if (ObjectUtil.isNull(userTypeEnum)) {
            return false;
        }
        switch (userTypeEnum) {
            case BUSINESS:
                //预发管理员[ybw1]
                if("ADUSER2025011601009".equals(userId)) return true;
                //管理账户放行[admin123]
                if("ADUSER20250508000001".equals(userId)) return true;
                //管理账户放行[commonAdmin]
                if("ADUSER20250611000001".equals(userId)) return true;
                //zhudianhui
//                if("ADUSER2025031001009".equals(userId)) return true;
                return checkBusinessUserPerm(path, userId);
            case PLATFORM:
                return checkPlatformUserPerm(path);
            case CLIENT:
                return checkClientUserPerm(path);
            default:
                return false;
        }
    }

    /**
     * @param path 请求路径
     * @param path 请求路径
     * @desc 商户端权限校验
     */
    private boolean checkBusinessUserPerm(String path, String userId) {

        //1:请求路径是否为服务端的
        boolean isServerUrl = path.startsWith(Constants.SERVER_URL_PRE);
        if (!isServerUrl) return false;

        String perm = (String) redisUtil.get(String.format(RedisKey.PRE_PERMISSION, userId));
        if (StrUtil.isBlank(perm)) return false;

        List<String> permList = JSON.parseArray(perm, String.class);
        return permList.contains(path);
    }

    /**
     * 判断是否为服务端接口
     *
     * @param path 请求路径
     * @return 是否为服务端接口
     */
    private boolean checkPlatformUserPerm(String path) {
        // 示例：通过路径前缀来判断是否为内部接口
        return path.startsWith(Constants.PLATFORM_URL_PRE);
    }

    /**
     * 判断是否为客户端接口
     *
     * @param path 请求路径
     * @return 是否为客户端接口
     */
    private boolean checkClientUserPerm(String path) {
        // 示例：通过路径前缀来判断是否为内部接口
        return path.startsWith(Constants.CLIENT_URL_PRE);
    }

    private String getUserInfoByToken(String token){
        if(StrUtil.isBlank(token)){
            return null;
        }
        //获取当前登录的用户Id
        String userId = TokenUtil.getInfoFromToken(token);
        String tokenKey = TOKEN_PREFIX + userId;
        Object val =  redisUtil.get(tokenKey);
        String redisToken = (String) val;
        if (StrUtil.isNotBlank(redisToken) && redisToken.equals(token)) {
            String userInfoKey = String.format(RedisKey.USER_INFO, userId);
            return (String) redisUtil.get(userInfoKey);
        }
        return null;
    }
}
