package com.gosun.isap.warn.impl.alert.base;

import java.io.IOException;

/**
 * <p>创建时间：2017-6-10 17:31</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface ReportHelper {
    void export(String path) throws IOException;
    void saveResult(String methodName, Object object, Object... params);
    void saveResult(boolean useLastResult,String methodName,Object object, Object... params);
    void clearReport();
}
