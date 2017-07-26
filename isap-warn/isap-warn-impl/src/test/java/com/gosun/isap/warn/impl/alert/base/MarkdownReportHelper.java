package com.gosun.isap.warn.impl.alert.base;

import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.impl.alert.export.freemarker.FreemarkerStreamingOutput;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * <p>创建时间：2017-6-10 17:36</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class MarkdownReportHelper implements ReportHelper{
    private static final String UTF_8 = "utf-8";
    private static final String TEMPLATE = "method_test_report.ftl";
    private static final String BASE_PACKAGE= "/";
    private List<MethodTestReport> reports = new ArrayList<>();
    private String serviceName;

    public MarkdownReportHelper(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void export(String path) throws IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("methods",reports);
        map.put("serviceName",serviceName);
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding(UTF_8);
        configuration.setDateFormat(AlertConst.DATE_TIME_PATTERN);
        configuration.setClassLoaderForTemplateLoading(FreemarkerStreamingOutput.class.getClassLoader(),BASE_PACKAGE);
        Template template =configuration.getTemplate(TEMPLATE);

        Writer writer = new OutputStreamWriter(new FileOutputStream(path));
        try {
            template.process(map,writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        writer.close();
    }

    @Override
    public void saveResult(String methodName, Object object, Object... params) {
        reports.add(new MethodTestReport(methodName,object,params));
    }

    @Override
    public void saveResult(boolean useLastResult, String methodName, Object object, Object... params) {
        reports.add(new MethodTestReport(useLastResult,methodName,object,params));
    }

    @Override
    public void clearReport() {
        reports = new ArrayList<>();
    }
}
