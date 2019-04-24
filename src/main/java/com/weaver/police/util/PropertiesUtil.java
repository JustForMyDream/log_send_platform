package com.weaver.police.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*
 * @Author      :wyl
 * @Date        :2019/4/24  14:41
 * @Version 1.0 :
 * @Description :
 **/
public class PropertiesUtil {

    // 静态块中不能有非静态属性，所以加static
//    private static Properties prop = null;

    //静态块中的内容会在类别加载的时候先被执行
//    static {
//        try {
//            prop = new Properties();
//            prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("d.properties"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static Properties loadPropties(String fileName){
        Properties prop = new Properties();
        try {
            prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
            return prop;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    //静态方法可以被类名直接调用
//    public static String getValue(String key) {
//        return prop.getProperty(key);
//    }
}
