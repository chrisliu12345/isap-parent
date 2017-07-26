package com.gosun.isap.dao.po.customer;

import java.util.List;

import com.gosun.isap.dao.po.TDevAuthdef;
import com.gosun.isap.dao.po.TMenuAuthdef;
import com.gosun.isap.dao.po.TRole;

/**
 * 封装角色及对应得权限列表
 * 
 * @author lyf
 *
 */
public class TRoleCustomer extends TRole {

	private String devId;
    private String devname;
    private String devType;
    private String devPermission;
    private Long menuId;
    private String menuName;
    private String menuParent;
    private String menuPermission;
    
   
	private List<RoleDevAuthCustomer> devAuths;

	private List<TMenuAuthdef> menuAuths;

	public List<RoleDevAuthCustomer> getDevAuths() {
		return devAuths;
	}

	public void setDevAuths(List<RoleDevAuthCustomer> devAuths) {
		this.devAuths = devAuths;
	}

	public List<TMenuAuthdef> getMenuAuths() {
		return menuAuths;
	}

	public void setMenuAuths(List<TMenuAuthdef> menuAuths) {
		this.menuAuths = menuAuths;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getDevname() {
		return devname;
	}

	public void setDevname(String devname) {
		this.devname = devname;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevPermission() {
		return devPermission;
	}

	public void setDevPermission(String devPermission) {
		this.devPermission = devPermission;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(String menuParent) {
		this.menuParent = menuParent;
	}

	public String getMenuPermission() {
		return menuPermission;
	}

	public void setMenuPermission(String menuPermission) {
		this.menuPermission = menuPermission;
	}
	
	

}
