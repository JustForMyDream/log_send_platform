package com.weaver.police.bean;

import java.util.List;

/*
 * @Author      :wyl
 * @Date        :2019/4/17  14:44
 * @Version 1.0 :
 * @Description :
 **/
public class DataLogBean {

    private String userInfo;

    private String operateLog;

    private String interfaceLog;

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getOperateLog() {
        return operateLog;
    }

    public void setOperateLog(String operateLog) {
        this.operateLog = operateLog;
    }

    public String getInterfaceLog() {
        return interfaceLog;
    }

    public void setInterfaceLog(String interfaceLog) {
        this.interfaceLog = interfaceLog;
    }
}
