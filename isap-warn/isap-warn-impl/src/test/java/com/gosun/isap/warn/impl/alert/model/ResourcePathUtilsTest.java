package com.gosun.isap.warn.impl.alert.model;

import com.gosun.isap.warn.api.alert.util.ResourcePathUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * <p>创建时间：2017-5-18 14:29</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ResourcePathUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(ResourcePathUtilsTest.class);

    @Test
    public void file(){
        File file = ResourcePathUtils.createNewFile("1.txt");
        logger.info("file : {}",file.getAbsolutePath());
    }
}
