package vip.dtcloud.extractor.snmp;

import vip.dtcloud.extractor.snmp.configuration.Configuration;
import vip.dtcloud.extractor.snmp.info.Message;
import vip.dtcloud.extractor.snmp.info.SnmpServiceReturnMesage;
import vip.dtcloud.extractor.snmp.kafka.TraditionalAvroProducer;
import vip.dtcloud.extractor.snmp.snmp.SnmpManagerImpl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Entry class.
 *
 * @author HuangYao 2019/8/2 13:47
 * @version v1.0
 * @since v1.0
 **/
public class Application {

    public static void main(String[] args) throws IOException {

        //读取所有Ip的配置文件
        Properties props = Configuration.getConf("object-ip.properties");
        Set<Object> keys = props.keySet();
        //创建线程池
        ScheduledThreadPoolExecutor executorDtc = new ScheduledThreadPoolExecutor(keys.size());
        //遍历每个IP
        for(Object key:keys){
            //加入一个线程执行
            executorDtc.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    //获取时间
                    String time = System.currentTimeMillis()+"";
                    //获取IP
                    String ip = key.toString();
                    //获取OID文件的路径
                    String path = props.getProperty(ip);
                    Properties prop = Configuration.getConf(path);
                    Enumeration<?> e = prop.propertyNames();
                    //创建SNMP采集对象
                    SnmpManagerImpl smi = new SnmpManagerImpl();
                    //创建kafka生产者
                    TraditionalAvroProducer producer = new TraditionalAvroProducer();
                    //遍历oid进行采集
                    while (e.hasMoreElements()) {
                        String oid = (String) e.nextElement();
                        String code = prop.getProperty(oid);
                        SnmpServiceReturnMesage result = smi.walk(oid, "public", ip,161);
                        String answer = result.getComplexResultObject().toString();
                        Message message = new Message();
                        //判断是否有多个值
                        if(answer.contains(",")) {
                            String[] splits = answer.split(",");
                            for (String s : splits) {
                                String str = s.replace("[", "").replace("]", "").replace("OID:", "").replace("Value :", "").trim();
                                String[] strs = str.split(" ");
                                //获取指标编号
                                String number = strs[0].substring(strs[0].lastIndexOf(".")).trim();
                                String code1= code+number;
                                //获取指标值
                                String value = strs[1].trim();
                                message.setCode(code1);
                                message.setHost(ip);
                                message.setTime(time);
                                message.setValue(value);
                                producer.producerKafka(message.toJSON());
                                //System.out.println(message.toJSON());
                                //System.out.println(code1+"......"+value+"......"+time);
                            }
                        }else{
                            String value = answer.replace("]", "").substring(answer.lastIndexOf(":")+1).trim();
                            message.setCode(code);
                            message.setHost(ip);
                            message.setTime(time);
                            message.setValue(value);
                            producer.producerKafka(message.toJSON());
                            //System.out.println(message.toJSON());
                           // System.out.println(code+"......"+value+"......"+time);
                        }

                    }
                    producer.closeProducer();
                }
            },0,5000, TimeUnit.MILLISECONDS);
        }
    }
}