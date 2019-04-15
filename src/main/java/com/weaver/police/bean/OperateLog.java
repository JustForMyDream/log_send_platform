package com.weaver.police.bean;

import java.io.Serializable;
import java.util.Date;

public class OperateLog implements Serializable {

    private String NUM_ID;//流水号

    private String REG_ID;//应用标识

    private String USER_ID;//用户标识

    private String ORGANIZATION;//单位名称

    private String ORGANIZATION_ID;//单位机构代码

    private String USER_NAME;//用户姓名

    private String TERMINAL_ID;//终端标识

    private String OPERATE_TIME;//操作时间

    private Short OPERATE_TYPE;//操作类型

    private String OPERATE_RESULT;//操作结果

    private String ERROR_CODE;//操作所在模块或功能名称

    private String OPERATE_NAME;//操作所在模块或功能名称

    private String OPERATE_CONDITION;//操作条件

    private Long OPERATE_NUMBER;//本次操作返回的条目数

    private String OPERATE_TABLE;//被操作数据表

    private String OPERATE_KEY;//被操作数据的主键标识

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

    public String getOPERATE_TIME() {
        return OPERATE_TIME;
    }

    public void setOPERATE_TIME(String OPERATE_TIME) {
        this.OPERATE_TIME = OPERATE_TIME == null ? null : OPERATE_TIME.trim();
    }

    public Short getOPERATE_TYPE() {
        return OPERATE_TYPE;
    }

    public void setOPERATE_TYPE(Short OPERATE_TYPE) {
        this.OPERATE_TYPE = OPERATE_TYPE;
    }

    public String getOPERATE_RESULT() {
        return OPERATE_RESULT;
    }

    public void setOPERATE_RESULT(String OPERATE_RESULT) {
        this.OPERATE_RESULT = OPERATE_RESULT == null ? null : OPERATE_RESULT.trim();
    }

    public String getERROR_CODE() {
        return ERROR_CODE;
    }

    public void setERROR_CODE(String ERROR_CODE) {
        this.ERROR_CODE = ERROR_CODE == null ? null : ERROR_CODE.trim();
    }

    public String getOPERATE_NAME() {
        return OPERATE_NAME;
    }

    public void setOPERATE_NAME(String OPERATE_NAME) {
        this.OPERATE_NAME = OPERATE_NAME == null ? null : OPERATE_NAME.trim();
    }

    public String getOPERATE_CONDITION() {
        return OPERATE_CONDITION;
    }

    public void setOPERATE_CONDITION(String OPERATE_CONDITION) {
        this.OPERATE_CONDITION = OPERATE_CONDITION == null ? null : OPERATE_CONDITION.trim();
    }

    public Long getOPERATE_NUMBER() {
        return OPERATE_NUMBER;
    }

    public void setOPERATE_NUMBER(Long OPERATE_NUMBER) {
        this.OPERATE_NUMBER = OPERATE_NUMBER;
    }

    public String getOPERATE_TABLE() {
        return OPERATE_TABLE;
    }

    public void setOPERATE_TABLE(String OPERATE_TABLE) {
        this.OPERATE_TABLE = OPERATE_TABLE == null ? null : OPERATE_TABLE.trim();
    }

    public String getOPERATE_KEY() {
        return OPERATE_KEY;
    }

    public void setOPERATE_KEY(String OPERATE_KEY) {
        this.OPERATE_KEY = OPERATE_KEY == null ? null : OPERATE_KEY.trim();
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