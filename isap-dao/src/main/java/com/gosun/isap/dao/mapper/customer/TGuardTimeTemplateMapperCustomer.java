package com.gosun.isap.dao.mapper.customer;

public interface TGuardTimeTemplateMapperCustomer {
	void increaseRefCount(Long templateId);

	void decreaseRefCount(Long templateId);

	Integer getRefCount(Long templateId);
}
