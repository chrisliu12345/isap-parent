package com.gosun.isap.operlog.api.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 记录系统操作日志
 * 
 * @author liuzk
 *
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface SysOperateLog {
	/**
	 * 操作类型，取值为 com.gosun.isap.operlog.api.OperateType
	 * 
	 * @return 操作类型
	 */
	int operateType();

	/**
	 * 业务类型，取值为com.gosun.isap.operlog.api.ServiceType
	 * 
	 * @return 业务类型
	 */
	int serviceType();

	/**
	 * 操作描述
	 * 
	 * @return 操作描述信息
	 */
	String description();
}
