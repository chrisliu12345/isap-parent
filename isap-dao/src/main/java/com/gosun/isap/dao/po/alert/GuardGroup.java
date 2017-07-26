package com.gosun.isap.dao.po.alert;

import com.gosun.isap.dao.po.alert.base.BaseGuardGroup;
import com.gosun.isap.dao.po.alert.bean.StringIdNameBean;

import java.util.List;

/**
 * <p>创建时间：2017/5/10 12:00</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class GuardGroup extends BaseGuardGroup {
    private int countOfGuard;
    private int countOfDepartment;
    private List<String> departmentIds;

    public int getCountOfGuard() {
        return countOfGuard;
    }

    public void setCountOfGuard(int countOfGuard) {
        this.countOfGuard = countOfGuard;
    }

    public int getCountOfDepartment() {
        return countOfDepartment;
    }

    public void setCountOfDepartment(int countOfDepartment) {
        this.countOfDepartment = countOfDepartment;
    }

    public List<String> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<String> departmentIds) {
        this.departmentIds = departmentIds;
    }

    @Override
    public String toString() {
        return "GuardGroup{" +
                "countOfGuard=" + countOfGuard +
                ", countOfDepartment=" + countOfDepartment +
                "} " + super.toString();
    }
}
