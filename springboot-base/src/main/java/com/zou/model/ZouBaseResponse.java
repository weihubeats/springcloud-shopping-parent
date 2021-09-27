package com.zou.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : wh
 * @date : 2021/6/1 19:47
 * @description: 基础返回类
 */
@Data
public class ZouBaseResponse implements Serializable {

    /**
     * 响应状态码
     */
    private String code;

    /**
     * 响应描述
     */
    private String msg;

    /**
     * 响应业务数据
     */
    private Object data;
}
