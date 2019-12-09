package com.mybatis.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/24 22:14
 */
@Data
@NoArgsConstructor
@Component
//不使用这个注解默认读取application.yml
@PropertySource(value = "classpath:config.properties")
public class Teacher {
    @Value("${teacher.id}")
    private Integer id;
    @Value("${teacher.name}")
    private String name;
}
