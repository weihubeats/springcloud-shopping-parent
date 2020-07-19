package com.zou.dao;

import com.zou.entity.OrderConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/7/19 14:03
 * @Description TODO
 */
@Repository
public interface OrderConfigDao {


    List<OrderConfig> getOrderConfig();


}
