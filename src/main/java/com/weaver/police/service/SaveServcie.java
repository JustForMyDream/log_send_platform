package com.weaver.police.service;

import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.bean.OperateLog;
import org.springframework.stereotype.Component;

import java.util.List;


/*
 * @Author      :wyl
 * @Date        :2019/4/10  18:11
 * @Version 1.0 :
 * @Description :
 **/
@Component
public interface SaveServcie {

    public boolean doSaveOperateLog(OperateLog operateLog) throws Exception;

    public boolean doSaveInterfaceLog(InterfaceLog interfaceLog) throws Exception;

    public boolean doSaveOperateLog(List<OperateLog> operateLogs) throws Exception;

    public boolean doSaveInterfaceLog(List<InterfaceLog> interfaceLogs) throws Exception;


}
