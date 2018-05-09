package com.yx.service;

import com.yx.bean.Person;
import com.yx.utils.LogUtil;
import org.msgpack.MessagePack;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConsumeOne implements MessageListener {

    private MessagePack msgPack;

    @PostConstruct
    public void init() {
        msgPack = new MessagePack();
    }

    @Override
    public void onMessage(Message message) {
        try {
            byte[] data = message.getBody();
            Person person = msgPack.read(data, Person.class);
            System.out.println(person);
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }
}