package com.hzg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hzg.entity.Company;
import com.hzg.entity.CompanyMergeHistory_Total;
import com.hzg.entity.Contact;
import com.hzg.entity.ContactMerge;
import com.hzg.vo.contactResult;
import com.hzg.vo.resultContactList;

public interface ContactService {
	public List<Contact> findContactByLikeNameOrID(String ContactNameOrID);


	public Contact updateContDelTag(String contID,int value);

	public Contact  findContactBycontID(String contID);

	public int MergerContact(List<Contact> oldContactList,Contact newContact);

	public List<ContactMerge> getContactListHistory();

	public int resumeContact(int id);

	public List<Contact> testlist(String key);
	 
}
