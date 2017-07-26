package com.gosun.isap.warn.impl.alert.export.setting;

import com.gosun.isap.warn.impl.alert.export.base.AfterWriteDataHandler;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TableSetting
 * <p>创建时间：2017-5-23 11:04</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class TableSetting {
    private ExportBody body;
    private ExportHeader header;
    private ExportTemplate template;
    private ExportStyle style;
    private AfterWriteDataHandler handler;

    // 表格在文档中的位置 word 有效
    private int index;

    public ExportBody getBody() {
        return body;
    }

    public void setBody(ExportBody body) {
        this.body = body;
    }

    public AfterWriteDataHandler getHandler() {
        return handler;
    }

    public void setHandler(AfterWriteDataHandler handler) {
        this.handler = handler;
    }

    public ExportHeader getHeader() {
        return header;
    }

    public void setHeader(ExportHeader header) {
        this.header = header;
    }

    public ExportTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ExportTemplate template) {
        this.template = template;
    }

    public ExportStyle getStyle() {
        return style;
    }

    public void setStyle(ExportStyle style) {
        this.style = style;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private boolean initFromDocument(Document document) {
        // 解析根元素
        Element root = document.getRootElement();
        return initFromElement(root);
    }

    private boolean initFromElement(Element tableElement) {
        String rootQName = tableElement.getQualifiedName();
        if (!rootQName.equals(ExportConst.TABLE)) {
            return false;
        }
        Integer tableIndex = getIntegerAttribute(tableElement, ExportConst.INDEX);
        if (tableIndex != null) {
            this.index = tableIndex;
        }
        // 解析 AfterWriteDataHandler
        String handler = getAttribute(tableElement,ExportConst.HANDLER);
        initHandler(handler);

        // 解析 body
        Element bodyElement = tableElement.element(ExportConst.BODY);
        if (bodyElement == null) {
            return false;
        }
        List<ExportColumn> columns = initColumns(bodyElement);
        if (columns == null) {
            return false;
        }
        String columnHandler = getAttribute(bodyElement,ExportConst.HANDLER);
        body = ExportBody.getInstance(getRow(bodyElement), getColumn(bodyElement),getIntegerAttribute(bodyElement,ExportConst.MAX_ROW), columns);
        body.initHandler(columnHandler);

        // 解析 header
        Element headerElement = tableElement.element(ExportConst.HEADER);
        if (headerElement != null) {
            List<ExportCell> cells = initCells(headerElement);
            header = ExportHeader.getInstance(getRow(headerElement), getColumn(headerElement), cells);
        }

        // 解析 template
        Element templateElement = tableElement.element(ExportConst.TEMPLATE);
        if (templateElement != null) {
            String path = getAttribute(templateElement, ExportConst.PATH);
            String resource = getAttribute(templateElement,ExportConst.RESOURCE);
            Integer index = getIntegerAttribute(templateElement, ExportConst.INDEX);
            template = ExportTemplate.getInstance(path,resource,index);
        }

        // 解析 style
        Element styleElement = tableElement.element(ExportConst.STYLE);
        if (styleElement != null) {
            Element dateElement = styleElement.element(ExportConst.DATE);
            if (dateElement != null) {
                String formatter = dateElement.getStringValue();
                style = ExportStyle.getInstance(formatter);
            }
        }

        return true;
    }

    private List<ExportColumn> initColumns(Element bodyElement) {
        List<ExportColumn> columns = new ArrayList<>();
        for (Iterator it = bodyElement.elementIterator(ExportConst.COLUMN); it.hasNext(); ) {
            Element e = (Element) it.next();
            String key = e.getStringValue();
            if (key != null && !key.isEmpty()) {
                ExportColumn column = new ExportColumn();
                column.setKey(key);
                column.setColumn(getColumn(e));
                column.setDateFormat(getAttribute(e,ExportConst.DATE));
                column.setWidth(getIntegerAttribute(e,ExportConst.WIDTH));
                column.setHeight(getIntegerAttribute(e,ExportConst.HEIGHT));
                columns.add(column);
            }
        }
        if (columns.size() == 0) {
            return null;
        }
        return columns;
    }

    private List<ExportCell> initCells(Element headerElement) {
        List<ExportCell> cells = new ArrayList<>();
        for (Iterator it = headerElement.elementIterator(ExportConst.CELL); it.hasNext(); ) {
            Element e = (Element) it.next();
            String key = e.getStringValue();
            if (key != null && !key.isEmpty()) {
                ExportCell cell = new ExportCell();
                cell.setKey(key);
                cell.setRow(getRow(e));
                cell.setColumn(getColumn(e));
                cells.add(cell);
            }
        }
        if (cells.size() == 0) {
            return null;
        }

        return cells;
    }

    private Integer getRow(Element element) {
        Integer row = getIntegerAttribute(element, ExportConst.ROW);
        if (row != null && row > 0) {
            row -= 1;
        } else if (row != null && row < 0) {
            row = 0;
        }
        return row;
    }

    private Integer getColumn(Element element) {
        Integer column = getIntegerAttribute(element, ExportConst.COLUMN);
        if (column != null && column > 0) {
            column -= 1;
        } else if (column != null && column < 0) {
            column = 0;
        }
        return column;
    }

    private static Integer getIntegerAttribute(Element element, String name) {
        String string = getAttribute(element, name);
        if (string == null) {
            return null;
        }
        Integer integer = null;
        try {
            integer = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return integer;
    }

    private static String getAttribute(Element element, String name) {
        if (element == null) {
            return null;
        }
        Attribute attribute = element.attribute(name);
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    public static TableSetting getSettingFromResource(String resource) throws DocumentException {
        TableSetting setting = new TableSetting();
        // 读取 resource 文件
        InputStream stream = TableSetting.class.getClassLoader().getResourceAsStream(resource);
        // 从文件初始化 excel
        SAXReader reader = new SAXReader();
        Document document = reader.read(stream);
        if (setting.initFromDocument(document)) {
            return setting;
        }
        return null;
    }

    public static TableSetting getSetting(InputStream inputStream) throws DocumentException {
        if(inputStream == null){
            return null;
        }
        TableSetting setting = new TableSetting();
        // 从文件初始化 excel
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        if (setting.initFromDocument(document)) {
            return setting;
        }
        return null;
    }

    public static TableSetting getSettingFromFile(String path) throws DocumentException {
        TableSetting setting = new TableSetting();
        File file = new File(path);
        // 从文件初始化 excel
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        if (setting.initFromDocument(document)) {
            return setting;
        }
        return null;
    }

    public static List<TableSetting> getSettingsFromResource(String resource) throws DocumentException {
        // 读取 resource 文件
        InputStream stream = TableSetting.class.getClassLoader().getResourceAsStream(resource);
        // 从文件初始化 excel
        SAXReader reader = new SAXReader();
        Document document = reader.read(stream);
        return getSettingsFromDocument(document);
    }
    public static List<TableSetting> getSettingsFromFile(String path) throws DocumentException {
        // 从文件初始化 excel
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));
        return getSettingsFromDocument(document);
    }

    private static List<TableSetting> getSettingsFromDocument(Document document)  {
        List<TableSetting> settings = new ArrayList<>();
        Element root = document.getRootElement();
        String rootName = root.getQualifiedName();
        if(rootName.equals(ExportConst.TABLES)){
            for (Iterator it = root.elementIterator(ExportConst.TABLE); it.hasNext(); ) {
                TableSetting setting = new TableSetting();
                Element e = (Element) it.next();
                if(setting.initFromElement(e)){
                    settings.add(setting);
                }
            }
        }else if(rootName.equals(ExportConst.TABLE)){
            TableSetting setting = new TableSetting();
            if(setting.initFromElement(root)){
                settings.add(setting);
            }
        }
        if(settings.size() > 0){
            return settings;
        }
        return null;
    }

    private void initHandler(String handler){
        if(handler == null || handler.isEmpty()){
            return;
        }
        try {
            Class handlerClass = Class.forName(handler);
            this.handler = (AfterWriteDataHandler) handlerClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
