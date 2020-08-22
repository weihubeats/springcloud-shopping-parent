package com.zou.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author WH
 * @version 1.0
 * @date 2020/8/22 8:53
 * @Description TODO
 */
@TableName("t_order")
@Data
@ToString
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userId;

}
