package com.shopping.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/19 21:15
 */
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    //密码加密解密
  /*  @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }*/
}
