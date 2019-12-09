package com.springcloud.member.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/30 19:53
 */
@RestController
public class MemberController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getmember")
    public String getmember() {
        return "调用会员服务" + port ;
    }
}
