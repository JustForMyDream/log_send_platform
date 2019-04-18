package com.weaver.police.excuter;

/*
 * @Author      :wyl
 * @Date        :2019/4/17  9:13
 * @Version 1.0 :
 * @Description :
 **/

import com.weaver.police.bean.InterfaceLog;
import com.weaver.police.bean.OperateLog;
import com.weaver.police.service.SaveServcie;
import com.weaver.police.util.JedisUtil;
import org.springframework.stereotype.Component;

public class SaveDataTask implements Runnable {

    private SaveServcie saveServcie;

    private OperateLog operateLog;

    private InterfaceLog interfaceLog;


    public SaveDataTask(SaveServcie saveServcie, OperateLog operateLog) {
        this.saveServcie = saveServcie;
        this.operateLog = operateLog;
    }

    public SaveDataTask(SaveServcie saveServcie, InterfaceLog interfaceLog) {
        this.saveServcie = saveServcie;
        this.interfaceLog = interfaceLog;
    }

    @Override
    public void run() {
        if (interfaceLog != null){
            try {
                saveServcie.doSaveInterfaceLog(interfaceLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(operateLog != null){
            try {
                saveServcie.doSaveOperateLog(operateLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
