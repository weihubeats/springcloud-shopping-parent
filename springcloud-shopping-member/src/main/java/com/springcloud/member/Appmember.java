package com.springcloud.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/30 19:55
 */
@SpringBootApplication
@EnableEurekaClient
public class Appmember {

    public static void main(String[] args) {
        SpringApplication.run(Appmember.class, args);
    }
}
