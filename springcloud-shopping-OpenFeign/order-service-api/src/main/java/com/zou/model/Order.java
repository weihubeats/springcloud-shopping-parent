package com.zou.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author WH
 * @version 1.0
 * @date 2020/9/12 22:47
 * @Description TODO
 */
@Data
public class Order {

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 商品数量
     */
    private Integer orderCount;
}
