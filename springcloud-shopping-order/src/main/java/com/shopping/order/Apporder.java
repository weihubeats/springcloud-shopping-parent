package com.shopping.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/30 20:25
 */
@SpringBootApplication
@EnableEurekaClient
public class Apporder {
    public static void main(String[] args) {
        SpringApplication.run(Apporder.class, args);
    }

    @Bean
    @LoadBalanced
    //LoadBalanced 开启负载均衡  使用rest使用别名调用必须开启
    public RestTemplate RestTemplate() {
        return new RestTemplate();
    }
}
