package com.zq.project.kafkastream;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

/**
 * @author zhangqian
 * @date 2023/3/16 16:48
 * @description:
 */
public class KafkaStream {

    public static void main(String[] args) {
        StreamsBuilder streamsBuilder=new StreamsBuilder();
        KStream<Object, Object> stream = streamsBuilder.stream("connect_source_radar");

    }
}
