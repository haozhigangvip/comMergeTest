package com.hzg.service;

import java.util.List;

import com.hzg.entity.Company;

public interface CompanyService {
	 public List<Company> findCompanyByLikeNameOrID(String companyNameOrID);


	public int UpdateCompanyDelTag(String companyID,int value);

	public Company  findCompanyBycomID(String companyID);

	public int MergerCompany(List<Company> oldCompanList, Company newCompany);

	 
}
