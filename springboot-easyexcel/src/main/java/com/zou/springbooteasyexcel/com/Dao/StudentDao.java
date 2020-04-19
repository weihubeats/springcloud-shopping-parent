package com.zou.springbooteasyexcel.com.Dao;

import com.zou.springbooteasyexcel.com.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/4/18 20:59
 * @Description TODO
 */
@Repository
public class StudentDao {

    public void save(List<Student> students) {
        System.out.println("开始存储数据");
        students.forEach(s -> System.out.println("存储数据：" + s));
    }
}
