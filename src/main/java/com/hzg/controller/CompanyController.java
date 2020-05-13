package com.hzg.controller;

import com.hzg.entity.Company;
import com.hzg.entity.CompanyMergeHistory_Total;
import com.hzg.service.CompanyService;
import com.hzg.service.CompanyServiceImpl;
import com.hzg.utils.myUtils;
import com.hzg.vo.CompanyConvert;
import com.hzg.vo.CompanyQueryVo;
import com.hzg.vo.MergeResult;
import com.hzg.vo.resultList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompanyController {
		@Autowired
		private CompanyService customerService;
	
	  	@RequestMapping("/searchCompany")
	    @ResponseBody
	    public  CompanyQueryVo json(@RequestBody  Company company ,HttpSession session)  {
	  		
	        String cname=company.getCompanyname().trim();
	        List<Company> ls=new ArrayList<Company>();
	        List<Company> session_list=(List<Company>)session.getAttribute("SourceList");
	        List<Company> session_list1=(List<Company>)session.getAttribute("TargetList");
	        List<Company> session_ls=new ArrayList<Company>();
	        if(session_list!=null){
	        	session_ls.addAll(session_list);
	        }
	        if(session_list1!=null){
	        	session_ls.addAll(session_list1);
	        }
	        
	        if(cname!=null &cname!=""){
	        
	        	ls=customerService.findCompanyByLikeNameOrID(cname);
	            if(ls!=null && session_ls!=null){
	        	ls=myUtils.chkCompanyList(ls, session_ls);
	        	}
	        }


	        CompanyQueryVo cv=new CompanyQueryVo();
	        cv.setSuccess(1);
	        cv.setListCompany(ls);

	       
	        return cv;
	    } 
	  	
		
		
	
			
		@RequestMapping("/clearSourceList")
	    @ResponseBody
	    public  void  clearSourceList(HttpSession session,HttpServletResponse response)  {
			
			session.setAttribute("SourceList",null);
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@RequestMapping("/removeSourceList")
	    public  String  removeSourceList(@RequestParam("comID") String comID,@RequestParam("session_name") String session_name,HttpSession session,HttpServletResponse response)  {
			System.out.println("sss:"+session_name);
			List<Company> ls = (List<Company>)session.getAttribute(session_name);
			 List<Company> newls=new ArrayList<Company>();
			 if(ls!=null){
			 for (Company company : ls) {
				if(!(company.getComID().trim().equals(comID))){
					newls.add(company);
				}
			}
			
			session.setAttribute(session_name, newls);
			 }
			 
			return "redirect:/";
		}
		
		@RequestMapping("/addSourceList")
	    @ResponseBody
	    public  void  addSourceList(HttpServletRequest request,HttpSession session,HttpServletResponse response)  {
			String para=request.getParameter("myname");
			int e=0;
			
			if(para!=null){
			e=para.indexOf("-");
			}
			if( e>0){
				String comid=para.substring(0,e);
		
				Company com=customerService.findCompanyBycomID(comid);
				if(com!=null){
					
					
					List<Company> list=(List<Company>)session.getAttribute("SourceList");
					
					if(list==null){
						list=new ArrayList<Company>();
					}
					
					if(myUtils.chkCompanyList(list,com)==0){
					list.add(com);
					session.setAttribute("SourceList", list);
					}
				}
			}
			
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			
		}
		
		
		@RequestMapping("/addTargetList")
	    @ResponseBody
	    public  void  addTargetList(HttpServletRequest request,HttpSession session,HttpServletResponse response)  {
			String para=request.getParameter("myname_new");
			int e=0;
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.setViewName("/index.jsp");
			if(para!=null){
			e=para.indexOf("-");
			}
			if( e>0){
				String comid=para.substring(0,e);
		
				Company com=customerService.findCompanyBycomID(comid);
				if(com!=null){
					
					
					List<Company> list=new ArrayList<Company>();
					
					
					if(myUtils.chkCompanyList(list,com)==0){
					list.add(com);
					session.setAttribute("TargetList", list);
					}
				}
			}
			
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			
		}
		
		@RequestMapping(value="/mergeCompany",produces="application/json")
	    public @ResponseBody MergeResult mergeCompany(HttpSession session) throws InterruptedException{
			List<Company> oldList=(List<Company>)session.getAttribute("SourceList");
			List<Company> newList=(List<Company>)session.getAttribute("TargetList");
			String message="合并完成";
			int code=0;
			 
			if(oldList==null||oldList.size()<=0){
				message="操作失败，请选择待合并源公司";
				code=2;
				
			}else if(newList==null || newList.size()<=0){
				message="操作失败，请选择合并目标公司";
				code=2;
			}else{
			//开始合并
			code=customerService.MergerCompany(oldList, newList.get(0));

			}
			
			if(code==1){
				
				message="合并失败，请联系管理员";
			}else if(code==0)
			{
				session.setAttribute("SourceList",null);
				session.setAttribute("TargetList",null);
			}
			
			
			MergeResult result=new MergeResult();
			result.setCode(code);
			result.setMessage(message);
			System.out.println(result.getMessage());
			return result;
			
		}
		
		
		@RequestMapping("/listhistory")
		@ResponseBody
	    public  resultList listHistory(CompanyMergeHistory_Total key)  {
	    	int code=0;
	    	List<CompanyMergeHistory_Total> list=new ArrayList<CompanyMergeHistory_Total>();
	    	try{
	    		list=customerService.getListHistory();
	    	}catch(Exception e)
	    	{
	    		code=1;
	    	}
	    	resultList result=new resultList();
	    	result.setData(list);
	    	result.setCode(code);
	    	for (CompanyMergeHistory_Total dd: result.getData()) {
				System.out.println(dd.toString());
			}
			return result;
		}
		
		@RequestMapping("/resumeCompany")
		@ResponseBody
		public MergeResult resumeCompany(@RequestBody  CompanyMergeHistory_Total cm_total){
			int res=1;		
			String msg="不能空参";
			MergeResult result=new MergeResult();
			if(cm_total!=null){
			int id=cm_total.getAutoID();
			res=customerService. resumeCompany(id);
			if( res==0){
			msg="还原成功";}else
			{
				msg="恢复的记录不存在，还原失败";
			}
			}

			result.setMessage(msg);
			result.setCode(res);
			return result;
		}

}
