package com.weaver.police.constant;

public interface PoliceConstant {

    public static final String PUBLISH_WAY = "publish_way";//订阅模式

    public static final String TIMER_WAY  = "timer_way"; //定时推送

    public static final String REG_ID  = "440000100001";//参见附录B：应用系统/资源库标识

    public static final String INTERFACE_LOG  = "interface_log";//接口类型

    public static final String OPERATE_LOG  = "operate_log";//用户操作类型

    public static final String INTERFACE_LOG_PREFIX  = "interface_log_";//redis接口类型前缀

    public static final String OPERATE_LOG_PREFIX  = "operate_log_";//redis用户操作类型前缀

    public static final String OPERATE_LOG_CHANNEL  = "operate_log_channel";//redis  operate_log推送通道

    public static final String INTERFACE_LOG_CHANNEL  = "interface_log_channel";//redis interface_log推送通道
}
