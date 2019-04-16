package com.weaver.police.util;

import com.weaver.police.constant.PoliceConstant;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/*
 * @Author      :wyl
 * @Date        :2019/4/16  17:13
 * @Version 1.0 :
 * @Description :
 **/
public class Util {

    public static String null2String(Object s) {
        return s == null ? "" : s.toString();

    }

    public static Long getLong(String s) {
        return getLong(s,0);

    }

    public static Long getLong(String s,long def) {
        try {
            return Long.parseLong(s);
        } catch (Exception ex) {
            return def;
        }
    }


    public static int getInt(String s) {
        return getInt(s,0);

    }

    public static int getInt(String s,int def) {
        try {
            return Integer.parseInt(s);
        } catch (Exception ex) {
            return def;
        }
    }

    public static String getNowDateTimeStr(String time){
        if (StringUtils.isBlank(time)){
            return  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime());
        }
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(Long.parseLong(time)));
    }

    /**
     * 获得4个长度的十六进制的UUID
     * @return
     */
    public static String get4UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[1];
    }
    /**
     * 获得8个长度的十六进制的UUID
     * @return UUID
     */
    public static String get8UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0];
    }
    /**
     * 获得12个长度的十六进制的UUID
     * @return UUID
     */
    public static String get12UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1];
    }
    /**
     * 获得16个长度的十六进制的UUID
     * @return UUID
     */
    public static String get16UUID(){

        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2];
    }
    /**
     * 获得20个长度的十六进制的UUID
     * @return UUID
     */
    public static String get20UUID(){

        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2]+idd[3];
    }
    /**
     * 获得24个长度的十六进制的UUID
     * @return UUID
     */
    public static String get24UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[4];
    }

    public static String get32UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2]+idd[3]+idd[4];
    }

    /**
     * 根据url匹配操作类型
     * @param url
     * @return
     */
    public static short getMethod(String url){
        url = url.toLowerCase();
        if(StringUtils.equals(url,PoliceConstant.USER_LOGIN.toLowerCase())){
            return 0;
        }else if(url.contains("get") || url.contains("query") ){
            return 1;
        }else if(url.contains("save") || url.contains("add") || url.contains("insert")){
            return 2;
        }else if(url.contains("update") || url.contains("change")){
            return 3;
        }else if(url.contains("del") || url.contains("delete") || url.contains("remove")){
            return 4;
        }else {
            return 1;
        }
    }


    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ((ip.indexOf(",") >= 0)){
            ip = ip.substring(0 , ip.indexOf(","));
        }
        return ip;
    }
}
