package com.gosun.isap.proxy.instance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gosun.isap.common.utils.JsonUtils;

/**
 * sdk调用日志记录切面
 * 
 * @author liuzk
 *
 */
@Aspect
@Component
public class SdkCallLoggerAspect {
	private static Logger logger = LoggerFactory.getLogger(SdkCallLoggerAspect.class);

	/**
	 * 切入点
	 */
	@Pointcut(value = "execution(public void com.gosun.isap.proxy.instance.NativeSdkCallerImpl.*(..))")
	public void pointCut() {

	}

	@Around(value = "pointCut()")
	private void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		logger.debug("--- Got a sdk call, method[" + method + "] ---");

		// 记录参数
		for (Object o : pjp.getArgs()) {
			try {
				String s = JsonUtils.obj2json(o);
				logger.debug("[" + method + "] -> argument: " + s);
			} catch (Exception e) {

			}
		}

		pjp.proceed();
	}

}
