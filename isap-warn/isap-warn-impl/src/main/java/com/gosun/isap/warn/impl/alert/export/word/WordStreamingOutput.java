package com.gosun.isap.warn.impl.alert.export.word;

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
public class WordStreamingOutput extends ExportStreamingOutput {
    private static final String MEDIA_TYPE = "application/msword";
    private static final String SUFFIX_NAME = "docx";

    public WordStreamingOutput() {
        super();
    }

    public WordStreamingOutput(String settingResource, Map<String, Object> header, List<Map<String, Object>> body, String name) {
        super(settingResource, header, body, name);
    }

    @Override
    public void write(OutputStream outputStream) throws IOException, WebApplicationException {
        WordExportHelper helper = new WordExportHelper(getSetting(),getHeader(),getBody());
        helper.export(outputStream);
    }

    @Override
    public String getMediaType() {
        return MEDIA_TYPE;
    }

    @Override
    protected String getSuffixName() {
        return SUFFIX_NAME;
    }
}
