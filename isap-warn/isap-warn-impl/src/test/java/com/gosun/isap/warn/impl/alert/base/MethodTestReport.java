package com.gosun.isap.warn.impl.alert.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>创建时间：2017-6-10 17:12</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class MethodTestReport {
    private String methodName;
    private String methodDescription;
    private Param[] params;
    private String result;
    private String resultSize;
    private boolean useLastResult;

    public MethodTestReport(boolean useLastResult,String description, Object result, Object... params) {
        this.useLastResult = useLastResult;
        Pattern pattern = Pattern.compile("^[^\\(]+");
        Matcher matcher = pattern.matcher(description);
        if(matcher.find()) {
            methodName = matcher.group();
        }else {
            methodName = description;
        }
        methodDescription = description;

        if(result != null && result instanceof List ){
            resultSize = ((List) result).size()+"";
        }

        if(params != null){
            this.params = new Param[params.length];
            int i = 0;
            for (Object param:params) {
                this.params[i] = new Param(param);
            }
        }

        this.result = toJson(result);
    }

    public MethodTestReport(String description, Object result, Object... params) {
        this(false,description,result,params);
    }

    public boolean isUseLastResult() {
        return useLastResult;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
    }


    public Param[] getParams() {
        return params;
    }

    public void setParams(Param[] params) {
        this.params = params;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultSize() {
        return resultSize;
    }

    public void setResultSize(String resultSize) {
        this.resultSize = resultSize;
    }

    public static class Param{
        private String param;
        private String paramSize;
        Param(Object param) {

            if(param != null && param instanceof List ){
                paramSize = ((List) param).size()+"";
            }
            this.param = toJson(param);
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getParamSize() {
            return paramSize;
        }

        public void setParamSize(String paramSize) {
            this.paramSize = paramSize;
        }
    }

    private static String toJson(Object object){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        String  json = gson.toJson(object);
        return FormatJsonString.formatJson(json);
    }
}
