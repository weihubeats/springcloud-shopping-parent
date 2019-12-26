package com.zou.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author WH
 * @version 1.0
 * @date 2019/12/26 19:47
 */
@Controller
public class ErrorController {

	// 403权限不足页面
	@RequestMapping("/error/403")
	public String error() {
		return "/error/403";
	}

}
