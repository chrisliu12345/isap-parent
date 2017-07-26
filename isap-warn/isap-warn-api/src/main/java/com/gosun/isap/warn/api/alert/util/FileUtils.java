package com.gosun.isap.warn.api.alert.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>创建时间：2017-5-18 17:28</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class FileUtils {
    /**
     * @param inputStream  inputStream
     * @param outputStream outputStream
     * @throws IOException IOException
     */
    public static void streamFromInputToOutput(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream == null || outputStream == null) {
            return;
        }
        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) != -1) {
            outputStream.write(bytes);
        }
    }

    /**
     * 从 http content disposition 获取文件后缀名
     *
     * @param disposition http content disposition
     * @return 后缀名
     */
    public static String getSuffixFromDisposition(String disposition) {
        Pattern pattern = Pattern.compile("\\.[[a-z]|[A-Z]]+");
        Matcher matcher = pattern.matcher(disposition);
        String suffix = null;
        while (matcher.find()) {
            suffix = matcher.group(0);
        }
        return suffix;
    }

    /**
     * 从文件名获取文件后缀名
     *
     * @param name 文件名
     * @return 文件后缀名
     */
    public static String getFileNameSuffix(String name) {
        if (name == null) {
            return null;
        }
        int index = name.lastIndexOf('.');
        return name.substring(index);
    }
}
