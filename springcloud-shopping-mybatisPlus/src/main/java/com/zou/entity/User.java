package com.zou.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author WH
 * @version 1.0
 * @date 2020/5/5 11:21
 * @Description TODO
 */
@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    @TableField(update = "now()")
    private LocalDateTime updateTime;
}
