package com.weaver.police.dao;

import com.weaver.police.bean.OperateLog;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * @Author      :wyl
 * @Date        :2019/4/12  18:11
 * @Version 1.0 :
 * @Description :
 **/
@Component
public interface OperateLogDao {

    /**
     * 单个对象插入
     * @param operateLog
     * @return
     * @throws Exception
     */
    public boolean insert(OperateLog operateLog) throws Exception;

    /**
     * 批量插入
     * @param operateLogs
     * @return
     * @throws Exception
     */
    public boolean insertWithBatch(List<OperateLog> operateLogs) throws Exception;

    /**
     * 根据日期批量插入数据
     * @param operateLogs
     * @return
     * @throws Exception
     */
    public boolean insertWithBatchAndDay(List<OperateLog> operateLogs) throws Exception;
}
