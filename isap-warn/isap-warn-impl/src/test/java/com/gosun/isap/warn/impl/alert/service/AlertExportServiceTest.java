package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.warn.api.alert.service.AlertExportService;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import com.gosun.isap.warn.impl.alert.export.setting.TableSetting;
import com.gosun.isap.warn.impl.alert.export.word.WordExportHelper;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间：2017-5-25 14:18</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AlertExportServiceTest extends BaseUnitTest{

    @Autowired
    AlertExportService exportService;

    @Override
    public void autoWiredTest() {

    }

    @Test
    public void exportAlertLog() throws IOException, DocumentException {
        String path = "E:\\other\\alert_log.docx";
        File file = new File(path);
        OutputStream outputStream = new FileOutputStream(file);
        List<Map<String,Object>> body = exportService.alertLogBody(null,1L,null,null,null);

        TableSetting setting = TableSetting.getSettingFromResource("alert/setting/alert_log_setting.xml");
        WordExportHelper helper = new WordExportHelper(setting,body);
        helper.export(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
