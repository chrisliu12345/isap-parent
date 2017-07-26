package com.gosun.isap.warn.impl.alert.export.excel;

import com.gosun.isap.warn.impl.alert.export.base.ExportStreamingOutput;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间：2017-5-18 19:34</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ExcelStreamingOutput extends ExportStreamingOutput {
    private static final String MEDIA_TYPE = "application/-excel";
    private static final String SUFFIX_NAME = "xls";

    public ExcelStreamingOutput() {

    }

    public ExcelStreamingOutput(String settingResource, Map<String, Object> header, List<Map<String, Object>> body, String name) {
        super(settingResource, header, body, name);
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
        ExcelExportHelper helper = new ExcelExportHelper(getSetting(),getHeader(),getBody());
        helper.export(outputStream);
    }
}
