package com.hzg.utils;

import java.util.List;

import com.hzg.entity.Company;

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

public static  List<Company>  chkCompanyList(List<Company> sourcelist,List<Company> targetlist){
	
	for (Company source_company : sourcelist) {
		for(Company target_company : targetlist)
		if(source_company.getComID().toString().trim().equals(target_company.getComID().toString().trim())){
			sourcelist.remove(source_company);
			
		}
	}
	return sourcelist;
}
;
}
