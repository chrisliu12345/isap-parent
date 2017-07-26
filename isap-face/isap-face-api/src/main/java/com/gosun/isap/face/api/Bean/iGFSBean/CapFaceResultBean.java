package com.gosun.isap.face.api.Bean.iGFSBean;

import com.gosun.isap.dao.po.face.CapFacesInfoBean;

public class CapFaceResultBean {
		private CapFacesInfoBean key;
		private float value;
		
		public CapFacesInfoBean getKey() {
			return key;
		}
		public void setKey(CapFacesInfoBean key) {
			this.key = key;
		}
		public float getValue() {
			return value;
		}
		public void setValue(float value) {
			this.value = value;
		}
		
}
