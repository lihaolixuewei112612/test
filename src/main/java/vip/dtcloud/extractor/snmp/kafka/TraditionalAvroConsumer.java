package vip.dtcloud.extractor.snmp.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import vip.dtcloud.extractor.snmp.configuration.Configuration;
import vip.dtcloud.extractor.snmp.info.Message;
import java.util.Collections;
import java.util.Properties;

/**
 * @Title TraditionalAvroConsumer.java
 * @Description Kafka Consumer 解析avro序列化后的Stock对象
 * @Author RenZhiCheng
 * @Date 2018-06-21 17:43:03
 */
public class TraditionalAvroConsumer {

    public static void main(String[] args) {

        Properties props = Configuration.getConf("kafka-consumer.properties");
        /*
        props.put("bootstrap.servers", "10.3.1.60:9092,10.3.1.61:9092,10.3.1.62:9092");
        props.put("group.id", "kafkaDemo");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 设置反序列化类为自定义的avro反序列化类
        props.put("value.deserializer", "com.dtc.serializer.AvroDeserializer");
        */

        KafkaConsumer<String, Message> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList("snmpTest"));

        try {
            while (true) {
                ConsumerRecords<String, Message> records = consumer.poll(100);
                for (ConsumerRecord<String, Message> record : records) {
                    Message message = record.value();
                    System.out.println(message.toString());
                }
            }
        } finally {
            consumer.close();
        }
    }

}
