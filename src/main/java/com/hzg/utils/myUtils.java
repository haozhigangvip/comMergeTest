package com.hzg.utils;

import java.util.ArrayList;
import java.util.List;

import com.hzg.entity.Company;
import com.hzg.entity.Contact;

public class myUtils {
public static  int chkCompanyList(List<Company> list,Company CheckCompany){
		for (Company company : list) {
		System.out.println(company.getComID());

		if(company.getComID().toString().trim().equals(CheckCompany.getComID().toString().trim())){

			return 1;
		}
	}
	return 0;
}
public static  int chkContactList(List<Contact> list,Contact CheckContact){
	for (Contact cont : list) {
	System.out.println(cont.getContID());

	if(cont.getContID().toString().trim().equals(CheckContact.getContID().toString().trim())){

		return 1;
	}
}
return 0;
}

public static  List<Company>  chkCompanyList(List<Company> sourcelist,List<Company> targetlist){
	List<Company> ls =new ArrayList<Company>();
	
	int tag;
	
	for (Company source_company : sourcelist) {
		tag=1;
		for(Company target_company : targetlist){
				if(source_company.getComID().trim().equals(target_company.getComID().trim())==true){
				tag=0;
			

			}
		}
		if (tag==1){
			ls.add(source_company);
		}
	}
	return ls;
}

public static  List<Contact>  chkContactList(List<Contact> sourcelist,List<Contact> targetlist){
	List<Contact> ls =new ArrayList<Contact>();
	
	int tag;
	
	for (Contact source_Contact : sourcelist) {
		tag=1;
		for(Contact target_Contact : targetlist){
				if(source_Contact.getContID().trim().equals(target_Contact.getContID().trim())==true){
				tag=0;
			

			}
		}
		if (tag==1){
			ls.add(source_Contact);
		}
	}
	return ls;
}
}
