package com.zq.project.kafka.service.impl;

import com.zq.project.kafka.producer.KafkaProducer;
import com.zq.project.kafka.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangqian
 * @date 2023/2/21 19:57
 * @description:
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public boolean sendMessage(String topic, String message) {
        return kafkaProducer.sendMessage(topic,message);
    }
}
