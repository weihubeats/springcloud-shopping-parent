package com.zou.entity;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/19 20:24
 */
public class BaseController {
    //返回成功 无data
    public ResponseBase setResultSucess() {
        return setResultSucess(null);
    }

    //返回成功 有data
    public ResponseBase setResultSucess(Object object) {
        return new ResponseBase(true, StatusCode.OK, "成功", object);
    }

    //返回失败
    public ResponseBase setResultError() {
        return new ResponseBase(false, StatusCode.ERROR, "失败");
    }

    //返回自定义结果
    public ResponseBase setResultError(Integer code, String message) {
        return new ResponseBase(false, code , message);

    }
}
