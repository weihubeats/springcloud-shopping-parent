package com.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/23 12:56
 */
@Component
public class FanoutProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String queueName) {
        System.out.println("queueName" + queueName);
        String msg = "msg:" + new Date();
        //发送消息 如果使用routing key  只需要在中间加入参数即可
        //主题模式
//        amqpTemplate.convertAndSend(queueName, "topic.msm", msg);
        amqpTemplate.convertAndSend(queueName, msg);
    }
}
