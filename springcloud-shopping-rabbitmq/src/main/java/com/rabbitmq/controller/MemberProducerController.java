package com.rabbitmq.controller;

import com.rabbitmq.producer.FanoutProducer;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/23 13:00
 */
@RestController
public class MemberProducerController {
    @Autowired
    private FanoutProducer producer;

    @GetMapping("/sendMsg")
    public String send(String queueName) {
        producer.send(queueName);

        return "success";
    }
}
