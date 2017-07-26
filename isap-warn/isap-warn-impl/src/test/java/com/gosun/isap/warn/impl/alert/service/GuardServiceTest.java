package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.Guard;
import com.gosun.isap.dao.po.alert.base.BaseGuard;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.GuardService;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import com.gosun.isap.warn.impl.alert.base.ObjectService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static org.junit.Assert.*;
/**
 * <p>创建时间：2017/5/10 10:31</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class GuardServiceTest extends BaseUnitTest{
    @Autowired
    @Qualifier(AlertServiceId.GUARD_SERVICE)
    private GuardService guardService;

    @Autowired
    private ObjectService objectService;

    @Override
    public void autoWiredTest() {
        assertNotNull(guardService);
    }

    @Test
    public void addGuardTest(){
        // insert
        BaseGuard guard = new BaseGuard();
        guard.setName("张保安");
        guard.setPhone("17014235123");
        guard.setGroupId(objectService.getGuardGroupId());
        assert (guardService.addGuard(guard));

        // update
        String name = "李保安";
        guard.setName(name);
        assert (guardService.updateGuard(guard));
        BaseGuard baseGuard = guardService.getGuard(guard.getId());
        assertEquals(guard.getName(),guardService.getGuardName(guard.getId()));

        // delete
//        assert (guardService.deleteGuard(guard.getId()));
    }

    @Test
    public void listTest(){
        List<Guard> list = guardService.listGuards(null,null,null);
        printList(list);
        list = guardService.listGuardsByDepartmentId(objectService.getDepartmentId(),null,null,null);
        printList(list);
        list = guardService.listGuardsByGroup(objectService.getGuardGroupId(),null,null,null);
        printList(list);
    }

    @Test
    public void alertsTest(){
        List<Alert> list = guardService.listGuardAlerts(objectService.getGuardId(),null,null,null,null);
        printList(list);
    }

    private void printList(List<?> guards){
        if(guards == null){
            return;
        }
        for (Object baseGuard : guards) {
            System.out.println(baseGuard);
        }
    }
}
