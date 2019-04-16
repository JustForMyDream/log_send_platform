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

    private int OPERATE_NUMBER;//本次操作返回的条目数

    private String OPERATE_TABLE;//被操作数据表

    private String OPERATE_KEY;//被操作数据的主键标识

    private Date INSERT_TIME;

    private String COLLECT_TYPE;

    private String SENDID;

    private static final long serialVersionUID = 1L;

    public OperateLog(String NUM_ID, String REG_ID, String USER_ID, String ORGANIZATION, String USER_NAME, String TERMINAL_ID, String OPERATE_TIME, Short OPERATE_TYPE, String OPERATE_RESULT) {
        this.NUM_ID = NUM_ID;
        this.REG_ID = REG_ID;
        this.USER_ID = USER_ID;
        this.ORGANIZATION = ORGANIZATION;
        this.USER_NAME = USER_NAME;
        this.TERMINAL_ID = TERMINAL_ID;
        this.OPERATE_TIME = OPERATE_TIME;
        this.OPERATE_TYPE = OPERATE_TYPE;
        this.OPERATE_RESULT = OPERATE_RESULT;
    }

    public String getNUM_ID() {
        return NUM_ID;
    }

    public OperateLog setNUM_ID(String NUM_ID) {
        this.NUM_ID = NUM_ID == null ? null : NUM_ID.trim();
        return this;
    }

    public String getREG_ID() {
        return REG_ID;
    }

    public OperateLog setREG_ID(String REG_ID) {
        this.REG_ID = REG_ID == null ? null : REG_ID.trim();
        return this;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public OperateLog setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID == null ? null : USER_ID.trim();
        return this;
    }

    public String getORGANIZATION() {
        return ORGANIZATION;
    }

    public OperateLog setORGANIZATION(String ORGANIZATION) {
        this.ORGANIZATION = ORGANIZATION == null ? null : ORGANIZATION.trim();
        return this;
    }

    public String getORGANIZATION_ID() {
        return ORGANIZATION_ID;
    }

    public OperateLog setORGANIZATION_ID(String ORGANIZATION_ID) {
        this.ORGANIZATION_ID = ORGANIZATION_ID == null ? null : ORGANIZATION_ID.trim();
        return this;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public OperateLog setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME == null ? null : USER_NAME.trim();
        return this;
    }

    public String getTERMINAL_ID() {
        return TERMINAL_ID;
    }

    public OperateLog setTERMINAL_ID(String TERMINAL_ID) {
        this.TERMINAL_ID = TERMINAL_ID == null ? null : TERMINAL_ID.trim();
        return this;
    }

    public String getOPERATE_TIME() {
        return OPERATE_TIME;
    }

    public OperateLog setOPERATE_TIME(String OPERATE_TIME) {
        this.OPERATE_TIME = OPERATE_TIME == null ? null : OPERATE_TIME.trim();
        return this;
    }

    public Short getOPERATE_TYPE() {
        return OPERATE_TYPE;
    }

    public OperateLog setOPERATE_TYPE(Short OPERATE_TYPE) {
        this.OPERATE_TYPE = OPERATE_TYPE;
        return this;
    }

    public String getOPERATE_RESULT() {
        return OPERATE_RESULT;
    }

    public OperateLog setOPERATE_RESULT(String OPERATE_RESULT) {
        this.OPERATE_RESULT = OPERATE_RESULT == null ? null : OPERATE_RESULT.trim();
        return this;
    }

    public String getERROR_CODE() {
        return ERROR_CODE;
    }

    public OperateLog setERROR_CODE(String ERROR_CODE) {
        this.ERROR_CODE = ERROR_CODE == null ? null : ERROR_CODE.trim();
        return this;
    }

    public String getOPERATE_NAME() {
        return OPERATE_NAME;
    }

    public OperateLog setOPERATE_NAME(String OPERATE_NAME) {
        this.OPERATE_NAME = OPERATE_NAME == null ? null : OPERATE_NAME.trim();
        return this;
    }

    public String getOPERATE_CONDITION() {
        return OPERATE_CONDITION;
    }

    public OperateLog setOPERATE_CONDITION(String OPERATE_CONDITION) {
        this.OPERATE_CONDITION = OPERATE_CONDITION == null ? null : OPERATE_CONDITION.trim();
        return this;
    }

    public int getOPERATE_NUMBER() {
        return OPERATE_NUMBER;
    }

    public OperateLog setOPERATE_NUMBER(int OPERATE_NUMBER) {
        this.OPERATE_NUMBER = OPERATE_NUMBER;
        return this;
    }

    public String getOPERATE_TABLE() {
        return OPERATE_TABLE;
    }

    public OperateLog setOPERATE_TABLE(String OPERATE_TABLE) {
        this.OPERATE_TABLE = OPERATE_TABLE == null ? null : OPERATE_TABLE.trim();
        return this;
    }

    public String getOPERATE_KEY() {
        return OPERATE_KEY;
    }

    public OperateLog setOPERATE_KEY(String OPERATE_KEY) {
        this.OPERATE_KEY = OPERATE_KEY == null ? null : OPERATE_KEY.trim();
        return this;
    }

    public Date getINSERT_TIME() {
        return INSERT_TIME;
    }

    public OperateLog setINSERT_TIME(Date INSERT_TIME) {
        this.INSERT_TIME = INSERT_TIME;
        return this;
    }

    public String getCOLLECT_TYPE() {
        return COLLECT_TYPE;
    }

    public OperateLog setCOLLECT_TYPE(String COLLECT_TYPE) {
        this.COLLECT_TYPE = COLLECT_TYPE == null ? null : COLLECT_TYPE.trim();
        return this;
    }

    public String getSENDID() {
        return SENDID;
    }

    public OperateLog setSENDID(String SENDID) {
        this.SENDID = SENDID == null ? null : SENDID.trim();
        return this;
    }

    @Override
    public String toString() {
        return "OperateLog{" +
                "NUM_ID='" + NUM_ID + '\'' +
                ", REG_ID='" + REG_ID + '\'' +
                ", USER_ID='" + USER_ID + '\'' +
                ", ORGANIZATION='" + ORGANIZATION + '\'' +
                ", ORGANIZATION_ID='" + ORGANIZATION_ID + '\'' +
                ", USER_NAME='" + USER_NAME + '\'' +
                ", TERMINAL_ID='" + TERMINAL_ID + '\'' +
                ", OPERATE_TIME='" + OPERATE_TIME + '\'' +
                ", OPERATE_TYPE=" + OPERATE_TYPE +
                ", OPERATE_RESULT='" + OPERATE_RESULT + '\'' +
                ", ERROR_CODE='" + ERROR_CODE + '\'' +
                ", OPERATE_NAME='" + OPERATE_NAME + '\'' +
                ", OPERATE_CONDITION='" + OPERATE_CONDITION + '\'' +
                ", OPERATE_NUMBER=" + OPERATE_NUMBER +
                ", OPERATE_TABLE='" + OPERATE_TABLE + '\'' +
                ", OPERATE_KEY='" + OPERATE_KEY + '\'' +
                ", INSERT_TIME=" + INSERT_TIME +
                ", COLLECT_TYPE='" + COLLECT_TYPE + '\'' +
                ", SENDID='" + SENDID + '\'' +
                '}';
    }
}