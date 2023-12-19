//package com.zq.project.kafkastream;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.*;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.Properties;
//
///**
// * @author zhangqian
// * @date 2023/3/16 16:48
// * @description:
// */
//@Component
//@Slf4j
//public class KafkaStream {
//
//    private KafkaStreams kafkaStreams;
//
//    @PostConstruct
//    public void onApplicationEvent() {
//        try {
//            StreamsBuilder streamsBuilder=new StreamsBuilder();
//            streamsBuilder.stream("connect_source_kafka_stream_data").map((k,v) -> {
//                return new KeyValue<Object,String>(k,v+"123456");
//            }).to("connect_sink_kafka_stream_data");
//            Topology build = streamsBuilder.build();
//            kafkaStreams=new KafkaStreams(build,getProperties());
//            kafkaStreams.start();
//        } catch (Exception e) {
//            log.error("kafka-stream转换出现异常!"+e.getMessage(),e);
//        }
//    }
//
//    private Properties getProperties(){
//        Properties properties=new Properties();
//        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG,"kafka-stream消费组");
//        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"172.17.1.36:30092");
//        properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        return properties;
//    }
//
//    @PreDestroy
//    private void destroy(){
//        if (kafkaStreams!=null){
//            kafkaStreams.close();
//        }
//    }
//}
