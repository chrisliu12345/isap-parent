package com.gosun.isap.warn.impl.alert.aop;

import com.alibaba.dubbo.rpc.RpcContext;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.impl.alert.export.excel.DataExcelOutput;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>创建时间：2017-6-15 11:00</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Component
@Aspect
public class DataExportAspect {
    private static final String POINTCUT = "@within(com.gosun.isap.warn.impl.alert.aop.DataExport) "
            + "|| @annotation(com.gosun.isap.warn.impl.alert.aop.DataExport)";
    private static final String EXPORT = "export";
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    @Around(POINTCUT)
    public Object exportData(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object  = joinPoint.proceed();
        if(object instanceof ResponseData){
            HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
            String strExport = request.getParameter(EXPORT);
            if(StringUtils.isEmpty(strExport)){
                return object;
            }
            if (!strExport.trim().equals(TRUE)){
                return object;
            }
            Object data = ((ResponseData) object).getBody();
            if(data instanceof List) {
                DataExcelOutput output = new DataExcelOutput(data);
                HttpServletResponse response = (HttpServletResponse) RpcContext.getContext().getResponse();
                response.setContentType(output.getMediaType());
                response.setHeader(DataExcelOutput.CONTENT_DISPOSITION,output.getContentDisposition());
                OutputStream outputStream = response.getOutputStream();
                if(outputStream != null) {
                    output.write(response.getOutputStream());
                    outputStream.flush();
                    outputStream.close();
                }
            }
        }
        return object;
    }
}
