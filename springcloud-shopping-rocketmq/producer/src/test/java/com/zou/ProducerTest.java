package com.zou;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class ProducerTest
{

    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_consumer");
        // 设置NameServer地址
        consumer.setNamesrvAddr("49.233.150.105:9876");
        // 订阅Topic
        consumer.subscribe("TopicTest", "*");
        // 注册回调接口，获取消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println("接受到的消息为：" + msgs);
                msgs.forEach(s -> System.out.println(new String(s.getBody())));


                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

    }

}
