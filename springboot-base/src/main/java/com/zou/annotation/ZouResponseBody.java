package com.zou.annotation;

import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.annotation.*;

/**
 * @author : wh
 * @date : 2021/6/1 19:33
 * @description: 自定义返回注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZouResponseBody {

    SerializerFeature[] serializerFeature() default {};
}
