package com.gosun.isap.face.api.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 部门名单组信息<br>
 * <p>创建时间：2017/05/02</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class DepartmentListGroupBean implements Serializable{
	private List<String> departmentIDList;		//部门ID
	private List<Integer> listGroupIDList;							//名单组ID
	public List<String> getDepartmentIDList() {
		return departmentIDList;
	}
	public void setDepartmentIDList(List<String> departmentIDList) {
		this.departmentIDList = departmentIDList;
	}
	public List<Integer> getListGroupIDList() {
		return listGroupIDList;
	}
	public void setListGroupIDList(List<Integer> listGroupIDList) {
		this.listGroupIDList = listGroupIDList;
	}
	
}
