package com.weaver.police.excuter;

import com.weaver.police.constant.PoliceConstant;
import com.weaver.police.util.TableUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author      :wyl
 * @Date        :2019/4/24  17:03
 * @Version 1.0 :
 * @Description :定时创建table
 **/

@Configuration
@EnableScheduling
@Component
public class CreateTableTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTableTask.class);

    public void execute(){

        List tableList = getTableList(PoliceConstant.OPERATE_LOG);

        LOGGER.info("<=============定时创建表开始============>");
        TableUtil.execute(PoliceConstant.OPERATE_LOG,tableList,PoliceConstant.OPERATE_LOG);






    }


    private List<String> getTableList(String type){

        List<String> list = new ArrayList<>();
        if (StringUtils.equals(PoliceConstant.INTERFACE_LOG,type)){
            list.add("  id                  NUMBER(20) not null,");
            list.add("  num_id              varchar2(32) not null,");
            list.add("  reg_id              VARCHAR2(120) not null,");
            list.add("  user_id             VARCHAR2(180),");
            list.add("  organization        VARCHAR2(1000),");
            list.add("  organization_id     VARCHAR2(120),");
            list.add("  user_name           VARCHAR2(600),");
            list.add("  terminal_id         VARCHAR2(400) not null,");
            list.add("  interface_time      VARCHAR2(14) not null,");
            list.add("  requester           VARCHAR2(1000) not null,");
            list.add("  interface_result    VARCHAR2(1) not null,");
            list.add("  error_code          VARCHAR2(4),");
            list.add("  interface_name      VARCHAR2(1000),");
            list.add("  interface_condition VARCHAR2(4000),");
            list.add("  interface_number    NUMBER(20),");
            list.add("  interface_table     VARCHAR2(2000),");
            list.add("  interface_key       VARCHAR2(2000),");
            list.add("  insert_time         DATE default SYSDATE,");
            list.add("  collect_type        VARCHAR2(10) default ('2'),");
            list.add("  sendid              VARCHAR2(320)");
        }else if (StringUtils.equals(PoliceConstant.OPERATE_LOG,type)){
            list.add("  id                NUMBER(13) not null,");
            list.add("  num_id            varchar2(32) not null,");
            list.add("  reg_id            VARCHAR2(12) not null,");
            list.add("  user_id           VARCHAR2(18) not null,");
            list.add("  organization      VARCHAR2(100) not null,");
            list.add("  organization_id   VARCHAR2(12),");
            list.add("  user_name         VARCHAR2(60) not null,");
            list.add("  terminal_id       VARCHAR2(40) not null,");
            list.add("  operate_time      CHAR(14) not null,");
            list.add("  operate_result    CHAR(1) not null,");
            list.add("  error_code        CHAR(4),");
            list.add("  operate_name      VARCHAR2(60),");
            list.add("  operate_condition VARCHAR2(4000),");
            list.add("  operate_number    NUMBER(13),");
            list.add("  operate_table     VARCHAR2(200),");
            list.add("  operate_key       VARCHAR2(200),");
            list.add("  insert_time       DATE default SYSDATE,");
            list.add("  insert_time       DATE default SYSDATE,");
            list.add("  collect_type      CHAR(1) default ('2'),");
            list.add("  sendid            VARCHAR2(32)");
        }

        return list;
    }

}
