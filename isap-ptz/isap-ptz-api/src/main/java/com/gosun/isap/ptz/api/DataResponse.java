package com.gosun.isap.ptz.api;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class DataResponse {
private String err;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public String getErr() {
	return err;
}

public void setErr(String err) {
	this.err = err;
}
}
