package com.zou.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-25 9:37
 */
@SpringBootApplication
@MapperScan("com.zou.security.mapper")
public class SecurityApplication {


    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
