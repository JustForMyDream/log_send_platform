package com.weaver.police.dao;

import com.weaver.police.bean.OperateLog;
import org.springframework.stereotype.Component;

/*
 * @Author      :wyl
 * @Date        :2019/4/12  18:11
 * @Version 1.0 :
 * @Description :
 **/
@Component
public interface OperateLogDao {

    public boolean insert(OperateLog operateLog) throws Exception;
}
