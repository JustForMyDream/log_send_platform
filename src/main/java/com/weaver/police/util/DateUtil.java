package com.weaver.police.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @Author      :wyl
 * @Date        :2019/4/23  11:57
 * @Version 1.0 :
 * @Description :
 **/
public class DateUtil {

    /**
     * 获取当前时间 2018-10-18：11-24-45
     * @return
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getSringDateWithNotTime(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    public static String getSringDateWithUnderline(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime).replace("-","_");
        return dateString;
    }
}
