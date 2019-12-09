package com.shopping.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/19 20:07
 */
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    //注入到spring容器中
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
