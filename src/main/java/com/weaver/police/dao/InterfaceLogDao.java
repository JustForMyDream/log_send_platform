package com.weaver.police.dao;


import com.weaver.police.bean.InterfaceLog;
import org.springframework.stereotype.Component;

/*
 * @Author      :wyl
 * @Date        :2019/4/15  16:43
 * @Version 1.0 :
 * @Description :
 **/
@Component
public interface InterfaceLogDao {

    public boolean insert(InterfaceLog interfaceLog) throws Exception;
}
