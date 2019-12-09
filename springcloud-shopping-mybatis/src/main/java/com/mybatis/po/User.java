package com.mybatis.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/24 22:11
 */
@Data
@NoArgsConstructor
public class User {

    private Integer id;
    private String iphone;
    private String password;
    private String name;
    private String sex;

    public User(String iphone, String password, String name, String sex) {
        this.iphone = iphone;
        this.password = password;
        this.name = name;
        this.sex = sex;
    }
}
