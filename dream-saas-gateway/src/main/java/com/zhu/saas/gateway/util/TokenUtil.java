package com.zhu.saas.gateway.util;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:    TokenUtil
 * Package:    com.zhu.saas.gateway.util
 * Description:
 * Datetime:    2026/3/18   16:20
 * Author:   朱殿辉
 */
public class TokenUtil {
    private static final Logger log = LoggerFactory.getLogger(TokenUtil.class);
    private static final String SECRET = "jianshishangwuKUAJINGYUNJP2016";
    private static final Integer DEFAULT_EXPIRE_OFFSET = 14400;

    public static String createToken(String userId) {
        return createToken(userId, DEFAULT_EXPIRE_OFFSET);
    }

    public static String createToken(String userId, Integer expire) {
        Map<String, Object> map = new HashMap(2);
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create().withHeader(map).withClaim("iss", "Service").withClaim("aud", "Client").withClaim("userId", StrUtil.isBlank(userId) ? null : userId).withIssuedAt(new Date()).withExpiresAt(new Date(System.currentTimeMillis() + (long)(expire * 1000))).sign(Algorithm.HMAC256("jianshishangwuKUAJINGYUNJP2016"));
    }

    public static String createToken(String userId, Integer expire, Integer roleLevel) {
        Map<String, Object> map = new HashMap();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map).withClaim("iss", "Service").withClaim("aud", "Client").withClaim("roleLevel", roleLevel).withClaim("userId", StrUtil.isBlank(userId) ? null : userId).withIssuedAt(new Date()).withExpiresAt(new Date(System.currentTimeMillis() + (long)(expire * 1000))).sign(Algorithm.HMAC256("jianshishangwuKUAJINGYUNJP2016"));
        return token;
    }

    public static boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("jianshishangwuKUAJINGYUNJP2016")).build();
            verifier.verify(token);
            return true;
        } catch (TokenExpiredException var3) {
            String infoFromToken = getInfoFromToken(token);
            log.error("token expired the token{},用户id{}", token, infoFromToken);
            return false;
        } catch (Exception e) {
            log.error(String.format("token verify fail reason{%s},token${%s}", e.getMessage(), token), e);
            return false;
        }
    }

    public static String getInfoFromToken(String token) {
        DecodedJWT decode = JWT.decode(token);
        Claim claim = decode.getClaim("userId");
        return claim.asString();
    }

    public static Integer getRoleLevel(String token) {
        DecodedJWT decode = JWT.decode(token);
        Claim claim = decode.getClaim("roleLevel");
        return claim.asInt();
    }
}
