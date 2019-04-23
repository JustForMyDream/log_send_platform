package com.weaver.police.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * @Author      :wyl
 * @Date        :2019/4/15  9:37
 * @Version 1.0 :
 * @Description :数据库操作类
 **/
public class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final ThreadLocal<Connection> CONNECTION_HOLDER ;

    private static final QueryRunner QUERY_RUNNER;

    private static final String sep = "\"";

    private static final String wrap = "\n";

    static {
        CONNECTION_HOLDER = new ThreadLocal<Connection>();

        QUERY_RUNNER = new QueryRunner();
    }

    public static Connection getConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if(conn == null){
            try {
                conn = DbPoolConnection.getInstance().getConnection();
            } catch (SQLException e) {
                LOGGER.error("get connection failure",e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

    public static void closeConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure",e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    /**
     * 执行更新操作
     * @param sql
     * @param params
     * @return
     */
    public static int excuteUpdate(String sql,Object ...params){
        int rows = 0;
        try {
            rows = QUERY_RUNNER.update(getConnection(),sql,params);
        } catch (SQLException e) {
            LOGGER.error("excute update failure",e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return rows;
    }

    public static int[] excuteBatch(String sql,Object [][]params){
        int []rows = new int[params.length];
        try {
            QUERY_RUNNER.batch(getConnection(),sql,params);
        } catch (SQLException e) {
            LOGGER.error("excute update failure",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return rows;
    }


    /**
     * 适配达梦数据库，插入实体
     * @param tableName
     * @param fieldMap
     * @param <T>
     * @return
     */
    public  static <T> boolean insertEntityWithDM(String tableName,Map<String,Object> fieldMap){
        if(MapUtils.isEmpty(fieldMap)){
            LOGGER.error("can not insert entity:fieldMap is empty");
            return false;
        }

        String sql = "insert into " + tableName;

        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for(String fieldName : fieldMap.keySet()){
            columns.append(sep).append(fieldName).append(sep).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "),columns.length(),")");
        values.replace(values.lastIndexOf(","),values.length(),")");
        sql += columns + " values" + values;

        Object[] params = fieldMap.values().toArray();
        return excuteUpdate(sql,params) == 1;
    }

    /**
     * 通用数据插入
     * @param tableName
     * @param fieldMap
     * @param <T>
     * @return
     */
    public  static <T> boolean insertEntity(String tableName,Map<String,Object> fieldMap){
        if(MapUtils.isEmpty(fieldMap)){
            LOGGER.error("can not insert entity:fieldMap is empty");
            return false;
        }

        String sql = "insert into " + tableName;

        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for(String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "),columns.length(),")");
        values.replace(values.lastIndexOf(","),values.length(),")");
        sql += columns + " values" + values;

        Object[] params = fieldMap.values().toArray();
        return excuteUpdate(sql,params) == 1;
    }

    /**
     * 批量插入数据处理
     * @param tableName
     * @param fieldMap
     * @param <T>
     * @return
     */
    public  static <T> boolean insertEntityWithBatch(String tableName, List<Map<String,Object>> fieldMap){
        if(fieldMap.size() <=0){
            LOGGER.error("can not insert entity:fieldMap is empty");
            return false;
        }
        String sql = getSql(tableName,fieldMap.get(0));

        int size = fieldMap.size();
        int objSize = fieldMap.get(0).size();
        Object [][]params = new Object[size][];
        for(int i = 0; i< size; i ++){
            params[i] = new Object[objSize];
            Map<String,Object> value = fieldMap.get(i);
            int j = 0;
            for (Map.Entry<String,Object> entry: value.entrySet()){
                params[i][j] = entry.getValue();
                j++;
            }
        }

        return excuteBatch(sql,params).length > 0;
    }

    /**
     * 获取sql
     * @param tableName
     * @param fieldMap
     * @return
     */
    public static String getSql(String tableName,Map<String,Object> fieldMap){
        if(MapUtils.isEmpty(fieldMap)){
            LOGGER.error("can not insert entity:fieldMap is empty");
            return "";
        }
        String sql = "insert into " + tableName;

        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for(String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "),columns.length(),")");
        values.replace(values.lastIndexOf(","),values.length(),")");
        sql += columns + " values" + values;
        return sql;
    }

    /**
     *通过日期创建表
     * @param date
     * @return
     */
    public static String createTableByDate(Date date,Object object){

        StringBuilder sb = new StringBuilder();
        sb.append("create table ");


        return "";
    }

    /**
     *通过时间创建序列
     * @param prefix
     * @return
     */
    public static String createSeqByDate(String prefix){

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE  SEQUENCE " + "seq_"+prefix +DateUtil.getSringDateWithNotTime()).append(wrap);
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

        sb.append("CREATE OR REPLACE TRIGGER " + prefix +DateUtil.getSringDateWithNotTime()).append(wrap);
        sb.append("before insert on " + tableName + " for each row").append(wrap);
        sb.append("begin ").append(wrap);
        sb.append("select seq_"+prefix+DateUtil.getSringDateWithNotTime()+".nextval into :new.datax_id from dual; ").append(wrap);
        sb.append("end;").append(wrap);
        return new String(sb);
    }



    public static String getTableName(Class<?> entityClass){
        return entityClass.getSimpleName();
    }

}
