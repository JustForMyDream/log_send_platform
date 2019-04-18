package com.weaver.police.dao.impl;

import com.weaver.police.bean.OperateLog;
import com.weaver.police.dao.OperateLogDao;
import com.weaver.police.util.DatabaseHelper;
import com.weaver.police.util.DbPoolConnection;
import com.weaver.police.util.ObjectUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

/*
 * @Author      :wyl
 * @Date        :2019/4/12  18:12
 * @Version 1.0 :
 * @Description :
 **/
@Service
public class OperateLogDaoImpl implements OperateLogDao {

//    private static final String tableName = "\"SYSDBA\".\"OPERATE_LOG\"";
    @Value("${operate.log}")
    private String tableName;

    private static String stableName;

    @PostConstruct
    public void init(){
        stableName = tableName;
    }

    @Override
    public boolean insert(OperateLog operateLog) throws Exception {
        Map<String,Object> dataMap = ObjectUtil.objectToMap(operateLog);
        boolean flag = DatabaseHelper.insertEntity(stableName,dataMap);
        return flag;
    }
}
