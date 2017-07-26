package com.gosun.isap.warn.impl.alert.generator;

import com.gosun.isap.dao.mapper.*;
import com.gosun.isap.dao.mapper.alert.AlertTestMapper;
import com.gosun.isap.dao.mapper.alert.base.GuardBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.GuardGroupBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.GuardGroupBelongBaseMapper;
import com.gosun.isap.dao.po.*;
import com.gosun.isap.dao.po.alert.base.BaseGuard;
import com.gosun.isap.dao.po.alert.base.BaseGuardGroup;
import com.gosun.isap.dao.po.alert.base.BaseGuardGroupBelong;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 辅助生成测试数据。
 * TUserMapper,TRoleMapper 插入数据时，要保证能插入 id，否则不会成功。
 * 基于已有的 department 做的插入。
 * 生成的测试数据：resources/test_data.sql。
 * <p>创建时间：2017-6-10 10:30</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class CommonGenerator extends BaseUnitTest {
    @Autowired
    private AlertTestMapper testMapper;

    @Autowired
    private TDeviceMapper deviceMapper;

    @Autowired
    private AlertCommonService commonService;

    @Autowired
    private TRoleDepMapper roleDepMapper;

    @Autowired
    private TRoleMapper roleMapper;

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TUserBelongMapper userBelongMapper;

    @Autowired
    private TRoleMenuAuthMapper authMapper;

    @Autowired
    private GuardGroupBaseMapper groupMapper;

    @Autowired
    private GuardGroupBelongBaseMapper groupBelongMapper;
    
    @Autowired
    private GuardBaseMapper guardMapper;

    private String north = "48056976-3d22-11e7-b6d1-4ccc6aa6e080";
    private String west = "f86a0c7d-4d86-11e7-9107-02004c4f4f50";
    private String area = "4805686f-3d22-11e7-b6d1-4ccc6aa6e080";

    @Override
    public void autoWiredTest() {

    }

    public void generate(){
        createUser();
        createGroups();
    }

    private void generatorDevice() {
        List<String> northList = testMapper.getChildDepartment(north);
        List<String> westList = testMapper.getChildDepartment(west);

        generatorDevice(northList);
        generatorDevice(westList);
    }

    private void generatorDevice(List<String> list) {
        for (String id : list) {
            String departmentName = commonService.getDepartmentName(id);
            TDevice device = generateDevice(departmentName + "-东", id);
            TDevice device1 = generateDevice(departmentName + "-西", id);
            TDevice device2 = generateDevice(departmentName + "-南", id);
            TDevice device3 = generateDevice(departmentName + "-北", id);
            deviceMapper.insertSelective(device);
            deviceMapper.insertSelective(device1);
            deviceMapper.insertSelective(device2);
            deviceMapper.insertSelective(device3);
        }
    }

    private TDevice generateDevice(String name, String departmentId) {
        String id = UUID.randomUUID().toString();
        TDevice device = new TDevice();
        device.setPlatId(1L);
        device.setStatus((byte) 1);
        device.setName(name);
        device.setDepartmentId(departmentId);
        device.setId(id);
        device.setCode(id);
        device.setNetStatus((byte) 1);
        device.setNameSpell(id.substring(0, 9));
        device.setLocationX("102");
        device.setLocationY("103");
        device.setDevType(1);
        device.setParent("");
        return device;
    }

    @Test
    public void createGroups(){
        createGroup(north,false);
        createGroup(west,true);
    }

    private void createGroup(String parentId,boolean skip){
        List<String> list = commonService.getChildCommunities(parentId,null);
        int number = 0;
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            String name = commonService.getDepartmentName(id);
            BaseGuardGroup group = new BaseGuardGroup();
            group.setName(name);
            groupMapper.insertSelective(group);
            BaseGuardGroupBelong belong = new BaseGuardGroupBelong();
            belong.setGroupId(group.getId());
            belong.setDepartmentId(id);
            groupBelongMapper.insertSelective(belong);
            if(skip && i<list.size() -1){
                i +=1;
                id = list.get(i);
                name = commonService.getDepartmentName(id);
                belong = new BaseGuardGroupBelong();
                belong.setGroupId(group.getId());
                belong.setDepartmentId(id);
                groupBelongMapper.insertSelective(belong);
            }
            createGuards(group.getId(),number);
            number += 5;
        }
    }

    private void createGuards(long groupId,int number){
        for (int i = 0; i < 5; i++) {
            BaseGuard guard = new BaseGuard();
            guard.setName("保安-" + number+i);
            guard.setGroupId(groupId);
            guard.setPhone("1701234"+String.format("%04d",number+i));
            guardMapper.insertSelective(guard);
        }
    }

    private void createUser(){
        generateUser();
        generateRole();
        bindUserRole();
        bindRoleDep();
        bindRoleMenu();
    }

    private void generateUser() {
        String name = "user-%d-%d";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                long id = (i + 1) * 10 + j;
                TUser user = createUser(String.format(name, i, j), id);
                userMapper.insert(user);
            }
        }
    }

    private void generateRole() {
        String name = "role-%d-%d";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                long id = (i + 1) * 10 + j;
                TRole role = createRole(String.format(name, i, j), id);
                roleMapper.insert(role);
            }
        }
    }

    private void bindUserRole() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                long id = (i + 1) * 10 + j;
                TUserBelong belong = new TUserBelong();
                belong.setUserId(id);
                belong.setRoleId(id);
                userBelongMapper.insertSelective(belong);
            }
        }
        for (int j = 0; j < 5; j++) {
            long id = 40 + j;
            for (int i = 1; i < 4; i++) {
                TUserBelong belong = new TUserBelong();
                belong.setUserId(id);
                belong.setRoleId(id - i * 10);
                userBelongMapper.insertSelective(belong);
            }
        }
    }

    private TRole createRole(String name, long id) {
        TRole role = new TRole();
        role.setId(id);
        role.setName(name);
        return role;
    }

    private TUser createUser(String name, long id) {
        TUser user = new TUser();
        user.setLockStartTime(new Date());
        user.setLockEndTime(new Date());
        user.setLoginName(name);
        user.setName(name);
        user.setMobile("");
        user.setPasswd("");
        user.setEmail("");
        user.setPriority((byte) 1);
        user.setStatus((byte) 1);
        user.setId(id);
        return user;
    }

    private void bindRoleDep() {
        for (int i = 1; i < 4; i++) {
            int id = i * 10;
            bindRoleDep(id, area);
            bindRoleDep(id + 1, north);
            bindRoleDep(id + 1, west);
            bindRoleDep(id + 2, north);
            bindChildDep(id + 2, west);
            bindChildDep(id + 3, north);
            bindChildDep(id + 3, west);
            bindRoleDep(id + 4, north);
            bindRoleDep(id + 4, west);
            bindChildDep(id + 4, west);
        }
    }

    private void bindRoleMenu() {
        for (int i = 0; i < 3; i++) {
            int authId = 25 + i;
            for (int j = 0; j < 5; j++) {
                long id = (i + 1) * 10 + j;
                TRoleMenuAuth auth = new TRoleMenuAuth();
                auth.setRoleId(id);
                auth.setAuthId((long) authId);
                authMapper.insertSelective(auth);
            }
        }
    }

    private void bindChildDep(int roleId, String parentId) {
        List<String> list = commonService.getChildCommunities(parentId, null);
        for (String departmentId : list) {
            bindRoleDep(roleId, departmentId);
        }
    }

    private void bindRoleDep(long roleId, String departmentId) {
        TRoleDep roleDep = new TRoleDep();
        roleDep.setDepartmentId(departmentId);
        roleDep.setRoleId(roleId);
        roleDepMapper.insertSelective(roleDep);
    }
}
