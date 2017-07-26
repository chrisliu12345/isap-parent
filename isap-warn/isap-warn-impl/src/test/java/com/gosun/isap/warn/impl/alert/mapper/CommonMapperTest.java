package com.gosun.isap.warn.impl.alert.mapper;

import com.gosun.isap.dao.mapper.alert.AlertCommonMapper;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>创建时间：2017-5-20 16:17</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class CommonMapperTest extends BaseUnitTest{
    private static final Logger logger = LoggerFactory.getLogger(CommonMapperTest.class);
    @Autowired
    AlertCommonMapper commonMapper;

    @Override
    public void autoWiredTest() {

    }

    @Test
    public void testDepartment(){
        List<String> departments = commonMapper.getChildCommunities("eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080",null);
        logger.info("testDepartment : {}",departments);
    }
}
