package com.hzg.service;

import java.util.List;

import com.hzg.entity.Company;
import com.hzg.entity.CompanyMergeHistory_Total;

public interface CompanyService {
	 public List<Company> findCompanyByLikeNameOrID(String companyNameOrID);


	public int UpdateCompanyDelTag(String companyID,int value);

	public Company  findCompanyBycomID(String companyID);

	public int MergerCompany(List<Company> oldCompanList, Company newCompany);


	public List<CompanyMergeHistory_Total> getListHistory();


	public int resumeCompany(int id);

	 
}
