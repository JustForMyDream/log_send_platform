package com.weaver.police.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;


/*
 * @Author      :wyl
 * @Date        :2019/4/24  14:34
 * @Version 1.0 :
 * @Description :
 **/
public class TableUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableUtil.class);
    private static final String wrap = " ";
    private final static String username;
    private final static String password;
    private final static String driverClassName;
    private final static String url;

    static {

        Properties prop = PropertiesUtil.loadPropties("durid.properties");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
        driverClassName = prop.getProperty("driverClassName");
        url = prop.getProperty("url");

    }

    private static Connection getConnection(){
        try {
            Class.forName(driverClassName);
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String execute(String tableName, List<String> list, String prefix){

        String tableSql = createTableByDate(tableName,list);
        String seqSql = createSeqByDate(prefix);
        String tiggerSql =createTiggerByDate(prefix,tableName);

        Connection connection = getConnection();
        boolean status = false;

        try {
            Statement statement = connection.createStatement();
            statement.execute(tableSql);
            statement.execute(seqSql);
            statement.execute(tiggerSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }

        System.out.println(tableSql);
//        int status = excuteUpdate(tableSql);


        return ""+status;
    }



    /**
     * 通过日期创建表
     * @param tableName
     * @param list
     * @return
     */
    public static String createTableByDate(String tableName,List<String> list){

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+ tableName +"_"+ DateUtil.getSringDateWithUnderline()).append(wrap);
        sb.append("(").append(wrap);
        for (String s : list){
            sb.append(s).append(wrap);
        }
        sb.append(")").append(wrap);

        return new String(sb);
    }

    /**
     *通过时间创建序列
     * @param prefix
     * @return
     */
    public static String createSeqByDate(String prefix){

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE  SEQUENCE " + "seq_"+prefix+ "_" +DateUtil.getSringDateWithUnderline()).append(wrap);
        sb.append("INCREMENT BY 1 ").append(wrap);
        sb.append("START WITH 1 ").append(wrap);
        sb.append("NOMAXVALUE ").append(wrap);
        sb.append("NOCYCLE ").append(wrap);
        sb.append("NOCACHE ").append(wrap);
        return new String(sb);
    }

    /**
     * 通过时间创建触发器
     * @param prefix
     * @param tableName
     * @return
     */
    public static String createTiggerByDate(String prefix,String tableName){

        StringBuilder sb = new StringBuilder();

        sb.append("CREATE OR REPLACE TRIGGER tigger_" + prefix +"_"+DateUtil.getSringDateWithUnderline()).append(wrap);
        sb.append("before insert on " + tableName+"_" + DateUtil.getSringDateWithUnderline() + " for each row").append(wrap);
        sb.append("begin ").append(wrap);
        sb.append("select seq_"+prefix+DateUtil.getSringDateWithUnderline()+".nextval into :new.datax_id from dual; ").append(wrap);
        sb.append("end").append(wrap);
        return new String(sb);
    }
}
