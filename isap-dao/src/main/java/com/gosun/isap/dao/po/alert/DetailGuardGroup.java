package com.gosun.isap.dao.po.alert;

import com.gosun.isap.dao.po.alert.base.BaseGuardGroup;
import com.gosun.isap.dao.po.alert.bean.StringIdNameBean;

import java.util.List;

/**
 * <p>创建时间：2017/5/10 13:53</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class DetailGuardGroup extends GuardGroup{
    private List<Guard> guards;
    private List<StringIdNameBean> departments;

    public void init(BaseGuardGroup baseGuardGroup){
        if(baseGuardGroup == null){
            return;
        }
        setId(baseGuardGroup.getId());
        setAddTime(baseGuardGroup.getAddTime());
        setUpdateTime(baseGuardGroup.getUpdateTime());
        setName(baseGuardGroup.getName());
        setDescription(baseGuardGroup.getDescription());
    }

    public void init(GuardGroup guardGroup){
        if(guardGroup == null){
            return;
        }
        init((BaseGuardGroup) guardGroup);
        setCountOfDepartment(guardGroup.getCountOfDepartment());
        setCountOfGuard(guardGroup.getCountOfGuard());
    }

    public List<Guard> getGuards() {
        return guards;
    }

    public void setGuards(List<Guard> guards) {
        this.guards = guards;
    }

    public List<StringIdNameBean> getDepartments() {
        return departments;
    }

    public void setDepartments(List<StringIdNameBean> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "DetailGuardGroup{" +
                "guards=" + guards +
                ", departments=" + departments +
                "} " + super.toString();
    }
}
