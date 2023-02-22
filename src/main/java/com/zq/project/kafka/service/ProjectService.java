package com.zq.project.kafka.service;

/**
 * @author zhangqian
 * @date 2023/2/21 19:56
 * @description:
 */
public interface ProjectService {

    /**
     * kafka推送消息
     * @param topic
     * @param message
     * @return
     */
    boolean sendMessage(String topic,String message);
}
