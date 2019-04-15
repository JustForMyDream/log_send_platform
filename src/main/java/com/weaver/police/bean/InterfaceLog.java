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

    private BigDecimal INTERFACE_NUMBER;//服务返回条目数

    private String INTERFACE_TABLE;//接口服务数据表

    private String INTERFACE_KEY;//接口服务数据的主键标识

    private Date INSERT_TIME;

    private String COLLECT_TYPE;

    private String SENDID;

    private static final long serialVersionUID = 1L;

    public String getNUM_ID() {
        return NUM_ID;
    }

    public void setNUM_ID(String NUM_ID) {
        this.NUM_ID = NUM_ID == null ? null : NUM_ID.trim();
    }

    public String getREG_ID() {
        return REG_ID;
    }

    public void setREG_ID(String REG_ID) {
        this.REG_ID = REG_ID == null ? null : REG_ID.trim();
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID == null ? null : USER_ID.trim();
    }

    public String getORGANIZATION() {
        return ORGANIZATION;
    }

    public void setORGANIZATION(String ORGANIZATION) {
        this.ORGANIZATION = ORGANIZATION == null ? null : ORGANIZATION.trim();
    }

    public String getORGANIZATION_ID() {
        return ORGANIZATION_ID;
    }

    public void setORGANIZATION_ID(String ORGANIZATION_ID) {
        this.ORGANIZATION_ID = ORGANIZATION_ID == null ? null : ORGANIZATION_ID.trim();
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME == null ? null : USER_NAME.trim();
    }

    public String getTERMINAL_ID() {
        return TERMINAL_ID;
    }

    public void setTERMINAL_ID(String TERMINAL_ID) {
        this.TERMINAL_ID = TERMINAL_ID == null ? null : TERMINAL_ID.trim();
    }

    public String getINTERFACE_TIME() {
        return INTERFACE_TIME;
    }

    public void setINTERFACE_TIME(String INTERFACE_TIME) {
        this.INTERFACE_TIME = INTERFACE_TIME == null ? null : INTERFACE_TIME.trim();
    }

    public String getREQUESTER() {
        return REQUESTER;
    }

    public void setREQUESTER(String REQUESTER) {
        this.REQUESTER = REQUESTER == null ? null : REQUESTER.trim();
    }

    public String getINTERFACE_RESULT() {
        return INTERFACE_RESULT;
    }

    public void setINTERFACE_RESULT(String INTERFACE_RESULT) {
        this.INTERFACE_RESULT = INTERFACE_RESULT == null ? null : INTERFACE_RESULT.trim();
    }

    public String getERROR_CODE() {
        return ERROR_CODE;
    }

    public void setERROR_CODE(String ERROR_CODE) {
        this.ERROR_CODE = ERROR_CODE == null ? null : ERROR_CODE.trim();
    }

    public String getINTERFACE_NAME() {
        return INTERFACE_NAME;
    }

    public void setINTERFACE_NAME(String INTERFACE_NAME) {
        this.INTERFACE_NAME = INTERFACE_NAME == null ? null : INTERFACE_NAME.trim();
    }

    public String getINTERFACE_CONDITION() {
        return INTERFACE_CONDITION;
    }

    public void setINTERFACE_CONDITION(String INTERFACE_CONDITION) {
        this.INTERFACE_CONDITION = INTERFACE_CONDITION == null ? null : INTERFACE_CONDITION.trim();
    }

    public BigDecimal getINTERFACE_NUMBER() {
        return INTERFACE_NUMBER;
    }

    public void setINTERFACE_NUMBER(BigDecimal INTERFACE_NUMBER) {
        this.INTERFACE_NUMBER = INTERFACE_NUMBER;
    }

    public String getINTERFACE_TABLE() {
        return INTERFACE_TABLE;
    }

    public void setINTERFACE_TABLE(String INTERFACE_TABLE) {
        this.INTERFACE_TABLE = INTERFACE_TABLE == null ? null : INTERFACE_TABLE.trim();
    }

    public String getINTERFACE_KEY() {
        return INTERFACE_KEY;
    }

    public void setINTERFACE_KEY(String INTERFACE_KEY) {
        this.INTERFACE_KEY = INTERFACE_KEY == null ? null : INTERFACE_KEY.trim();
    }

    public Date getINSERT_TIME() {
        return INSERT_TIME;
    }

    public void setINSERT_TIME(Date INSERT_TIME) {
        this.INSERT_TIME = INSERT_TIME;
    }

    public String getCOLLECT_TYPE() {
        return COLLECT_TYPE;
    }

    public void setCOLLECT_TYPE(String COLLECT_TYPE) {
        this.COLLECT_TYPE = COLLECT_TYPE == null ? null : COLLECT_TYPE.trim();
    }

    public String getSENDID() {
        return SENDID;
    }

    public void setSENDID(String SENDID) {
        this.SENDID = SENDID == null ? null : SENDID.trim();
    }
}