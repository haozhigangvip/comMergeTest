package com.hzg.controller;

import com.hzg.entity.Company;
import com.hzg.service.CompanyService;
import com.hzg.service.CompanyServiceImpl;
import com.hzg.vo.CompanyConvert;
import com.hzg.vo.CompanyQueryVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	  	
		@RequestMapping("/convertCompany")
	    @ResponseBody
	    public JSONObject Convert(@RequestBody JSONObject  cc )
		{
			Integer result=0;
			String sourceCompanycomID=null;
			String targetCompanyID=cc.getJSONObject("targetCompany").getString("comID");
			System.out.println(targetCompanyID);
			JSONArray jan = (JSONArray) cc.get("Sourcecompanys"); 
			if(jan!=null||jan.size()!=0){ 
				for(int i=0;i<jan.size();i++){
					JSONObject jo = JSONObject.fromObject(jan.get(i));
					sourceCompanycomID=jo.getString("comID");
					result=customerService.ConvertCompany(sourceCompanycomID, targetCompanyID);
					if(result==1){
						result=customerService.UpdateCompanyDelTarg(sourceCompanycomID, 1);
					
					}
				} 
			}
		
			
			
				return  JSONObject.fromObject("{Success:"+result+"}");
			
	  	}
		
		@RequestMapping("/toSourceList")
	    @ResponseBody
	    public  ModelAndView  toSourceList(HttpServletRequest request,HttpSession session)  {
			String para=request.getParameter("myname");
			
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.setViewName("index");
			int e=para.indexOf("-");
			if( e>0){
				String comid=para.substring(0,e);
		
				Company com=customerService.findCompanyBycomID(comid);
				if(com!=null){
					System.out.println(com.toString());
					
					List<Company> list=(List<Company>)session.getAttribute("SourceList");
					if(list==null){
						list=new ArrayList<Company>();
					}
					list.add(com);
					session.setAttribute("SourceList", list);
					
				}
			}
			
			return modelAndView;
			
		}
		
		

}
