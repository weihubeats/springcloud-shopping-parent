package rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/23 13:07
 */
@Component
@RabbitListener(queues = "email_fanout")
public class EmailConsumer {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("邮件队列接受到的消息为" + msg);

    }
}
