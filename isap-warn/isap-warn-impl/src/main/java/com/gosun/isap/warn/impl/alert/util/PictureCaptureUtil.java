package com.gosun.isap.warn.impl.alert.util;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.dao.mapper.TDeviceMapper;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.proxy.api.backend.IProxyCache;
import com.gosun.isap.proxy.api.backend.ProxyWrapper;
import com.gosun.isap.proxy.api.instance.ISdkAdapter;
import com.gosun.isap.proxy.api.sdk.PicCaptureParam;
import com.gosun.isap.proxy.api.sdk.constants.PicFormatType;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.api.alert.util.DateUtils;
import com.gosun.isap.warn.impl.alert.service.AlertServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * <p>创建时间：2017-6-2 14:55</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Component
public class PictureCaptureUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PictureCaptureUtil.class);
    private static final String ERROR_DEVICE_NOT_EXIST = "截图失败，原因：设备不存在";
    private static final String ERROR_PROXY_NOT_EXIST = "截图失败，原因：未获取到 proxy";
    private static final String ERROR_THROWABLE = "截图失败，程序异常";

    private static final String JPG_NAME_TEMPLATE = "%s.jpg";
    private static final String FULL_PATH_TEMPLATE = "%s/%s";

    private static final String SERVER_PROPERTIES = "../conf/server.properties";
    private static final String ALERT_BASE_PATH = "alertBasePath";
    private static String rootPath;

    static {
        rootPath = getRootPath();
        if (rootPath == null) {
            rootPath = "/mnt";
        }
    }

    @Autowired
    private AlertServiceImpl alertService;

    private IProxyCache proxyCache;

    @Autowired
    private TDeviceMapper mapper;

    @Autowired
    private AlertCommonService commonService;

    private static String getRootPath() {
        Properties properties = new Properties();
        File file = new File(SERVER_PROPERTIES);
        if (!file.exists()) {
            return null;
        } else {
            LOGGER.error("配置文件不存在，警情图片保存根路径无法获取");
        }

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            properties.load(inputStream);
            return properties.getProperty(ALERT_BASE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("配置文件加载异常，警情图片保存根路径无法获取");
        }
        return null;
    }


    private void initProxy() {
        try {
            proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 截图
     *
     * @param alertId 警情 id
     * @param type    截图类型
     */
    public void capturePicture(long alertId, int type) {
        String deviceId = commonService.getAlertDeviceId(alertId);
        capturePicture(alertId, deviceId, type);
    }

    /**
     * 截图
     *
     * @param alertId  警情 id
     * @param deviceId 设备 id
     * @param type     类型
     */
    public void capturePicture(long alertId, String deviceId, int type) {
        if (proxyCache == null) {
            initProxy();
            if (proxyCache == null) {
                LOGGER.error(ERROR_PROXY_NOT_EXIST);
                return;
            }
        }
        TDevice device = mapper.selectByPrimaryKey(deviceId);
        if (device != null) {
            try {
                ProxyWrapper wrapper = proxyCache.getProxy(device.getPlatId().toString());
                ISdkAdapter adapter = wrapper.getSdkAdapter();
                PicCaptureParam param = new PicCaptureParam();
                param.deviceCode = device.getCode();
                param.filePath = getRelativePath();
                param.picFormatType = PicFormatType.JPEG;
                adapter.capturePicture(param);
                alertService.saveResource(alertId, type, getLocalPath(param.filePath), null);
            } catch (Throwable throwable) {
                LOGGER.error(ERROR_THROWABLE, throwable);
            }
        } else {
            LOGGER.error(ERROR_DEVICE_NOT_EXIST);
        }
    }

    private String getRelativePath() {
        String today = DateUtils.formatDate(new Date());
        String fileName = String.format(JPG_NAME_TEMPLATE, UUID.randomUUID().toString());
        return String.format(FULL_PATH_TEMPLATE, today, fileName);
    }

    private String getLocalPath(String path) {
        return rootPath + File.separator + path;
    }
}
