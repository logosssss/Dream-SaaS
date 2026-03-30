//package com.zhu.saas.gateway.filter.abs;
//
//import com.zhu.saas.common.constant.Response;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.validation.BindException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//import java.util.*;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    public GlobalExceptionHandler() {
//    }
//
//    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
//    @ResponseBody
//    public Response handlerRequestParams(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        return Response.error(400);
//    }
//
//    @ExceptionHandler({ServletException.class})
//    @ResponseBody
//    public Response ServletException(ServletException ex, HttpServletRequest request) {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        return Response.error(400);
//    }
//
//    @ExceptionHandler({SysException.class})
//    @ResponseBody
//    public Response SysException(SysException ex, HttpServletRequest request) {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        return new Response(ex.getError().getCode(), ex.getError().getMessage());
//    }
//
//    @ExceptionHandler({RequestTimeOutException.class})
//    @ResponseBody
//    public Response RequestTimeOutException(RequestTimeOutException ex, HttpServletRequest request) {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        return Response.error(408);
//    }
//
//    @ExceptionHandler({ParamsException.class})
//    @ResponseBody
//    public Response ParamsException(ParamsException ex, HttpServletRequest request) {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        return new Response(406, ex.getData());
//    }
//
//    @ExceptionHandler({BaseException.class})
//    @ResponseBody
//    public Response BaseException(HttpServletRequest request, BaseException ex) {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        return new Response(ex.getError().getCode(), ex.getCur_msg());
//    }
//
//    @ExceptionHandler({ServiceFeignException.class})
//    @ResponseBody
//    public Response serviceFeignException(HttpServletRequest request, ServiceFeignException ex) {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        return new Response(ex.getError(), ex.getMessage());
//    }
//
//    @ExceptionHandler({RedisCommandTimeoutException.class})
//    @ResponseBody
//    public Response handleRedisCommandTimeoutException(HttpServletRequest request, Exception ex) {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        return new Response(500);
//    }
//
//    @ExceptionHandler({Exception.class})
//    @ResponseBody
//    public Response handleGlobal(HttpServletRequest request, Exception ex) {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        return StringUtils.isBlank(ex.getMessage()) ? new Response(500) : new Response(500);
//    }
//
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    @ResponseBody
//    public Response MethodArgumentNotValidHandler(MethodArgumentNotValidException ex, HttpServletRequest request) throws Exception {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        BindingResult result = ex.getBindingResult();
//        return this.bindParamValid(result);
//    }
//
//    @ExceptionHandler({BindException.class})
//    @ResponseBody
//    public Response BindException(BindException ex, HttpServletRequest request) throws Exception {
//        CustomLogger.printErrorLog("访问异常", this.requestParams(request), ex);
//        BindingResult result = ex.getBindingResult();
//        return this.bindParamValid(result);
//    }
//
//    private Response bindParamValid(BindingResult result) {
//        List<Object> invalidArguments = new ArrayList();
//        Response response = new Response(406);
//        if (result.hasErrors()) {
//            Iterator var4 = result.getFieldErrors().iterator();
//            if (var4.hasNext()) {
//                FieldError error = (FieldError)var4.next();
//                Map<String, String> map = new HashMap();
//                map.put("filed", error.getField());
//                map.put("errorMsg", error.getDefaultMessage());
//                invalidArguments.add(map);
//                response.message = error.getDefaultMessage();
//                return response;
//            }
//        }
//
//        return response;
//    }
//
//    public Map requestParams(HttpServletRequest request) {
//        HashMap<String, Object> paramsMap = new HashMap();
//        Enumeration params = request.getParameterNames();
//
//        while(params.hasMoreElements()) {
//            String param = (String)params.nextElement();
//            paramsMap.put(param, request.getParameter(param));
//        }
//
//        return paramsMap;
//    }
//
//}
