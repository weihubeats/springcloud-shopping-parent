package com.zou.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/5/8 20:38
 * @Description TODO
 */
@Data
public class CommonPage<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;


    public static <T> CommonPage<T> restPage(List<T> list, Integer pageNum, Integer pageSize) {
        Page<T> page = new Page<>(pageNum, pageSize);
        return null;
    }
}
