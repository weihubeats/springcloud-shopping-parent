package com.zou.dao.entity;

import com.sun.tools.corba.se.idl.constExpr.Or;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : wh
 * @date : 2021/5/21 10:31
 * @description:
 */
@Data
public class Order {

    private Integer customerId;

    private Integer buyDate;

    private String cardNo;

    private BigDecimal payMoney;

    private BigDecimal originMoney;

    private String storeNo;

    private String storeUserNo;

    private String orderNo;

    private Integer year;

    private Integer month;

    private Integer day;

    private String payWay;

    private Date createDate;

    private Integer sign;

    private Integer version;


}
