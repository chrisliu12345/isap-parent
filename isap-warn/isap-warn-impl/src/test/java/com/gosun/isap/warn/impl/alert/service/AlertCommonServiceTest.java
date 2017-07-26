package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import com.gosun.isap.warn.impl.alert.base.MarkdownReportHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 测试基于 resources/test_data.sql 。
 * <p>创建时间：2017-6-10 9:02</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AlertCommonServiceTest extends BaseUnitTest {
    @Autowired
    private AlertCommonService commonService;


    @Override
    public void autoWiredTest() {

    }

    @Test
    public void paramNullTest() {
        commonService.getUserName(1L);
        commonService.getUserId(null);
        commonService.getDeviceDepartment(null);
        commonService.getLimitTime(null);
        commonService.getAlertDeviceId(null);
        commonService.getAlertDepartmentId(null);
        commonService.getRoles(null);
        commonService.getCommunitiesByUser(null, null);
        commonService.checkDepartmentRole(null, null);
        commonService.getDevicesByDepartments(null, null);
        commonService.getAllDevice(null);
        commonService.checkMenuPermission((Long) null, (Long) null);
        commonService.checkMenuPermission((Long) null, (String) null);
        commonService.countChildCommunities(null);
        commonService.getChildDepartmentsByUserId(null, null, null);
        commonService.countChildDepartmentsByUserId(null, null);
        commonService.getDepartmentName(null);
        commonService.getParentDepartmentName(null);
        commonService.getRelationDepartmentCount(null);
        commonService.countDepartmentGuards(null);
        commonService.suggestGuardGroup(null);
        commonService.suggestDepartment(null);
        commonService.getMenuId(null);
    }

    @Test
    public void simpleMethodTest() {
        setReportHelper(new MarkdownReportHelper("AlertCommonService"));
        String admin = commonService.getUserName(adminId);
        saveResult("getUserName(adminId)", admin, adminId);
        assertEquals(admin, "admin");

        String deviceDepartmentId = commonService.getDeviceDepartment(deviceId);
        saveResult("getDeviceDepartment(deviceId)", deviceDepartmentId, deviceId);

        Integer limitTime = commonService.getLimitTime(deviceId);
        assertNotNull(limitTime);
        assertEquals(limitTime.intValue(), 300);
        saveResult("getLimitTime(deviceId)", limitTime, deviceId);

//        commonService.getAlertDeviceId(null);
//        commonService.getAlertDepartmentId(null);

        List<Long> roles = commonService.getRoles(userId);
        assertEquals(roles.size(), 3);
        saveResult("getRoles(userId)", roles, userId);

        List<String> adminDeps = commonService.getCommunitiesByUser(adminId, null);
        saveResult("getCommunitiesByUser(adminId, null)", adminDeps, adminId);
        List<String> userDeps = commonService.getCommunitiesByUser(userId, null);
        saveResult("getCommunitiesByUser(userId, null)", userDeps, userId);

        List<Long> roleIds = commonService.getRoles(userId42);
        saveResult("getRoles(userId42)", roleIds, userId42);
        Integer count = commonService.checkDepartmentRole(rootDepartmentId, roleIds);
        saveResult(true,"checkDepartmentRole(rootDepartmentId, roleIds)", count, rootDepartmentId);
        count = commonService.checkDepartmentRole(northRoadId, roleIds);
        saveResult(true,"checkDepartmentRole(rootDepartmentId, roleIds)", count, northRoadId);
        count = commonService.checkDepartmentRole(westRoadId, roleIds);
        saveResult(true,"checkDepartmentRole(rootDepartmentId, roleIds)", count, westRoadId);

        List<String> departments = commonService.getChildCommunities(rootDepartmentId, null);
        saveResult("getChildCommunities(rootDepartmentId,null)", departments, rootDepartmentId);
        List<String> devices = commonService.getDevicesByDepartments(departments, null);
        saveResult(true,"getDevicesByDepartments(departments, null)", devices);

        departments = commonService.getChildCommunities(westRoadId, null);
        saveResult("getChildCommunities(westRoadId,null)", departments, westRoadId);
        devices = commonService.getDevicesByDepartments(departments, null);
        saveResult("getDevicesByDepartments(departments, null)", devices, departments);

        saveResult("getAllDepartment(null)",departments);

        devices = commonService.getAllDevice(null);
        saveResult("getAllDevice(null)",devices);

        /*
        commonService.checkMenuPermission((Long) null, (Long) null);
        commonService.checkMenuPermission((Long) null, (String) null);
        commonService.countChildCommunities(null);
        commonService.getChildDepartmentsByUserId(null, null, null);
        commonService.countChildDepartmentsByUserId(null, null);
        commonService.getDepartmentName(null);
        commonService.getParentDepartmentName(null);
        commonService.getRelationDepartmentCount(null);
        commonService.countDepartmentGuards(null);
        commonService.suggestGuardGroup(null);
        commonService.suggestDepartment(null);
        commonService.getMenuId(null);
        */

        try {
            export("E:\\isap\\test\\AlertCommonService.md");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
