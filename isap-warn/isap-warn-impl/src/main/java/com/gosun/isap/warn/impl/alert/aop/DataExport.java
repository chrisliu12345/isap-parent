package com.gosun.isap.warn.impl.alert.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>创建时间：2017-6-15 10:57</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Target({ ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataExport {
}
