package com.weaver.police.dao.impl;

import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.dao.InterfaceLogDao;
import com.weaver.police.util.DatabaseHelper;
import com.weaver.police.util.ObjectUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/*
 * @Author      :wyl
 * @Date        :2019/4/15  16:43
 * @Version 1.0 :
 * @Description :
 **/
@Service
public class InterfaceLogDaoImpl implements InterfaceLogDao {

    private static final String tableName = "\"SYSDBA\".\"INTERFACE_LOG\"";

    @Override
    public boolean insert(InterfaceLog interfaceLog) throws Exception {
        Map<String,Object> dataMap = ObjectUtil.objectToMap(interfaceLog);
        boolean flag = DatabaseHelper.insertEntity(tableName,dataMap);
        return flag;
    }
}
