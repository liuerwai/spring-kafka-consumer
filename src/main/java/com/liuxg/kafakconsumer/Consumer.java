package com.liuxg.kafakconsumer;

import com.liuxg.kafakconsumer.DealConsumerMessageDao.IKafkaConsumerDeal;
import com.liuxg.kafakconsumer.DealConsumerMessageImpl.CdhourseKafkaConsuerDeal;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Consumer {

    Map<String, IKafkaConsumerDeal> delas = new ConcurrentHashMap<>();

    @Autowired
    CdhourseKafkaConsuerDeal cdhourseKafkaConsuerDeal;

    @KafkaListener(topics = "error")
    public void receive(ConsumerRecord<String, String> record) {
        if (delas.containsKey(record.key())) {
            delas.get(record.key()).dealRecords(record);
        }
    }

    @PostConstruct
    public void init() {
        delas.put(cdhourseKafkaConsuerDeal.getKey(), cdhourseKafkaConsuerDeal);
    }
    
}
