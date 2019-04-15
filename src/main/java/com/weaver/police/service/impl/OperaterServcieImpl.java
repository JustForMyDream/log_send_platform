package com.weaver.police.service.impl;

import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.bean.OperateLog;
import com.weaver.police.dao.InterfaceLogDao;
import com.weaver.police.dao.OperateLogDao;
import com.weaver.police.service.OperaterServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * @Author      :wyl
 * @Date        :2019/4/10  18:13
 * @Version 1.0 :
 * @Description :
 **/
@Service
public class OperaterServcieImpl implements OperaterServcie {

    @Autowired
    private OperateLogDao operateLogDao;

    @Autowired
    private InterfaceLogDao interfaceLogDao;

    @Override
    public boolean doSaveOperateLog(OperateLog operateLog) throws Exception {
      return operateLogDao.insert(operateLog);
    }

    @Override
    public boolean doSaveInterfaceLog(InterfaceLog operateLog) throws Exception {
        return interfaceLogDao.insert(operateLog);
    }
}
