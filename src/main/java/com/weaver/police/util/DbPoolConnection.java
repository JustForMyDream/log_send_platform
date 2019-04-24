package com.weaver.police.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.sql.SQLException;
import java.util.Properties;

/*
 * @Author      :wyl
 * @Date        :2019/4/12  17:29
 * @Version 1.0 :
 * @Description :
 **/
public class DbPoolConnection {

    private static DbPoolConnection databasePool = null;
    public static DruidDataSource dds = null;

//    private final static String driverClassName = "oracle.jdbc.driver.OracleDriver";
//    private final static String url = "jdbc:oracle:thin:@192.168.40.245:1521:ECOLOGY";
//    private final static String username = "ecology";
//    private final static String password = "ecology";

//    private final static String path = "D:\\tools\\tomcat\\apache-tomcat-9.0.12\\webapps\\police-0\\WEB-INF\\classes";

    static {
        Properties properties = loadPropertyFile("durid.properties");
        try {
            dds = (DruidDataSource) DruidDataSourceFactory
                    .createDataSource(properties);
//            dds.setDriverClassName(driverClassName);
//            dds.setUrl(url);
//            dds.setUsername(username);
//            dds.setPassword(password);
//            dds = new DruidDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DbPoolConnection() {}

    public static synchronized DbPoolConnection getInstance() {
        if (null == databasePool) {
            databasePool = new DbPoolConnection();
        }
        return databasePool;
    }

    public DruidPooledConnection getConnection() throws SQLException {
        return dds.getConnection();
    }

    public static Properties loadPropertyFile(String fullFile) {
        String webRootPath = null;
        if (null == fullFile || fullFile.equals(""))
            throw new IllegalArgumentException(
                    "Properties file path can not be null : " + fullFile);
        webRootPath = DbPoolConnection.class.getClassLoader().getResource("")
                .getPath();

//        webRootPath = webRootPath.substring(1,webRootPath.length() - 1);

//        webRootPath = new File(webRootPath).getParent();
        InputStream inputStream = null;
        Properties p = null;
        try {
            inputStream = new FileInputStream(new File(webRootPath
                    + File.separator + fullFile));
            p = new Properties();
            p.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Properties file not found: "
                    + fullFile);
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "Properties file can not be loading: " + fullFile);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }
}
