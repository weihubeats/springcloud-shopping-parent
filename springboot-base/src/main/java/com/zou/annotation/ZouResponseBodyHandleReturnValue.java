package com.zou.annotation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zou.model.ZouBaseResponse;
import org.springframework.core.MethodParameter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : wh
 * @date : 2021/6/1 19:46
 * @description:
 */
public class ZouResponseBodyHandleReturnValue implements HandlerMethodReturnValueHandler, AsyncHandlerMethodReturnValueHandler {


    @Override
    public boolean isAsyncReturnValue(Object returnValue, MethodParameter returnType) {
        return supportsReturnType(returnType);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getParameterType() != ZouBaseResponse.class
                && !ObjectUtils.isEmpty(returnType.getAnnotatedElement().getAnnotation(ZouResponseBody.class));

    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        assert response != null;
        response.setContentType("application/json;charset=utf-8");
        ZouBaseResponse baseResponse = new ZouBaseResponse();
        baseResponse.setCode("200");
        baseResponse.setMsg("success");
        baseResponse.setData(returnValue);

        ZouResponseBody responseBody = returnType.getAnnotatedElement().getAnnotation(ZouResponseBody.class);

        SerializerFeature[] defaultSerializerFeature = {
                SerializerFeature.DisableCircularReferenceDetect
        };
        /**
         * 替换注解中的序列化格式
         */
        if (!ObjectUtils.isEmpty(responseBody) && !ObjectUtils.isEmpty(responseBody.serializerFeature())) {
            defaultSerializerFeature = responseBody.serializerFeature();
        }
        response.getWriter().write(JSON.toJSONString(baseResponse, defaultSerializerFeature));

    }
}
