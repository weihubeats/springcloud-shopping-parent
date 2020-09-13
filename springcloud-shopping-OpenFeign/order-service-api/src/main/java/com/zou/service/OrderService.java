package com.zou.service;

import com.zou.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WH
 * @version 1.0
 * @date 2020/9/12 22:32
 * @Description TODO
 */
@RestController
@RequestMapping("/order")
public interface OrderService {


    @GetMapping(value = "/get-order",headers = {"Accept=application/json"})
    Order getOrder(@RequestParam("orderNo")String orderNo);




}
