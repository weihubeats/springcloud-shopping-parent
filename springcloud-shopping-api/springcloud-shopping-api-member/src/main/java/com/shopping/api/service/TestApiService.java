package com.shopping.api.service;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/20 20:29
 */
@RequestMapping("/member/test")
public interface TestApiService {

    @RequestMapping("/test")
    public Map<String, Object> test(Integer id, String name);
}
