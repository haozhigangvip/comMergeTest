package com.hzg.vo;

public class content {
private String access_token;
private String error;
public String getAccess_token() {
	return access_token;
}
public void setAccess_token(String access_token) {
	this.access_token = access_token;
}
public String getError() {
	return error;
}
public void setError(String error) {
	this.error = error;
}
@Override
public String toString() {
	return "content [access_token=" + access_token + ", error=" + error + "]";
}


}
