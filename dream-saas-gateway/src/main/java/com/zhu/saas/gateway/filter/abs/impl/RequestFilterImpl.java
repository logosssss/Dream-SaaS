package com.zhu.saas.gateway.filter.abs.impl;

import cn.hutool.core.date.DateUtil;
import com.zhu.saas.common.constant.Response;
import com.zhu.saas.gateway.constant.Constants;
import com.zhu.saas.gateway.filter.abs.AbstractRequestLogFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * @className: RequestFilterImpl
 * @package: com.zhu.gateway.filter.abs.impl
 * @description: 请求日志处理逻辑
 * @projectName: IntelliJ IDEA
 * @author: 朱殿辉
 */
@Slf4j
@Component
public class RequestFilterImpl extends AbstractRequestLogFilter {

    /**
     * 打印请求日志
     * @param exchange
     * @return
     */
    @Override
    protected Response<ServerWebExchange> handle(ServerWebExchange exchange) {
        String logId = UUID.randomUUID().toString();
        String path = getURL(exchange);
        InetSocketAddress address = getAddress(exchange);
        String method = getMethod(exchange).name();
        String requestDate = DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss");
        log.info("traceId：{}，path：{}，address：{}，method：{}，requestDate：{}", logId,path,address,method,requestDate);
        return new Response<>(setTraceId(exchange, logId));
    }

    /**
     * 设置链路id：往下传递（增加Traceid 方便排查日志）
     * @param exchange
     * @param logId
     * @return
     */
    private ServerWebExchange setTraceId(ServerWebExchange exchange, String logId) {
        try {
            // 获取当前请求
            ServerHttpRequest request = exchange.getRequest();

            // 创建一个新的请求，添加自定义请求头
            ServerHttpRequest mutatedRequest = request.mutate()
                    .header(Constants.TRACEID, logId)
                    .build();
            // 使用新的请求替换原来的请求
           return exchange.mutate().request(mutatedRequest).build();
        } catch (Exception e) {
            log.error("设置traceId错误", e);
        }
        return exchange;
    }
}
