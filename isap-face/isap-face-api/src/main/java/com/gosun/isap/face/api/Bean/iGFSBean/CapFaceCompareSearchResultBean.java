package com.gosun.isap.face.api.Bean.iGFSBean;

import java.util.List;

public class CapFaceCompareSearchResultBean {
	
    public List<CapFaceResultBean> CapFaceResult ;
    public int TotalCount;
    
	public List<CapFaceResultBean> getCapFaceResult() {
		return CapFaceResult;
	}
	public void setCapFaceResult(List<CapFaceResultBean> capFaceResult) {
		CapFaceResult = capFaceResult;
	}
	public int getTotalCount() {
		return TotalCount;
	}
	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}
    
}

