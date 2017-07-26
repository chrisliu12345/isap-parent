package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.po.alert.GuardGroup;
import com.gosun.isap.dao.po.alert.base.BaseGuardGroup;
import com.gosun.isap.warn.api.alert.service.GuardGroupService;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import com.gosun.isap.warn.impl.alert.base.ObjectService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
/**
 * <p>创建时间：2017/5/10 10:54</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class GuardGroupServiceTest extends BaseUnitTest {
    @Autowired
    private GuardGroupService groupService;

    @Autowired
    private ObjectService objectService;

    @Override
    public void autoWiredTest() {

    }

    @Test
    public void groupCrudTest() {
        // 插入小组
        String description = "瑶湖小区 A 组";
        String name = "瑶湖小区";
        BaseGuardGroup guardGroup = new BaseGuardGroup();
        guardGroup.setName(name);
        guardGroup.setDescription(description);
        assert (groupService.addGuardGroup(guardGroup));
        System.out.println(guardGroup.getId());

        // 绑定社区
        String departmentId = objectService.getDepartmentId(objectService.getDeviceId());
        groupService.bindDepartment(guardGroup.getId(),departmentId,1L);
        List<Long> ids = groupService.listGroupId(departmentId);
        assertNotNull(ids);
        assert (ids.contains(guardGroup.getId()));

        // 解绑社区
        groupService.unbindDepartment(guardGroup.getId(),departmentId);
        ids = groupService.listGroupId(departmentId);
        if(ids != null && ids.size()>0){
            assertFalse(ids.contains(guardGroup.getId()));
        }

        // 更新社区信息
        guardGroup.setName(description);
        groupService.updateGuardGroup(guardGroup);
        BaseGuardGroup group = groupService.getById(guardGroup.getId());
        assertEquals(group.getName(),description);

        // 删除社区信息
        groupService.deleteGuardGroup(group.getId());
        group = groupService.getById(group.getId());
        assertNull(group);
    }

    @Test
    public void listGroupTest(){
        List<GuardGroup> list = groupService.listGuardGroup(null,null);
        for (GuardGroup group :list) {
            System.out.println(group);
        }
    }

    @Test
    public void deleteGroupTest(){
        try {
            groupService.deleteGuardGroup(3);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            String message = e.getMessage();
            Pattern pattern1 = Pattern.compile("a foreign key constraint fails \\(`[^`]*`.`([^`]*)`");
            Pattern pattern2 = Pattern.compile("\\) REFERENCES `([^`]*)`");
            Matcher matcher1 = pattern1.matcher(message);
            Matcher matcher2 = pattern2.matcher(message);
            if(matcher1.find()) {
                String table1 = matcher1.group(1);
                System.out.println(table1);
            }
            if(matcher2.find()) {
                String table2 = matcher2.group(1);
                System.out.println(table2);
            }
        }
    }

    @Test
    @Transactional
    public void testTx(){
        String description = "瑶湖小区 B 组";
        String name = "瑶湖小区1";
        BaseGuardGroup guardGroup = new BaseGuardGroup();
        guardGroup.setName(name);
        guardGroup.setDescription(description);
        groupService.addGuardGroup(guardGroup);
        assert (groupService.addGuardGroup(guardGroup));
        System.out.println(guardGroup.getId());
    }

}
