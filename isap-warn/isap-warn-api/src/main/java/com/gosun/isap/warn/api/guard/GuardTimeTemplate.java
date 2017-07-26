package com.gosun.isap.warn.api.guard;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class GuardTimeTemplate implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;

	@NotEmpty(message = "名称不能为空")
	private String name;

	private Byte templateType;

	private String description;

	private Integer refCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Byte getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Byte templateType) {
		this.templateType = templateType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Integer getRefCount() {
		return refCount;
	}

	public void setRefCount(Integer refCount) {
		this.refCount = refCount;
	}
}
