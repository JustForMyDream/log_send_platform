package com.weaver.police.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
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
     * 数据插入
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

    public  static <T> boolean insertEntityWithBatch(String tableName,Map<String,Object> fieldMap,Object [][]params){
//        excuteBatch(getSql(tableName))
        return true;
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



    public static String getTableName(Class<?> entityClass){
        return entityClass.getSimpleName();
    }

}
