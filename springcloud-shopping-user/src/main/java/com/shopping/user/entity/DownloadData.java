package com.shopping.user.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/20 21:10
 */
@Data
public class DownloadData {
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
}
