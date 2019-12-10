package com.redis.controller;

import com.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WH
 * @version 1.0
 * @date 2019/12/10 21:21
 */
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    //添加
    @GetMapping(value="/redisAdd")
    public String saveRedis(){
        redisUtil.set("name", "小奏");
        return "success";

    }

    //获取
    @GetMapping(value="/redisGet")
    public String getRedis(){
        return (String) redisUtil.get("name");
    }

}
