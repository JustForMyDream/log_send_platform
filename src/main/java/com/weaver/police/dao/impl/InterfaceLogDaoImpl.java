package com.weaver.police.dao.impl;

import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.dao.InterfaceLogDao;
import com.weaver.police.util.DatabaseHelper;
import com.weaver.police.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @Author      :wyl
 * @Date        :2019/4/15  16:43
 * @Version 1.0 :
 * @Description :
 **/
@Service
public class InterfaceLogDaoImpl implements InterfaceLogDao {

//    private static final String tableName = "\"SYSDBA\".\"INTERFACE_LOG\"";
//    private static final String tableName = "ECOLOGY.INTERFACE_LOG";

    @Value("${interface.log}")
    private String tableName;

    private static String stableName;

    @PostConstruct
    public void init(){
        stableName = tableName;
    }

    @Override
    public boolean insert(InterfaceLog interfaceLog) throws Exception {
        Map<String,Object> dataMap = ObjectUtil.objectToMap(interfaceLog);
        boolean flag = DatabaseHelper.insertEntity(tableName,dataMap);
        return flag;
    }

    @Override
    public boolean insertWithBatch(List<InterfaceLog> interfaceLogs) throws Exception {
        List<Map<String,Object>> list = new ArrayList<>();
        int size = interfaceLogs.size();
        for(int i = 0;i < size; i ++){
            InterfaceLog interfaceLog = interfaceLogs.get(i);
            Map<String,Object> dataMap = ObjectUtil.objectToMap(interfaceLog);
            list.add(dataMap);
        }
        return DatabaseHelper.insertEntityWithBatch(stableName,list);
    }
}
