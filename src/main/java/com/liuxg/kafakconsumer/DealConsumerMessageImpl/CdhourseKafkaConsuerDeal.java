package com.liuxg.kafakconsumer.DealConsumerMessageImpl;

import com.liuxg.kafakconsumer.DealConsumerMessageDao.IKafkaConsumerDeal;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class CdhourseKafkaConsuerDeal implements IKafkaConsumerDeal {

    private static final String project = "cdhourse";

    @Autowired
    MailSender mailSender;

    @Override
    public void dealRecords(ConsumerRecord record) {
        if (record.key().equals(project)) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setText(record.value().toString());
            message.setSubject("成都房产报错啦");
            message.setTo("liuxg@channelsoft.com");
            message.setFrom("liuxg@channelsoft.com");
            mailSender.send(message);
        }
    }

    @Override
    public String getKey() {
        return project;
    }
}
