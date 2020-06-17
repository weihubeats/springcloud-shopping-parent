package com.zou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zou.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @author WH
 * @version 1.0
 * @date 2020/5/5 11:23
 * @Description 继承基础 BaseMapper 就可以实现简单的CRUD了
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    IPage<User> selectPage(Page<User> page,
                           @Param("User") User user);

}
