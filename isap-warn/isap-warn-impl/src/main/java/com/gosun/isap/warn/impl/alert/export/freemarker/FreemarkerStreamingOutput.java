package com.gosun.isap.warn.impl.alert.export.freemarker;

import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.impl.alert.export.base.ExportStreamingOutput;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间：2017-5-27 16:32</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class FreemarkerStreamingOutput extends ExportStreamingOutput {
    private static final String MEDIA_TYPE = "application/msword";
    private static final String SUFFIX_NAME = "doc";

    private static final String UTF_8 = "utf-8";
    private static final String BASE_PACKAGE= "alert/template";
    private static final String TEMPLATE = "guard_process_template.xml";
    private static final String LOGS = "logs";

    private List<Map<String,Object>> list;
    private Map<String,Object> header;
    public FreemarkerStreamingOutput(List<Map<String,Object>> list,Map<String,Object> header,String name) {
        this.list = list;
        setName(name);
        this.header = header;
    }

    @Override
    public String getMediaType() {
        return MEDIA_TYPE;
    }

    @Override
    protected String getSuffixName() {
        return SUFFIX_NAME;
    }

    @Override
    public void write(OutputStream outputStream) throws IOException, WebApplicationException {
        if(list == null || list.size() == 0 || outputStream == null){
            return;
        }
        if(header == null) {
            header = new HashMap<>();
        }
        header.put(LOGS,list);
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding(UTF_8);
        configuration.setDateFormat(AlertConst.DATE_TIME_PATTERN);
        configuration.setClassLoaderForTemplateLoading(FreemarkerStreamingOutput.class.getClassLoader(),BASE_PACKAGE);
        Template template =configuration.getTemplate(TEMPLATE);

        Writer writer = new OutputStreamWriter(outputStream);
        try {
            template.process(header,writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        writer.close();
    }

}
