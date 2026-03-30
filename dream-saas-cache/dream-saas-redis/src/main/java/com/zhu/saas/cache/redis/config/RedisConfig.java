//package com.zhu.saas.cache.redis.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.zhu.saas.cache.redis.componet.RedisUtil;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// *
// * @ClassName:  RedisConfig
// * @Description: redis模板
// * @author: zhuMS
// * @date:   2019年6月27日 下午3:27:21
// *
// * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
// */
//@Configuration
//@EnableCaching
//public class RedisConfig {
//    @Bean
//    public RedisUtil redisUtil() {
//        return new RedisUtil();  // 如果 RedisUtil 依赖其他 Bean（如 RedisTemplate），可以通过方法参数注入
//    }
//}
