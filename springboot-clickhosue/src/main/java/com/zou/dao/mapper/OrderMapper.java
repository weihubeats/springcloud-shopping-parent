package com.zou.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zou.dao.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author : wh
 * @date : 2021/5/21 10:33
 * @description:
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
