package com.zq.project.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Properties;

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

    private org.apache.kafka.clients.producer.KafkaProducer<String,Object> kafkaProducer;

    public KafkaProducer(KafkaProperties kafkaProperties){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        this.kafkaProducer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
    }
//    @Autowired
//    private KafkaAdminClient kafkaAdminClient;

    public boolean sendMessage(String topic,String message){
        try {
            kafkaProducer.send(new ProducerRecord<>(topic,message));
//            kafkaTemplate.send(topic, "data",message);
        }catch (Exception e){
            log.error("kafka推送消息出现异常!"+e.getMessage(),e);
            return false;
        }
        return true;
    }
}
