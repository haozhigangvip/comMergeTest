package com.hzg.service;

import java.util.List;

import com.hzg.entity.Company;
import com.hzg.entity.CompanyMergeHistory_Total;
import com.hzg.entity.Contact;

public interface ContactService {
	  public List<Contact> findContactByLikeNameOrID(String ContactNameOrID);


	public int UpdateContDelTag(String contID,int value);

	public Contact  findContactBycontID(String contID);

	public int MergerCompany(List<Company> oldCompanList, Company newCompany);


	public List<CompanyMergeHistory_Total> getListHistory();


	public int resumeCompany(int id);



	 
}
