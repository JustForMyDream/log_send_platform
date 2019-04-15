package com.weaver.police.service;

import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.bean.OperateLog;
import org.springframework.stereotype.Component;


/*
 * @Author      :wyl
 * @Date        :2019/4/10  18:11
 * @Version 1.0 :
 * @Description :
 **/
@Component
public interface OperaterServcie {

    public boolean doSaveOperateLog(OperateLog operateLog) throws Exception;

    public boolean doSaveInterfaceLog(InterfaceLog operateLog) throws Exception;

}
