package com.gosun.isap.face.api.Bean;
/**
 * 名单与名单组的联系<br>
 *     <p>将名单中的人员消息与名单组进行绑定</p>
 * <p>创建时间：2017/05/02</p>
 *
 * @author 王栋梁
 * @version 1.0
 */

import java.io.Serializable;
import java.util.List;

public class GroupListBean implements Serializable{
	
		private List<Integer> listGroupIDList;							//名单组ID
		private List<Integer> listIDList;										//名单ID
		public List<Integer> getListGroupIDList() {
			return listGroupIDList;
		}
		public void setListGroupIDList(List<Integer> listGroupIDList) {
			this.listGroupIDList = listGroupIDList;
		}
		public List<Integer> getListIDList() {
			return listIDList;
		}
		public void setListIDList(List<Integer> listIDList) {
			this.listIDList = listIDList;
		}
}
