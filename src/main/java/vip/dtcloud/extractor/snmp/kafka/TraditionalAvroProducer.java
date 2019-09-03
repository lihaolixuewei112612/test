package vip.dtcloud.extractor.snmp.kafka;

import vip.dtcloud.extractor.snmp.configuration.Configuration;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

/**
 * @Title TraditionalAvroProducer.java
 * @Description Kafka Producer 发送avro序列化后的Stock对象
 * @Author RenZhiCheng
 * @Date 2019-8-14 17:41:59
 */
public class TraditionalAvroProducer {

    private static Properties props;

    private static String topic;

    private Producer<String, String> producer;

    static {
        //获取kafka-peoducer的配置文件
        props = Configuration.getConf("kafka-producer.properties");
        topic = props.getProperty("topic");
    }


    public TraditionalAvroProducer() {
        this.producer=new KafkaProducer<>(props);

    }

    //   写入kafka
    public void producerKafka(String message){
        //Properties props = Configuration.getConf("kafka-producer.properties");
        //Producer<String, Message> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String > record = new ProducerRecord<>(topic, message);
        producer.send(record);
        //producer.close();
    }

    //关闭生产者
    public void closeProducer(){
        producer.close();
    }

}