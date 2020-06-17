package com.redis.config;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author WH
 * @version 1.0
 * @date 2020/5/7 0:12
 * @Description TODO
 */
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener   {


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        String orderCode = message.toString();

        System.out.println("过期的订单号是: " + orderCode);

    }

}
