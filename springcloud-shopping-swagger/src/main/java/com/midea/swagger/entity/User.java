package com.midea.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-19 17:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户实体", description = "User Entity")
public class User implements Serializable {
    private static final long serialVersionUID = 5057954049311281252L;
    @ApiModelProperty(value = "主键id", required = true)
    private Integer id;
    @ApiModelProperty(value = "用户名", required = true)
    private String name;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
