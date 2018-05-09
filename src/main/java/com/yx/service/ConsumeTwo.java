package com.yx.service;

import com.yx.utils.LogUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumeTwo implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(new String(message.getBody()));
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }
}