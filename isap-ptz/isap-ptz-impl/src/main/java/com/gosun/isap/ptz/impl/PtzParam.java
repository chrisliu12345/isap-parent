package com.gosun.isap.ptz.impl;

import java.util.Date;

public class PtzParam {
private long userId;
private Date datetime;
private int priority;
private String sessionId;

public int getPriority() {
	return priority;
}
public void setPriority(int priority) {
	this.priority = priority;
}
public Date getDatetime() {
	return datetime;
}
public void setDatetime(Date datetime) {
	this.datetime = datetime;
}
public long getUserId() {
	return userId;
}
public void setUserId(long userId) {
	this.userId = userId;
}
public String getSessionId() {
	return sessionId;
}
public void setSessionId(String sessionId) {
	this.sessionId = sessionId;
}

}
