package com.rabbitmq.confing;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/23 12:21
 * 交换机类型为fanout
 */
@Component
public class FanoutConfig {
    //邮件队列
    private static final String EMAIL_QUEUE = "email_fanout";
    private static final String SMS_QUEUE = "msm_fanout";
    private static final String DESTINATION_NAME = "fanout_destination";

    //1.定义邮件队列
    @Bean
    public Queue fanouEmailQueue() {
        return new Queue(EMAIL_QUEUE);
    }

    //短信队列
    @Bean
    public Queue fanoutSmsQueue() {
        return new Queue(SMS_QUEUE);
    }
    //2. 定义交换机  这里交换机使用FanoutExchange  如果想使用其他模式只需要修改就交换机比如TopicExchange
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(DESTINATION_NAME);
    }

    //3. 队列和交换机进行绑定
    //这里参数的名字一定要和定义队列的名字一致
    @Bean
    Binding bindingExchangEamil(Queue fanouEmailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanouEmailQueue).to(fanoutExchange);
        //如果要加routing kye.
    }

    //主题模式 TopicExchange
    /*@Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(DESTINATION_NAME);
    }*/
   /* @Bean
    Binding bindingExchangEamil (Queue fanouEmailQueue, TopicExchange fanoutExchange){
        return BindingBuilder.bind(fanoutExchange).to(fanoutExchange).with("topic.#");
    }*/
    @Bean
    Binding bindingExchangSms(Queue fanoutSmsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutSmsQueue).to(fanoutExchange);
    }

}
