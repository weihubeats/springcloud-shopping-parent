import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author WH
 * @version 1.0
 * @date 2020/9/3 22:32
 * @Description TODO
 */
public class ConsumerTest {

    public static void main(String[] args) throws Exception{
        final DefaultMQProducer producer = new DefaultMQProducer("test_product");
        // 配置NameServer 地址
        producer.setNamesrvAddr("49.233.150.105:9876");
        producer.start();
        //死循环发送消息
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        Message message = new Message("TopicTest", "Taga",
                                "ConsumerTest".getBytes(RemotingHelper.DEFAULT_CHARSET));
                        SendResult sendResult = producer.send(message);
                        // 通过sendResult返回消息是否成功送达
                        System.out.printf("%s%n", sendResult);
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
            thread.start();
            // 不让线程结束，一直发消息
            thread.join();

        }

    }
}
