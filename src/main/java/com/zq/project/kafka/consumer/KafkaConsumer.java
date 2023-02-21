package com.zq.project.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangqian
 * @date 2023/2/21 19:27
 * @description:
 */
@Slf4j
@Component
public class KafkaConsumer {

//    @KafkaListener(topics = "zq-test")
    public void kafkaListenser(ConsumerRecord<?, ?> record){
        System.out.println("监听到消息========"+record.value().toString());
    }
}
