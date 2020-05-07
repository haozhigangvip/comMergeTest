package com.hzg.vo;

import java.util.List;

import com.hzg.entity.Company;

public class CompanyConvert {

private List<Company> Sourcecompanys;
private Company targetCompany;

public List<Company> getSourcecompanys() {
	return Sourcecompanys;
}
public void setSourcecompanys(List<Company> sourcecompanys) {
	Sourcecompanys = sourcecompanys;
}
public Company getTargetCompany() {
	return targetCompany;
}
public void setTargetCompany(Company targetCompany) {
	this.targetCompany = targetCompany;
}

	
}
