package com.gosun.isap.operlog.api.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.common.utils.JsonUtils;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;

/**
 * 操作日志切面
 * 
 * @author liuzk
 *
 */
@Component
@Aspect
@SuppressWarnings("rawtypes")
public class SysOperateLogAspect {
	private static final Logger logger = LoggerFactory.getLogger(SysOperateLogAspect.class);

	/**
	 * 写操作日志，对所有com.gosun.isap下的使用了SysOperateLog注解的方法生效
	 * 
	 * @param jp
	 *            join point
	 * @param rl
	 *            日志注解
	 * @return object
	 * @throws Throwable
	 *             异常
	 */
	@Around("within(com.gosun.isap..*) && @annotation(rl)")
	public Object writeOperateLog(ProceedingJoinPoint jp, SysOperateLog rl) throws Throwable {
		String className = jp.getTarget().getClass().toString();// 获取目标类名
		className = className.substring(className.indexOf("com"));
		String signature = jp.getSignature().toString();// 获取目标方法签名
		String methodName = signature.substring(signature.lastIndexOf(".") + 1, signature.indexOf("("));
		Object[] parames = jp.getArgs();// 获取目标方法体参数
		String params = parseParames(parames); // 解析目标方法体的参数

		int serviceType = rl.serviceType();
		int operateType = rl.operateType();
		String description = rl.description();

		StringBuilder sbLogDetail = new StringBuilder();
		sbLogDetail.append(description).append(", 参数为:").append(params);

		Object object;
		try {
			object = jp.proceed();
			if (object != null && object instanceof ResponseResult) {
				int code = ((ResponseResult) object).getHead().getErrorCode();
				if (code == ErrorCode.ERR_OK) {
					success(serviceType, operateType, sbLogDetail.toString());
				} else {
					failed(serviceType, operateType, sbLogDetail.toString(),
							((ResponseResult) object).getHead().getMessage());
				}
			} else {
				success(serviceType, operateType, sbLogDetail.toString());
			}
		} catch (Throwable throwable) {
			failed(serviceType, operateType, sbLogDetail.toString(), throwable.getMessage());
			throw throwable;
		}
		return object;
	}

	/**
	 * 解析方法参数
	 * 
	 * @param parames
	 *            方法参数
	 * @return 解析后的方法参数
	 */
	private String parseParames(Object[] parames) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < parames.length; i++) {
			try {
				String json = JsonUtils.obj2json(parames[i]);
				if (i == parames.length - 1) {
					sb.append(json);
				} else {
					sb.append(json + ",");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String params = sb.toString();
		params = params.replaceAll("(\"\\w+\":\"\",)", "");
		params = params.replaceAll("(,\"\\w+\":\"\")", "");
		return params;
	}

	private void success(int serviceType, int operateType, String description) {
		try {
			OperateLogWriter.success(serviceType, operateType, description);
		} catch (Exception e) {
			logger.error("Write operate log error ", e);
		}
	}

	private void failed(int serviceType, int operateType, String description, String reason) {
		try {
			OperateLogWriter.fail(serviceType, operateType, description, reason);
		} catch (Exception e) {
			logger.error("Write operate log error ", e);
		}
	}
}
