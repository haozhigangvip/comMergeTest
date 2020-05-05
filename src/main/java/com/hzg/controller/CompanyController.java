package com.hzg.controller;

import com.hzg.entity.Company;
import com.hzg.service.CompanyService;
import com.hzg.service.CompanyServiceImpl;
import com.hzg.vo.CompanyQueryVo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CompanyController {
		@Autowired
		private CompanyService customerService;
	
	  	@RequestMapping("/searchCompany")
	    @ResponseBody
	    public  CompanyQueryVo json(@RequestBody  Company company)  {
	  		
	        String cname=company.getCompanyname().trim();
	        System.out.println(cname);
	        List<Company> ls=new ArrayList<Company>();
	       
	        if(cname!=null &cname!=""){
	        	
	        	ls=customerService.findCompanyByLikeNameOrID(cname);
	        
	        }
	        
	        CompanyQueryVo cv=new CompanyQueryVo();
	        cv.setSuccess(1);
	        cv.setListCompany(ls);

	       
	        return cv;
	    }

}
