package com.weaver.police.service.impl;

import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.bean.OperateLog;
import com.weaver.police.dao.InterfaceLogDao;
import com.weaver.police.dao.OperateLogDao;
import com.weaver.police.service.SaveServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
 * @Author      :wyl
 * @Date        :2019/4/10  18:13
 * @Version 1.0 :
 * @Description :
 **/
@Service
public class SaveServcieImpl implements SaveServcie {

    @Autowired
    private OperateLogDao operateLogDao;

    @Autowired
    private InterfaceLogDao interfaceLogDao;


    @Override
    public boolean doSaveOperateLog(OperateLog operateLog) throws Exception {
      return operateLogDao.insert(operateLog);
    }

    @Override
    public boolean doSaveInterfaceLog(InterfaceLog interfaceLog) throws Exception {
        return interfaceLogDao.insert(interfaceLog);
    }

    @Override
    public boolean doSaveOperateLog(List<OperateLog> operateLogs) throws Exception {
        return operateLogDao.insertWithBatch(operateLogs);
    }

    @Override
    public boolean doSaveInterfaceLog(List<InterfaceLog> interfaceLogs) throws Exception {
        return interfaceLogDao.insertWithBatch(interfaceLogs);
    }

    @Override
    public boolean doSaveOperateLogWithDay(List<OperateLog> operateLogs) throws Exception {
        return operateLogDao.insertWithBatchAndDay(operateLogs);
    }

    @Override
    public boolean doSaveInterfaceLogWithDay(List<InterfaceLog> interfaceLogs) throws Exception {
        return interfaceLogDao.insertWithBatchAndDay(interfaceLogs);
    }
}
