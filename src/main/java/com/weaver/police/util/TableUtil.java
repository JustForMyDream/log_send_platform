package com.weaver.police.util;

import com.weaver.police.constant.PoliceConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    private static final String wrap = "\n";
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

    public static Connection getConnection(){
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

    /**
     * 执行操作
     * @param tableName
     * @param list
     * @param prefix
     * @return
     */
    public static boolean execute(String tableName, List<String> list, String prefix){

        String tableSql = createTableByDate(tableName,list);
        String seqSql = createSeqByDate(prefix);
        String tiggerSql =createTiggerByDate(prefix,tableName);

        Connection connection = getConnection();

        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute(tableSql);
            statement.execute(seqSql);
            statement.execute(tiggerSql);
            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }finally {
            closeConnection(connection);
        }

    }



    /**
     * 通过日期创建表
     * @param tableName
     * @param list
     * @return
     */
    public static String createTableByDate(String tableName,List<String> list){
        tableName = tableName.toUpperCase();

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
        prefix = prefix.toUpperCase();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE  SEQUENCE " + "SEQ_"+prefix+ "_" +DateUtil.getSringDateWithUnderline()).append(wrap);
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

        prefix = prefix.toUpperCase();
        tableName = tableName.toUpperCase();
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE OR REPLACE TRIGGER TIG_" + prefix +"_"+DateUtil.getSringDateWithUnderline()).append(" before").append(wrap);
        sb.append("insert on " + tableName+"_" + DateUtil.getSringDateWithUnderline() + " for each row when (new.id is null) ").append(wrap);
        sb.append("begin ").append(wrap);
        sb.append("select SEQ_"+prefix+"_"+DateUtil.getSringDateWithUnderline()+".nextval into:new.id from dual; ").append(wrap);
        sb.append("end").append(wrap);
        return new String(sb);
    }

    /**
     * 根据日期获取表名
     * @param tableName
     * @return
     */
    public static String getTableNameByDate(String tableName){
        String dboName = "";

        if(driverClassName.contains("oracle") || driverClassName.contains("dm")){
            String []dbo = url.split(":");
            if(dbo.length == 0){
                dbo = url.split("/");
            }
            dboName = dbo[dbo.length - 1];
        }
        tableName = dboName+"."+tableName.toUpperCase() +"_"+ DateUtil.getSringDateWithUnderline();

        return tableName;
    }

    /**
     * 获取表的list
     * @param type
     * @return
     */
    public static List<String> getTableList(String type){

        List<String> list = new ArrayList<>();
        if (StringUtils.equals(PoliceConstant.INTERFACE_LOG,type)){
            list.add("  id                  NUMBER(20) not null,");
            list.add("  num_id              varchar2(32) not null,");
            list.add("  reg_id              VARCHAR2(120) not null,");
            list.add("  user_id             VARCHAR2(180),");
            list.add("  organization        VARCHAR2(1000),");
            list.add("  organization_id     VARCHAR2(120),");
            list.add("  user_name           VARCHAR2(600),");
            list.add("  terminal_id         VARCHAR2(400) not null,");
            list.add("  interface_time      VARCHAR2(14) not null,");
            list.add("  requester           VARCHAR2(1000) not null,");
            list.add("  interface_result    VARCHAR2(1) not null,");
            list.add("  error_code          VARCHAR2(4),");
            list.add("  interface_name      VARCHAR2(1000),");
            list.add("  interface_condition VARCHAR2(4000),");
            list.add("  interface_number    NUMBER(20),");
            list.add("  interface_table     VARCHAR2(2000),");
            list.add("  interface_key       VARCHAR2(2000),");
            list.add("  insert_time         DATE default SYSDATE,");
            list.add("  collect_type        VARCHAR2(10) default ('2'),");
            list.add("  sendid              VARCHAR2(320)");
        }else if (StringUtils.equals(PoliceConstant.OPERATE_LOG,type)){
            list.add("  id                NUMBER(13) not null,");
            list.add("  num_id            varchar2(32) not null,");
            list.add("  reg_id            VARCHAR2(12) not null,");
            list.add("  user_id           VARCHAR2(18) not null,");
            list.add("  organization      VARCHAR2(100) not null,");
            list.add("  organization_id   VARCHAR2(12),");
            list.add("  user_name         VARCHAR2(60) not null,");
            list.add("  terminal_id       VARCHAR2(40) not null,");
            list.add("  operate_time      CHAR(14) not null,");
            list.add("  operate_type      NUMBER(1) not null,");
            list.add("  operate_result    CHAR(1) not null,");
            list.add("  error_code        CHAR(4),");
            list.add("  operate_name      VARCHAR2(60),");
            list.add("  operate_condition VARCHAR2(4000),");
            list.add("  operate_number    NUMBER(13),");
            list.add("  operate_table     VARCHAR2(200),");
            list.add("  operate_key       VARCHAR2(200),");
            list.add("  insert_time       DATE default SYSDATE,");
            list.add("  collect_type      CHAR(1) default ('2'),");
            list.add("  sendid            VARCHAR2(32)");
        }

        return list;
    }
}
