package com.hzg.vo;

import java.util.List;

import com.hzg.entity.CompanyMergeHistory_Total;
import com.hzg.entity.ContactMerge;

public class resultContactList {
private Integer code;
private List<ContactMerge> data;
public Integer getCode() {
	return code;
}
public void setCode(Integer code) {
	this.code = code;
}
public List<ContactMerge> getData() {
	return data;
}
public void setData(List<ContactMerge> data) {
	this.data = data;
}

}
			