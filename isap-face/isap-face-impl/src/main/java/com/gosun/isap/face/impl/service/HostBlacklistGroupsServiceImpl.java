package com.gosun.isap.face.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosun.isap.dao.mapper.face.HostblacklistgroupsMapper;
import com.gosun.isap.dao.mapper.face.customer.HostsMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.TFaceServerMapperCustomer;
import com.gosun.isap.dao.po.face.Hostblacklistgroups;
import com.gosun.isap.dao.po.face.HostblacklistgroupsKey;
import com.gosun.isap.dao.po.face.Hosts;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.face.api.service.HostBlacklistGroupsService;

@Service
public class HostBlacklistGroupsServiceImpl implements HostBlacklistGroupsService {
	@Autowired
	private HostblacklistgroupsMapper hostblacklistgroupsMapper;
	@Autowired
	private HostsMapperCustomer hostsMapperCustomer;
	@Autowired
	private TFaceServerMapperCustomer faceServerMapperCustomer;

	/**
	 * 增加部门与名单组的关联
	 */
	@Override
	public int saveDepartmentListGroup(List<String> departmentIDList, List<Integer> listGroupIDList,
			List<TFaceServer> faceServerList) {
		int departmentIDListSize = departmentIDList.size();
		int listGroupIDListSize = listGroupIDList.size();

		for (int departmentIDListIndex = 0; departmentIDListIndex < departmentIDListSize; departmentIDListIndex++) {
			String departmentID = departmentIDList.get(departmentIDListIndex);

			for (int listGroupIDListIndex = 0; listGroupIDListIndex < listGroupIDListSize; listGroupIDListIndex++) {
				Hostblacklistgroups hostblacklistgroups = new Hostblacklistgroups();
				HostblacklistgroupsKey key = new HostblacklistgroupsKey();
				Integer listGroupID = listGroupIDList.get(listGroupIDListIndex);

				Hosts hosts = hostsMapperCustomer.selectbyRemarkToID(departmentID);
				key.setBlacklistgroupid(listGroupID);
				key.setHostid(hosts.getId());
				// 检索数据是否存在
				hostblacklistgroups = hostblacklistgroupsMapper.selectByPrimaryKey(key);

				if (hostblacklistgroups == null) {
					// 数据不存在,插入数据
					hostblacklistgroups = new Hostblacklistgroups();
					hostblacklistgroups.setBlacklistgroupid(listGroupID);
					hostblacklistgroups.setHostid(hosts.getId());
					hostblacklistgroups.setState(1);
					hostblacklistgroups.setUsedflag(1);

					int re = hostblacklistgroupsMapper.insert(hostblacklistgroups);
					if (re == 0) {
						return 1;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 删除部门与名单组的关联
	 */
	public int delectDepartmentListGroup(List<String> departmentIDList, List<Integer> listGroupIDList,
			List<TFaceServer> faceServerList) {
		HostblacklistgroupsKey key = new HostblacklistgroupsKey();
		int departmentIDListSize = departmentIDList.size();
		int listGroupIDListSize = listGroupIDList.size();

		for (int departmentIDListIndex = 0; departmentIDListIndex < departmentIDListSize; departmentIDListIndex++) {
			String departmentID = departmentIDList.get(departmentIDListIndex);
				for (int listGroupIDListIndex = 0; listGroupIDListIndex < listGroupIDListSize; listGroupIDListIndex++) {
					Integer listGroupID = listGroupIDList.get(listGroupIDListIndex);

					Hosts hosts = hostsMapperCustomer.selectbyRemarkToID(departmentID);
					key.setBlacklistgroupid(listGroupID);
					key.setHostid(hosts.getId());

					// 数据存在,删除数据
					int re = hostblacklistgroupsMapper.deleteByPrimaryKey(key);
					if (re == 0) {
						return 1;
					}
				}
			}
		return 0;
	}
}
