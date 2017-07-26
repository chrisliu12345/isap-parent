package com.gosun.isap.dao.po.alert;

import com.gosun.isap.dao.po.alert.base.BaseGuard;

/**
 * <p>创建时间：2017/5/10 17:12</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class Guard extends BaseGuard {
    private String groupName ;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
