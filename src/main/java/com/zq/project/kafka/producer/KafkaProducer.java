package com.zq.project.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author zhangqian
 * @date 2023/2/21 19:21
 * @description: kafka生产者
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

//    @Autowired
//    private KafkaAdminClient kafkaAdminClient;

    public boolean sendMessage(String topic,String message){
        try {
            kafkaTemplate.send(new ProducerRecord<>(topic,message));
//            kafkaTemplate.send(topic, "data",message);
        }catch (Exception e){
            log.error("kafka推送消息出现异常!"+e.getMessage(),e);
            return false;
        }
        return true;
    }
}
