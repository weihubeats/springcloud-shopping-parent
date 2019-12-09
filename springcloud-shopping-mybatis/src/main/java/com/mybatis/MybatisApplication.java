package com.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication( scanBasePackages = { "com.mybatis" })
public class MybatisApplication {

    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(intList)){
            //TODO
        }
        //而不是
        if (intList != null && intList.size() > 0){

        }
        String str = "";
        if (StringUtils.hasText(str)){

        }
        //而不是
        if (str != null && str.length() > 0){

        }
        SpringApplication.run(MybatisApplication.class, args);
    }

}
