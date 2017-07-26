package com.gosun.isap.warn.api.alert.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.UUID;

/**
 * <p>创建时间：2017-5-17 19:53</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ResourcePathUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePathUtils.class);

    private static final String PATH_PROPERTIES = "path.properties";
    private static final String DEFAULT_RESOURCE_PATH = "../resource/alert";
    private static final String ALERT_RESOURCE_PATH = "alert.resource.path";
    private static final String NO_SYSTEM_PROPERTY_HINT = "server.properties 不存在或者 property alert.resource.path 不存在，警情模块图片存储路径设置为默认值：%s";

    private static final int DEEP = 3;
    private static final int LAST_INDEX = DEEP - 1;

    private static String[] dirs = new String[DEEP];
    private static final int[] FILE_COUNTS = new int[DEEP];
    private static String resourceRoot;
    private static final String DIR_NAME_PREFIX = "dir_level_";
    private static final int MAX_FILES = 3000;
    private static Properties properties;

    static {
        initResourceRoot();
    }

    /**
     * 在 resource 文件目录下新建一个文件
     *
     * @param name 文件名
     * @return File
     */
    public static File createNewFile(String name) {
        String path = null;
        synchronized (FILE_COUNTS) {
            FILE_COUNTS[LAST_INDEX] += 1;
            path = getCurrentPath() + File.separator + name;
            if (FILE_COUNTS[LAST_INDEX] >= MAX_FILES) {
                checkCount(LAST_INDEX);
                storeConfig();
                new File(getCurrentPath()).mkdirs();
            }
        }

        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static String getResourcePath() {
        String path = getCurrentPath();
        FILE_COUNTS[LAST_INDEX] = getCountOfFiles(path);
        checkCount(LAST_INDEX);
        storeConfig();
        if (!getCurrentPath().equals(path)) {
            new File(getCurrentPath()).mkdirs();
        }
        return getCurrentPath();
    }

    private static void initResourceRoot() {
        // 初始化 resourceRoot
        resourceRoot = ConfigUtil.getServerProperty(ALERT_RESOURCE_PATH);
        if (resourceRoot == null) {
            File file = new File(DEFAULT_RESOURCE_PATH);
            file.mkdirs();
            try {
                resourceRoot = file.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                resourceRoot = file.getAbsolutePath();
            }

            // 输出日志
            String message = String.format(NO_SYSTEM_PROPERTY_HINT, resourceRoot);
            LOGGER.warn("initResourceRoot : {}", message);
        }


        // 初始化文件名称
        initProperties();
        for (int i = 0; i < DEEP; i++) {
            dirs[i] = getDirFromProperties(DIR_NAME_PREFIX + i);
            if (dirs[i] == null) {
                dirs[i] = UUID.randomUUID().toString();
                properties.setProperty(DIR_NAME_PREFIX + i, dirs[i]);
            }
        }
        // 创建路径
        new File(getResourcePath()).mkdirs();

        // 初始化文件个数
        initFilesCount();

        // 保存配置
        storeConfig();
    }

    private static void initProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(getPropertyInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void storeConfig() {
        OutputStream outputStream = null;
        try {
            outputStream = getPropertyOutputStream();
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static OutputStream getPropertyOutputStream() throws FileNotFoundException {
        return new FileOutputStream(getPropertyFile());
    }

    private static InputStream getPropertyInputStream() throws FileNotFoundException {
        return new FileInputStream(getPropertyFile());
    }

    private static File getPropertyFile() {
        File file = new File(getPropertyFilePath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private static String getPropertyFilePath() {
        return resourceRoot + File.separator + PATH_PROPERTIES;
    }

    private static String getDirFromProperties(String key) {
        return properties.getProperty(key);
    }

    private static String getCurrentPath() {
        return getPathInDeep(DEEP);
    }

    private static String getPathInDeep(int deep) {
        StringBuilder path = new StringBuilder(resourceRoot);
        for (int i = 0; i < deep; i++) {
            path.append(File.separator).append(dirs[i]);
        }
        return path.toString();
    }

    private static void checkCount(int i) {
        if (i < 0 || i > LAST_INDEX) {
            return;
        }
        if (FILE_COUNTS[i] >= MAX_FILES) {
            dirs[i] = UUID.randomUUID().toString();
            properties.setProperty(DIR_NAME_PREFIX + i, dirs[i]);
            FILE_COUNTS[i] = 0;
            if (i > 0) {
                FILE_COUNTS[i - 1] += 1;
                checkCount(i - 1);
            }
        }
    }

    private static void initFilesCount() {
        for (int i = 0; i < DEEP; i++) {
            FILE_COUNTS[i] = getCountOfFiles(getPathInDeep(i + 1));
            if (FILE_COUNTS[i] > 0) {
                FILE_COUNTS[i]--;
            }
        }
    }

    private static int getCountOfFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return 0;
        } else {
            String[] names = file.list();
            if (names == null) {
                return 0;
            } else {
                return names.length;
            }
        }
    }
}
