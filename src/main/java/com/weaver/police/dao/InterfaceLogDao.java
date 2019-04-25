package com.weaver.police.dao;


import com.weaver.police.bean.InterfaceLog;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * @Author      :wyl
 * @Date        :2019/4/15  16:43
 * @Version 1.0 :
 * @Description :
 **/
@Component
public interface InterfaceLogDao {

    /**
     * 单个对象插入
     * @param interfaceLog
     * @return
     * @throws Exception
     */
    public boolean insert(InterfaceLog interfaceLog) throws Exception;

    /**
     * 批量插入
     * @param interfaceLogs
     * @return
     * @throws Exception
     */
    public boolean insertWithBatch(List<InterfaceLog> interfaceLogs) throws Exception;


    /**
     * 根据日期批量插入数据
     * @param interfaceLogs
     * @return
     * @throws Exception
     */
    public boolean insertWithBatchAndDay(List<InterfaceLog> interfaceLogs) throws Exception;
}
