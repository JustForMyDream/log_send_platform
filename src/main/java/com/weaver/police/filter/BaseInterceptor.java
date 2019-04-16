package com.weaver.police.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @Author      :wyl
 * @Date        :2019/4/16  18:29
 * @Version 1.0 :
 * @Description :
 **/

@Component
public class BaseInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        response.addHeader("Access-Control-Allow-Origin", "*");
        //允许的 方法名
        response.addHeader("Access-Control-Allow-Methods", "*");
        //允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type
        response.addHeader("Access-Control-Allow-Headers", "*");
        //预检验请求时间
        response.addHeader("Access-Control-Max-Age", "3600");//30 min
        response.addHeader("Access-Control-Allow-Credentials", "false");

        return true;
    }
}
