package com.zou.entity;

import lombok.Data;

/**
 * @author WH
 * @version 1.0
 * @date 2020/7/19 14:00
 * @Description TODO
 */
@Data
public class OrderConfig {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 支付超时时间
     *
     * 单位：分钟
     */
    private Integer payTimeout;
}
