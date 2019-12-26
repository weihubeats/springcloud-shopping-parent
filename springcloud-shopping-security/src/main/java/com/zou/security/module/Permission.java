package com.zou.security.module;

import lombok.Data;

/**
 * @author WH
 * @version 1.0
 * @date 2019/12/26 19:32
 * 权限
 */
@Data
public class Permission {
    private Integer id;
    /**
     * 权限名称
     */
    private String permName;
    /**
     * 权限标识
     */
    private String permTag;
    /**
     * 请求url
     */
    private String url;
}
