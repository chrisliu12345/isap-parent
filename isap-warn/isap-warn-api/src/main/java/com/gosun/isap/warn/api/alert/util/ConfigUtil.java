package com.gosun.isap.warn.api.alert.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件操作工具
 * <p>创建时间：2017-5-18 14:46</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ConfigUtil {
    private static final String CONFIG_DIRECTORY = "../conf";
    private static final String SERVER_PROPERTIES = "server.properties";

    /**
     * @return conf 文件夹存在
     */
    public static boolean isConfigDirectoryExist() {
        File file = getConfigDirectory();
        return file.exists() && file.isDirectory();
    }


    /**
     * @return conf 文件夹
     */
    public static File getConfigDirectory() {
        return new File(CONFIG_DIRECTORY);
    }

    /**
     * @return conf 文件夹的路径
     */
    public static String getConfigDirectoryPath() {
        String path;
        try {
            path = getConfigDirectory().getCanonicalPath();
        } catch (IOException e) {
            path = getConfigDirectory().getAbsolutePath();
        }
        return path;
    }

    /**
     * @param name property 名
     * @return property 值
     */
    public static String getServerProperty(String name) {
        Properties properties = null;
        try {
            properties = getProperties(SERVER_PROPERTIES);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (properties == null) {
            return null;
        }
        return properties.getProperty(name);
    }

    /**
     * 先从配置文件夹下寻找，找不到搜索相应资源
     *
     * @param name 资源名
     * @return properties
     * @throws IOException properties load 抛出的异常
     */
    public static Properties getProperties(String name) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getInputStream(name);
        if(inputStream != null) {
            properties.load(inputStream);
        }
        return properties;
    }

    /**
     * 先从配置文件夹下寻找，找不到搜索相应资源
     *
     * @param name 资源相对于 conf 的路径名
     * @return inputStream
     */
    public static InputStream getInputStream(String name) {
        InputStream inputStream = null;
        if (isConfigDirectoryExist()) {
            File file = new File(getConfigDirectory() + File.separator + name);
            try {
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (inputStream == null) {
            inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(name);
        }
        return inputStream;
    }
}
