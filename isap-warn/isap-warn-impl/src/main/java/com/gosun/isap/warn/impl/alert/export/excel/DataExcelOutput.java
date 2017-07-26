package com.gosun.isap.warn.impl.alert.export.excel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间：2017-6-15 16:55</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class DataExcelOutput implements StreamingOutput {
    public static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String DISPOSITION_TEMPLATE = "attachment; filename=\"%s.xls\"";
    private static final String MEDIA_TYPE = "application/-excel";

    private List<Map<String, Object>> list;
    private Map<String, Integer> indexMap = new HashMap<>();
    private int keyIndex = 0;

    public DataExcelOutput(Object list) {
        if(!(list instanceof List) || ListUtils.isEmpty((List) list)){
            return;
        }
        Gson gson = new GsonBuilder()
                .setDateFormat(AlertConst.DATE_TIME_PATTERN)
                .registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                    @Override
                    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                        if (src == src.longValue())
                            return new JsonPrimitive(src.longValue());
                        return new JsonPrimitive(src);
                    }
                })
                .create();
        String json = gson.toJson(list);
        System.out.println(json);
        this.list = gson.fromJson(json,new TypeToken<List<Map<String,Object>>>(){}.getType());
    }

    @Override
    public void write(OutputStream outputStream) throws IOException, WebApplicationException {
        HSSFWorkbook workbook = generateExcel();
        if(workbook != null) {
            workbook.write(outputStream);
            workbook.close();
        }
    }

    private HSSFWorkbook generateExcel() {
        if(ListUtils.isEmpty(list)){
            return null;
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowNumber = 1;
        for (Map<String, Object> map : list) {
            Row row = sheet.createRow(rowNumber);
            for (Map.Entry<String,Object> entry:map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                int index = getIndex(key);
                Cell cell = row.createCell(index);
                ExcelUtil.writeDataToCell(cell,value);
            }
            rowNumber ++;
        }
        final Row row = sheet.createRow(0);
        indexMap.forEach((key,index)->{
            Cell cell = row.createCell(index);
            cell.setCellValue(key);
        });
        return workbook;
    }

    public String getMediaType(){
        return MEDIA_TYPE;
    }

    public String getContentDisposition(){
        return String.format(DISPOSITION_TEMPLATE,"data");
    }

    private int getIndex(String key) {
        if (!indexMap.containsKey(key)) {
            indexMap.put(key, keyIndex);
        }
        keyIndex++;
        return indexMap.get(key);
    }
}
