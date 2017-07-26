package com.gosun.isap.warn.impl.alert.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 可恶的前端老这样传参：
 * ?group_id=&department_id=&sort=&start=0
 * 参数解析出来是一个空串而非 null。
 * <p>创建时间：2017-6-15 11:00</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Component
@Aspect
public class CheckEmptyParamsAspect {
    private static final String POINTCUT = "@within(com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams) "
            + "|| @annotation(com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams)";

    @Around(POINTCUT)
    public Object exportData(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        if (objects != null && objects.length > 0) {
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object != null && object instanceof String) {
                    if (((String) object).trim().isEmpty()) {
                        objects[i] = null;
                    }
                }
            }
        }
        return joinPoint.proceed(objects);
    }
}
