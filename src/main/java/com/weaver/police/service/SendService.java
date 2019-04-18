package com.weaver.police.service;

import org.springframework.stereotype.Component;

@Component
public interface SendService {

    public String setMessage(String prefix,String message);

    public Long sendMessage(String channelName,String message);
}
