package com.zhu.saas.gateway.filter.abs.global;

import com.alibaba.fastjson2.JSONObject;
import com.zhu.saas.common.constant.Response;
import com.zhu.saas.gateway.constant.Constants;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @className: GlobalFilterAbs
 * @package: com.zhu.gateway.filter.abs
 * @description:    全局过滤处理抽象类
 * @author: 朱殿辉
 */
public abstract class AbstractGlobalFilter {

    /**
     * 响应JSON数据
     * @param responseBase
     * @param exchange
     * @return
     */
    public Mono<Void> responseJson(Response responseBase, ServerWebExchange exchange){
        ServerHttpResponse response = exchange.getResponse();
        String body = JSONObject.toJSONString(responseBase);
        //设置头部信息
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        httpHeaders.add(HttpHeaders.CACHE_CONTROL, "no-store,no-cache,must-revalidate,max-age=0");
        //设置body
        DataBuffer dataBuffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * 具体处理逻辑
     * 默认不做任何处理
     * @param exchange
     * @return
     */
    protected Response<ServerWebExchange> handle(ServerWebExchange exchange){
        return new Response<>(exchange);
    }

    /**
     * 获取请求方法
     * @param exchange
     * @return
     */
    public HttpMethod getMethod(ServerWebExchange exchange){
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        return Objects.requireNonNull(serverHttpRequest.getMethod());

    }

    /**
     * 获取请求路径
     * @param exchange
     * @return
     */
    public String getURL(ServerWebExchange exchange){
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        return Objects.requireNonNull(serverHttpRequest.getURI().getPath());
    }

    /**
     * 获取请求地址
     * @param exchange
     * @return
     */
    public InetSocketAddress getAddress(ServerWebExchange exchange){
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        return Objects.requireNonNull(serverHttpRequest.getRemoteAddress());
    }

    /**
     * 获取token
     * @param exchange
     * @return
     */
    public String getToken(ServerWebExchange exchange){
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        return serverHttpRequest.getHeaders().getFirst(Constants.AUTHORIZATION_HEAD);
    }
}
