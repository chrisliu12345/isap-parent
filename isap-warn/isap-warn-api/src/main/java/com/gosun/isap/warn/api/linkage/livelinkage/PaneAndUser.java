package com.gosun.isap.warn.api.linkage.livelinkage;

import java.util.Objects;

public class PaneAndUser {
	private Integer paneIndex;

	private String userCode;

	public PaneAndUser(Integer paneIndex, String userCode) {
		this.paneIndex = paneIndex;
		this.userCode = userCode;
	}

	public Integer getPaneIndex() {
		return paneIndex;
	}

	public void setPaneIndex(Integer paneIndex) {
		this.paneIndex = paneIndex;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		PaneAndUser other = (PaneAndUser) obj;

		return paneIndex.equals(other.paneIndex) && userCode.equals(other.userCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(paneIndex, userCode);
	}
}
