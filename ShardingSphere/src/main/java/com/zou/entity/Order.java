package com.zou.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author WH
 * @version 1.0
 * @date 2020/7/19 13:51
 * @Description TODO
 */
@Data
@ToString
public class Order {

    private String id;
    private Long userId;
    private Long orderId;
    private String userName;

}
