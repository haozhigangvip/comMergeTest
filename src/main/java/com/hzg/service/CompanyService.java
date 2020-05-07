package com.hzg.service;

import java.util.List;

import com.hzg.entity.Company;

public interface CompanyService {
	 public List<Company> findCompanyByLikeNameOrID(String companyNameOrID);

	public int ConvertCompany(String sourceCompanyID, String targetCompanyID);



	public int UpdateCompanyDelTarg(String companyID,int value);

	public Company  findCompanyBycomID(String companyID);

	 
}
