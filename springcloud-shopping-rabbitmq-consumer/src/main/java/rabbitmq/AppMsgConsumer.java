package rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/23 13:12
 */
@SpringBootApplication
public class AppMsgConsumer {
    public static void main(String[] args) {
        SpringApplication.run(AppMsgConsumer.class, args);
    }
}
