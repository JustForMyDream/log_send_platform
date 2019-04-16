package com.weaver.police.util;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/*
 * @Author      :wyl
 * @Date        :2019/4/16  19:05
 * @Version 1.0 :
 * @Description :
 **/
public class RestResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public RestResponse(){
        put("code", 0);
        put("msg", "success");
    }

    public RestResponse(String msg) {
        put("code", 0);
        put("msg", msg);
    }

    public static RestResponse error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static RestResponse error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static RestResponse error(int code, String msg) {
        RestResponse restResponse = new RestResponse();
        restResponse.put("code", code);
        restResponse.put("msg", msg);
        return restResponse;
    }

    public static RestResponse success(String msg) {
        RestResponse restResponse = new RestResponse();
        restResponse.put("msg", msg);
        return restResponse;
    }

    public static RestResponse success(Map<String, Object> map) {
        RestResponse restResponse = new RestResponse();
        restResponse.putAll(map);
        return restResponse;
    }

    public static RestResponse success() {
        return new RestResponse();
    }

    public static RestResponse result(String msg){
        return new RestResponse(msg);
    }

    @Override
    public RestResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
