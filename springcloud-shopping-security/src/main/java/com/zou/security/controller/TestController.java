package com.zou.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-25 9:38
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello,spirng security!";
    }


}
