package com.gosun.isap.warn.api.alert.util;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 从 AlertError 枚举，以及枚举注释生成 error code properties
 * <p>创建时间：2017-6-8 10:33</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class GenerateProperties {
    private static final String ENUM_PATH = "\\isap-warn\\isap-warn-api\\src\\main\\java\\com\\gosun\\isap\\warn\\api\\alert\\model\\enumeration\\AlertError.java";
    private static final String PROPERTIES_PATH = "\\isap-warn\\isap-warn-impl\\src\\test\\resources\\META-INF\\message\\Message_zh.properties";

    private static final String PATTERN = "\\/\\*\\*[\\s]+\\*([^\\/\\*]+)[\\s]+\\*\\/[\\s]+[A-Z_]+\\((\\d+)[^\\(]*\\)";

    private static String getStringFromFile(String path){
        File file = new File(path);
        if(!file.exists()){
            return null;
        }
        String string = null;
        try {
            Source source = Okio.source(file);
            BufferedSource bufferedSource = Okio.buffer(source);
            string = bufferedSource.readString(Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    private static Map<String,String> getCodeMap(String string) {
        if(string== null){
            return null;
        }
        Map<String ,String> map = new LinkedHashMap<>();
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher =pattern.matcher(string);
        while (matcher.find()){
            String key = matcher.group(2);
            String value = matcher.group(1).trim();
            System.out.printf("%s:%s%n",key,value);
            map.put(key,value);
        }
        if(map.isEmpty()){
            return null;
        }
        return map;
    }

    private static void writeToProperties(Map<String, String> map, String path, boolean utf8) throws IOException {
        if(map == null || map.isEmpty() || path == null){
            return;
        }
        File file = new File(path);
        if(!file.exists() && !file.createNewFile()){
            return;
        }
        if(utf8) {
            Sink sink = Okio.appendingSink(file);
            BufferedSink bufferedSink = Okio.buffer(sink);
            map.forEach((key, value) -> {
                String string = String.format("%s=%s%n", key, value);
                try {
                    bufferedSink.writeString(string, Charset.defaultCharset());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bufferedSink.flush();
            bufferedSink.close();
            sink.close();
        }else {
            Properties properties = new Properties();
            map.forEach(properties::setProperty);
            properties.store(new FileOutputStream(file),"warn-alert error code map");
        }
    }


    public static void main(String[] args) throws IOException {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        String code = getStringFromFile(projectPath+ENUM_PATH);
        if(code == null){
            System.out.println("读取文件失败");
            return;
        }

        Map<String,String> map = getCodeMap(code);
        writeToProperties(map,projectPath+PROPERTIES_PATH,false);
        System.out.println("文件写入成功");
    }
}
