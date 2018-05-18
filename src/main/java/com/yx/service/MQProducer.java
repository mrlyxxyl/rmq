package com.yx.service;

import com.yx.utils.LogUtil;
import org.msgpack.MessagePack;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("mqProducer")
public class MQProducer {

    private MessagePack msgPack;
    private MessageProperties properties;

    @PostConstruct
    public void init() {
        msgPack = new MessagePack();
        properties = new MessageProperties();
        properties.setContentType(MessageProperties.CONTENT_TYPE_BYTES);
    }

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void sendObjectToQueue(String queueKey, Object object) {
        try {
            byte[] data = msgPack.write(object);
            Message message = new Message(data, properties);
            amqpTemplate.send(queueKey, message);
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }

    public void sendStringToQueue(String queueKey, String msg) {
        try {
            amqpTemplate.convertAndSend(queueKey, msg);
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }
}