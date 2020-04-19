package com.zou.springbooteasyexcel.com.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author WH
 * @version 1.0
 * @date 2020/4/17 23:12
 * @Description TODO
 */
@Data
public class Student {

    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private Integer age;
    @ExcelProperty("成绩")
    private BigDecimal score;


}
