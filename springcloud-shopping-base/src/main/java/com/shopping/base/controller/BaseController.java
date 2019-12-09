package com.shopping.base.controller;

import entity.ResponseBase;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/19 20:16
 */
@RestController
@CrossOrigin
@RequestMapping("/laber")
public class BaseController extends entity.BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseBase findAll() {
        return setResultSucess();
    }
}
