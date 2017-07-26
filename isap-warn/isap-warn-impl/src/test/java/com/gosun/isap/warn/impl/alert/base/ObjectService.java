package com.gosun.isap.warn.impl.alert.base;

import com.gosun.isap.dao.mapper.TDepartmentMapper;
import com.gosun.isap.dao.mapper.TDeviceMapper;
import com.gosun.isap.dao.mapper.TUserMapper;
import com.gosun.isap.dao.mapper.alert.AlertCommonMapper;
import com.gosun.isap.dao.mapper.alert.AlertTestMapper;
import com.gosun.isap.dao.mapper.alert.base.GuardBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.GuardGroupBaseMapper;
import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.alert.base.BaseGuard;
import com.gosun.isap.dao.po.alert.base.BaseGuardGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * <p>创建时间：2017/5/9 8:59</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service
public class ObjectService {

    @Autowired
    AlertCommonMapper commonMapper;

    @Autowired
    AlertTestMapper alertTestMapper;

    @Autowired
    TDepartmentMapper departmentMapper;

    @Autowired
    TDeviceMapper deviceMapper;

    @Autowired
    TUserMapper userMapper;

    @Autowired
    GuardBaseMapper guardBaseMapper;

    @Autowired
    GuardGroupBaseMapper groupBaseMapper;

    public String getUserName(long id){
        return commonMapper.getUserName(id);
    }

    public String getDepartmentId(String deviceId){
        return commonMapper.getDeviceDepartment(deviceId);
    }

    public String getDepartmentId(){
        String departmentId = alertTestMapper.getDepartmentId();
        if(departmentId == null){
            createDepartment();
            departmentId = alertTestMapper.getDepartmentId();
        }
        return departmentId;
    }

    private void createDepartment(){
        TDepartment department = new TDepartment();
        department.setId(UUID.randomUUID().toString());
        department.setName("test_auto_generator");
        department.setParent("");
        departmentMapper.insertSelective(department);
    }

    public String getDeviceId(){
        String deviceId =alertTestMapper.getDeviceId();
        if(deviceId == null){
            createDevice();
            deviceId = alertTestMapper.getDeviceId();
        }
        return deviceId;
    }

    private void createDevice(){
        TDevice device = new TDevice();
        device.setDepartmentId(getDepartmentId());
        device.setId(UUID.randomUUID().toString());
        device.setBriefName("青山");
        device.setCode("10222");
        device.setDescription("青山湖小区 A 区");
        device.setDevType(1);
        device.setLocationX("200");
        device.setLocationY("200");
        device.setNameSpell("QS");
        device.setStatus((byte) 0);
        device.setPlatId(0L);
        deviceMapper.insertSelective(device);
    }

    public long getUserId(){
        Long userId = alertTestMapper.getUserId();
        if(userId == null){
            createUser();
            userId = alertTestMapper.getUserId();
        }
        return userId;
    }

    private void createUser(){
        TUser user = new TUser();
        user.setEmail("");
        user.setId(1L);
        user.setLockEndTime(new Date());
        user.setLockStartTime(new Date());
        user.setStatus((byte) 0);
        user.setPriority((byte) 0);
        user.setPasswd("123456");
        user.setName("用户张");
        user.setMobile("17012344569");
        user.setLoginName("yhz");

        userMapper.insertSelective(user);
    }

    public long getGuardId(){
        Long guardId = alertTestMapper.getGuardId();
        if(guardId == null){
            createGuard();
            guardId = alertTestMapper.getGuardId();
        }
        return guardId;
    }
    private void createGuard(){
        BaseGuard baseGuard = new BaseGuard();
        baseGuard.setName("李保安");
        baseGuard.setPhone("17012345678");
        baseGuard.setGroupId(getGuardGroupId());

        guardBaseMapper.insertSelective(baseGuard);
    }

    public long getGuardGroupId(){
        Long id = alertTestMapper.getGuardGroupId();
        if(id == null){
            createGuardGroup();
            id = alertTestMapper.getGuardGroupId();
        }
        return id;
    }

    private void createGuardGroup(){
        BaseGuardGroup groupBase = new BaseGuardGroup();
        groupBase.setName("青山区-绿地玫瑰");
        groupBase.setDescription("青山区-绿地玫瑰");
        groupBaseMapper.insertSelective(groupBase);
    }
}
