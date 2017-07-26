package com.gosun.isap.warn.impl.alert.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * <p>创建时间：2017/5/6 16:55</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/beans.xml"})
public abstract class BaseUnitTest{
    protected long adminId = 1;
    protected long listUserId = 10;
    protected long userId = 40;
    protected long userId42 = 42;
    protected long listRoleId = 11;
    protected long processRoleId = 21;
    protected long manageRoleId = 31;

    protected String rootDepartmentId = "eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080";
    protected String northRoadId = "48056976-3d22-11e7-b6d1-4ccc6aa6e080";
    protected String westRoadId = "f86a0c7d-4d86-11e7-9107-02004c4f4f50";
    protected String communityId = "cd60e42e-4d86-11e7-9107-02004c4f4f50";
    protected String deviceId = "02b4d08b-bfc9-4a9d-9d2a-11232eefc55f";

    private ReportHelper reportHelper;

    @Test
    public void autoWiredTest(){

    }

    public void setReportHelper(ReportHelper reportHelper) {
        this.reportHelper = reportHelper;
    }

    protected void saveResult(String methodName, Object object, Object... params) {
        if(reportHelper == null){
            reportHelper = new ExcelReportHelper();
        }
        reportHelper.saveResult(methodName,object,params);
    }

    protected void saveResult(boolean useLastResult,String methodName,Object object, Object... params){
        if(reportHelper == null){
            reportHelper = new ExcelReportHelper();
        }
        reportHelper.saveResult(useLastResult,methodName,object,params);
    }


    protected void export(String path) throws IOException {
        reportHelper.export(path);
    }

    protected void clearReport(){
        reportHelper.clearReport();
    }
}
