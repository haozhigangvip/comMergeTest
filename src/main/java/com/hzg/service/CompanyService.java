package com.hzg.service;

import java.util.List;

import com.hzg.entity.Company;

public interface CompanyService {
	 public List<Company> findCompanyByLikeNameOrID(String companyNameOrID);
	 
}
