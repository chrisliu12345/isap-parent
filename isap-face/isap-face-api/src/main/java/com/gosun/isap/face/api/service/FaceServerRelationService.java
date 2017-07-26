package com.gosun.isap.face.api.service;

import java.util.List;

import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.face.FaceServerRelationBean;
import com.gosun.isap.dao.po.face.TFaceServerDep;

public interface FaceServerRelationService {
	
	int saveFaceServerRelationService(FaceServerRelationBean faceServerRelation,List<TFaceServerDep> faceServerDepList, TDepartment department);
}
