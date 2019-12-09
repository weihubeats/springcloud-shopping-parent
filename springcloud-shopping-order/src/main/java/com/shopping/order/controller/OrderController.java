package com.shopping.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/30 20:28
 */
@RestController
public class OrderController {

    //RestTemplate 是由springboot Web组件提供默认整合ribbom负载均衡器
    //rest底层采用的是httpclient
    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/getOrder")
    public String getOrder() {
//        String memberUrl = "http://127.0.0.1:9100/getmember";
        String memberUrl = "http://shopping-member/getmember";
        String result = restTemplate.getForObject(memberUrl, String.class);
        System.out.println(result);
        return  result;

    }
}
