package com.mybatis.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;


/**
 * <p>Project:com.midea.jr <p>
 * <p>Module:finance.platform.war <p>
 * <p>Description:事务配置 <p>
 *
 * @author cengzh
 * @date 2018年3月24日 上午9:40:13
 */
//@Aspect
//@Component
//@Order(2)
@Slf4j
public class AopConfig {
	/*
	 *  第一个*号：表示返回类型， *号表示所有的类型
	 * com.mybatis.service表示需要拦截的包名，后面的两个点表示当前包和当前包的所有子包
	 * 第二个*号：表示类名，*号表示所有的类
	 *    *(..)：最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数*/
	private static final String POINT_CUT = "execution(* com.mybatis.service..*.*(..))";
	//获取换行
	private static final String SEPARATOR = System.getProperty("line.separator");

	//aop拦截路径配置 定义切面
	@Pointcut(POINT_CUT)
	private void pointcut(){}

	@Before(value = "pointcut()")
	public void allBefore(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		StringBuilder sb = new StringBuilder();
		//日志打印
        sb.append(SEPARATOR)
				.append("所属类方法:"+className)
				.append("."+methodName)
				.append(SEPARATOR)
				.append("输入参数params:");
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
            sb.append(JSONObject.toJSONString(arg) + ", ");
		}
		log.info(sb.toString());
	}

	@AfterReturning(value = "pointcut()", returning = "returnObj")
	public void afterReturn(Object returnObj) {
		StringBuilder sb = new StringBuilder();
		sb.append(SEPARATOR);
		if (returnObj != null) {
			 //TODO 处理不要打印所有日志
			if (returnObj instanceof List) {
				List list = (List) returnObj;
				sb.append("返回参数params数据条数: " + list.size());
			} else {
				String result = JSONObject.toJSONString(returnObj);
				sb.append("返回参数params: " + result);
			}
		}
        log.info(sb.toString());
	}

	@AfterThrowing(value ="pointcut()", throwing = "e")
	public void afterThrowing(Throwable e) {
        log.error(e.getMessage(), e);
	}

	@Around(value = "pointcut()")
	public Object allAround(ProceedingJoinPoint joinPoint)throws Throwable {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();

		long begin = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		Object result = joinPoint.proceed();
		long end =  System.currentTimeMillis();
		sb.append(SEPARATOR)
				.append("所属类方法:"+className)
				.append("."+methodName)
				.append(SEPARATOR)
				.append("环绕通知: ")
				.append("执行时间: ").append(end-begin).append("ms");
        log.info(sb.toString());
		return result;
	}
}
