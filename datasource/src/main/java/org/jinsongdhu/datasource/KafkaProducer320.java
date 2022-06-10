package org.jinsongdhu.datasource;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jinsongdhu.entity.ExampleEventLogEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class KafkaProducer320 {

    public static void main(String[] args) {

        //topic
        String topic_name = "topic_test";

        //producer
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        Producer producer = new KafkaProducer(properties);

        //messages
        int msg_nums = 10;

        while(msg_nums>0) {
            String msg = getRandomMessage();
            System.out.println(msg);
            //produce
            ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>(topic_name,msg);
            producer.send(producerRecord);
            msg_nums--;
        }





        producer.close();

    }

    static public String getRandomMessage(){

        List<String> dim1list = new ArrayList<String>();
        dim1list.add("dim1-a");
        dim1list.add("dim1-b");
        dim1list.add("dim1-c");

        List<String> dim2list = new ArrayList<String>();
        dim2list.add("dim2-a");
        dim2list.add("dim2-b");
        dim2list.add("dim2-c");

        List<String> dim3list = new ArrayList<String>();
        dim3list.add("dim3-a");
        dim3list.add("dim3-b");
        dim3list.add("dim3-c");

        Random random = new Random();

        ExampleEventLogEntity exampleEventLogEntity = new ExampleEventLogEntity();
        exampleEventLogEntity.setDims1(dim1list.get(random.nextInt(dim1list.size())));
        exampleEventLogEntity.setDims2(dim2list.get(random.nextInt(dim2list.size())));
        exampleEventLogEntity.setDims3(dim3list.get(random.nextInt(dim3list.size())));
        exampleEventLogEntity.setTarget1(random.nextInt(100));
        exampleEventLogEntity.setEventTime(System.currentTimeMillis());

        return exampleEventLogEntity.toString();

    }
}
