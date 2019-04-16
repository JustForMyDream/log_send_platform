package com.weaver.police.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InterfaceLog implements Serializable {

    private String NUM_ID;//流水号

    private String REG_ID;//应用标识

    private String USER_ID;//用户标识

    private String ORGANIZATION;//单位名称

    private String ORGANIZATION_ID;//单位机构代码

    private String USER_NAME;//用户姓名

    private String TERMINAL_ID;//终端标识

    private String INTERFACE_TIME;//接口服务时间

    private String REQUESTER;//请求方名称

    private String INTERFACE_RESULT;//接口服务结果

    private String ERROR_CODE;//失败原因代码

    private String INTERFACE_NAME;//接口名称

    private String INTERFACE_CONDITION;//接口服务条件

    private int INTERFACE_NUMBER;//服务返回条目数

    private String INTERFACE_TABLE;//接口服务数据表

    private String INTERFACE_KEY;//接口服务数据的主键标识

    private Date INSERT_TIME;

    private String COLLECT_TYPE;

    private String SENDID;

    private static final long serialVersionUID = 1L;

    public InterfaceLog(String NUM_ID, String REG_ID, String TERMINAL_ID, String INTERFACE_TIME, String REQUESTER, String INTERFACE_RESULT, String INTERFACE_CONDITION) {
        this.NUM_ID = NUM_ID;
        this.REG_ID = REG_ID;
        this.TERMINAL_ID = TERMINAL_ID;
        this.INTERFACE_TIME = INTERFACE_TIME;
        this.REQUESTER = REQUESTER;
        this.INTERFACE_RESULT = INTERFACE_RESULT;
        this.INTERFACE_CONDITION = INTERFACE_CONDITION;
    }

    public String getNUM_ID() {
        return NUM_ID;
    }

    public InterfaceLog setNUM_ID(String NUM_ID) {
        this.NUM_ID = NUM_ID == null ? null : NUM_ID.trim();
        return this;
    }

    public String getREG_ID() {
        return REG_ID;
    }

    public InterfaceLog setREG_ID(String REG_ID) {
        this.REG_ID = REG_ID == null ? null : REG_ID.trim();
        return this;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public InterfaceLog setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID == null ? null : USER_ID.trim();
        return this;
    }

    public String getORGANIZATION() {
        return ORGANIZATION;
    }

    public InterfaceLog setORGANIZATION(String ORGANIZATION) {
        this.ORGANIZATION = ORGANIZATION == null ? null : ORGANIZATION.trim();
        return this;
    }

    public String getORGANIZATION_ID() {
        return ORGANIZATION_ID;
    }

    public InterfaceLog setORGANIZATION_ID(String ORGANIZATION_ID) {
        this.ORGANIZATION_ID = ORGANIZATION_ID == null ? null : ORGANIZATION_ID.trim();
        return this;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public InterfaceLog setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME == null ? null : USER_NAME.trim();
        return this;
    }

    public String getTERMINAL_ID() {
        return TERMINAL_ID;
    }

    public InterfaceLog setTERMINAL_ID(String TERMINAL_ID) {
        this.TERMINAL_ID = TERMINAL_ID == null ? null : TERMINAL_ID.trim();
        return this;
    }

    public String getINTERFACE_TIME() {
        return INTERFACE_TIME;
    }

    public InterfaceLog setINTERFACE_TIME(String INTERFACE_TIME) {
        this.INTERFACE_TIME = INTERFACE_TIME == null ? null : INTERFACE_TIME.trim();
        return this;
    }

    public String getREQUESTER() {
        return REQUESTER;
    }

    public InterfaceLog setREQUESTER(String REQUESTER) {
        this.REQUESTER = REQUESTER == null ? null : REQUESTER.trim();
        return this;
    }

    public String getINTERFACE_RESULT() {
        return INTERFACE_RESULT;
    }

    public InterfaceLog setINTERFACE_RESULT(String INTERFACE_RESULT) {
        this.INTERFACE_RESULT = INTERFACE_RESULT == null ? null : INTERFACE_RESULT.trim();
        return this;
    }

    public String getERROR_CODE() {
        return ERROR_CODE;
    }

    public InterfaceLog setERROR_CODE(String ERROR_CODE) {
        this.ERROR_CODE = ERROR_CODE == null ? null : ERROR_CODE.trim();
        return this;
    }

    public String getINTERFACE_NAME() {
        return INTERFACE_NAME;
    }

    public InterfaceLog setINTERFACE_NAME(String INTERFACE_NAME) {
        this.INTERFACE_NAME = INTERFACE_NAME == null ? null : INTERFACE_NAME.trim();
        return this;
    }

    public String getINTERFACE_CONDITION() {
        return INTERFACE_CONDITION;
    }

    public InterfaceLog setINTERFACE_CONDITION(String INTERFACE_CONDITION) {
        this.INTERFACE_CONDITION = INTERFACE_CONDITION == null ? null : INTERFACE_CONDITION.trim();
        return this;
    }

    public int getINTERFACE_NUMBER() {
        return INTERFACE_NUMBER;
    }

    public InterfaceLog setINTERFACE_NUMBER(int INTERFACE_NUMBER) {
        this.INTERFACE_NUMBER = INTERFACE_NUMBER;
        return this;
    }

    public String getINTERFACE_TABLE() {
        return INTERFACE_TABLE;
    }

    public InterfaceLog setINTERFACE_TABLE(String INTERFACE_TABLE) {
        this.INTERFACE_TABLE = INTERFACE_TABLE == null ? null : INTERFACE_TABLE.trim();
        return this;
    }

    public String getINTERFACE_KEY() {
        return INTERFACE_KEY;
    }

    public InterfaceLog setINTERFACE_KEY(String INTERFACE_KEY) {
        this.INTERFACE_KEY = INTERFACE_KEY == null ? null : INTERFACE_KEY.trim();
        return this;
    }

    public Date getINSERT_TIME() {
        return INSERT_TIME;
    }

    public InterfaceLog setINSERT_TIME(Date INSERT_TIME) {
        this.INSERT_TIME = INSERT_TIME;
        return this;
    }

    public String getCOLLECT_TYPE() {
        return COLLECT_TYPE;
    }

    public InterfaceLog setCOLLECT_TYPE(String COLLECT_TYPE) {
        this.COLLECT_TYPE = COLLECT_TYPE == null ? null : COLLECT_TYPE.trim();
        return this;
    }

    public String getSENDID() {
        return SENDID;
    }

    public InterfaceLog setSENDID(String SENDID) {
        this.SENDID = SENDID == null ? null : SENDID.trim();
        return this;
    }
}