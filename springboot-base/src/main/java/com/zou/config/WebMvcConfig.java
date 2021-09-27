package com.zou.config;

import com.zou.annotation.ZouResponseBodyHandleReturnValue;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author : wh
 * @date : 2021/6/1 19:52
 * @description: 全局异常处理，同时注册自定义返回值处理
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new ZouResponseBodyHandleReturnValue());
    }
}
