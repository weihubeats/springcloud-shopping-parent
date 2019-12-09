package rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.MQConnectionUtils;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/21 22:21
 */
//生成者  交换机类型为Topics类型
public class TopicsProducer {

    //交换机名称
    private static final String DESTINATION_NAME = "topics_destination";
    //routing key
    private static final String ROUTING_KEY = "zou.insert ";
    private static final String ROUTING_KEY1 = "zou.insert.more";

    public static void main(String[] args) throws Exception{
        //1.建立连接
        Connection connection = MQConnectionUtils.newConnection();
        //2. 创建通道
        Channel channel = connection.createChannel();
        //3.生产者绑定交换机 参数1. 交换机名称 2.交换机类型 direct
        channel.exchangeDeclare(DESTINATION_NAME, "topic");
        //4. 创建消息
        String msg = "topics_destination" + "发送消息";
        //5.发送消息 参数1为交换机名 2 为这里routingKey 3.为消息基本属性  4.为消息
        //这里绑定routing key为 severity
        channel.basicPublish(DESTINATION_NAME,ROUTING_KEY,null, msg.getBytes());
        //6. 关闭通道和连接
        channel.close();
        connection.close();
    }
}
