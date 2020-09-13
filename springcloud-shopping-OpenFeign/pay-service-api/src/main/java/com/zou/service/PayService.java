package com.zou.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WH
 * @version 1.0
 * @date 2020/9/12 22:54
 * @Description TODO
 */
@RestController
@RequestMapping("/pay")
public interface PayService {

    /**
     * 支付接口
     * @return
     */
    @GetMapping()
    boolean pay();

}
