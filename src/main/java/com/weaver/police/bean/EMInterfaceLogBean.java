package com.weaver.police.bean;

/*
 * @Author      :wyl
 * @Date        :2019/4/22  14:18
 * @Version 1.0 :
 * @Description :EM的bean
 **/
public class EMInterfaceLogBean {

    private String userInfo;

    private String INTERFACE_TIME;

    private String REQUESTER;

    private String INTERFACE_CONDITION;

    private String INTERFACE_RESULT;

    private String TERMINAL_ID;

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getINTERFACE_TIME() {
        return INTERFACE_TIME;
    }

    public void setINTERFACE_TIME(String INTERFACE_TIME) {
        this.INTERFACE_TIME = INTERFACE_TIME;
    }

    public String getREQUESTER() {
        return REQUESTER;
    }

    public void setREQUESTER(String REQUESTER) {
        this.REQUESTER = REQUESTER;
    }

    public String getINTERFACE_CONDITION() {
        return INTERFACE_CONDITION;
    }

    public void setINTERFACE_CONDITION(String INTERFACE_CONDITION) {
        this.INTERFACE_CONDITION = INTERFACE_CONDITION;
    }

    public String getINTERFACE_RESULT() {
        return INTERFACE_RESULT;
    }

    public void setINTERFACE_RESULT(String INTERFACE_RESULT) {
        this.INTERFACE_RESULT = INTERFACE_RESULT;
    }

    public String getTERMINAL_ID() {
        return TERMINAL_ID;
    }

    public void setTERMINAL_ID(String TERMINAL_ID) {
        this.TERMINAL_ID = TERMINAL_ID;
    }
}
