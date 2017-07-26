package com.gosun.isap.warn.impl.alert.export;

import com.gosun.isap.warn.api.alert.service.AlertExportService;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import com.gosun.isap.warn.impl.alert.export.excel.ExcelExportHelper;
import com.gosun.isap.warn.impl.alert.export.excel.ExcelStreamingOutput;
import com.gosun.isap.warn.impl.alert.export.base.ExportStreamingOutput;
import com.gosun.isap.warn.impl.alert.export.setting.TableSetting;
import com.gosun.isap.warn.impl.alert.export.word.WordExportHelper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.*;


/**
 * <p>创建时间：2017-5-23 14:56</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ExportHelperTest extends BaseUnitTest{
    @Autowired
    AlertExportService exportService;
    @Test
    public void helperTest() throws DocumentException, IOException {
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String,Object> map = new HashMap();
            map.put("id",i+1);
            map.put("name","name_"+i);
            map.put("createTime",new Date());
            map.put("gender",true);
            map.put("age",i);
            list.add(map);
        }

        TableSetting setting = TableSetting.getSettingFromResource("alert/export_user.xml");
        ExcelExportHelper helper = new ExcelExportHelper(setting,list);
        File file = new File("D:\\hello.xls");
        helper.export(new FileOutputStream(file));

        TableSetting docSetting = TableSetting.getSettingFromResource("alert/export_user_doc.xml");
        WordExportHelper wordExportHelper = new WordExportHelper(docSetting,list);
        File wordFile = new File("E:\\other\\demo.docx");
        File testFile = new File("E:\\other\\test.docx");
        XWPFDocument document = new XWPFDocument(new FileInputStream(testFile));
        OutputStream outputStream = new FileOutputStream(wordFile);
        wordExportHelper.export(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @Test
    public void unfinishedAlerts() throws IOException {
        List<Map<String, Object>> list = null;
        Map<String, Object> header = null;

        Long userId = 1L;
        list = exportService.getUnfinishedAlertsBody(null, userId, null, null, null);
//        header = exportService.getUnfinishedAlertsHeader(null, userId, null, null);

        ExportStreamingOutput output = new ExcelStreamingOutput("alert/setting/unfinished_alerts_setting.xml", null, list, "dhh");
        output.write(new FileOutputStream("E:\\other\\unfinished.xls"));
    }

    @Override
    public void autoWiredTest() {

    }
}
