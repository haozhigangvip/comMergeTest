package com.hzg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzg.entity.Company;
import com.hzg.entity.CompanyMergeHistory_Total;
import com.hzg.entity.Contact;
import com.hzg.entity.ContactMerge;
import com.hzg.repository.ContactMergeDao;
import com.hzg.service.ContactService;
import com.hzg.utils.myUtils;
import com.hzg.vo.MergeResult;
import com.hzg.vo.QueryVo;
import com.hzg.vo.contactQueryVo;
import com.hzg.vo.contactResult;
import com.hzg.vo.delSession;
import com.hzg.vo.resultContactList;
import com.hzg.vo.resultList;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/searchContact")
    @ResponseBody
    public contactQueryVo getContact(@RequestBody  Contact contact ,HttpSession session)  {
	    String cname=contact.getName();
      

        List<Contact> ls=new ArrayList<Contact>();
        List<Contact> session_list=(List<Contact>)session.getAttribute("contactSourceList");
        List<Contact> session_list1=(List<Contact>)session.getAttribute("contactTargetList");
        List<Contact> session_ls=new ArrayList<Contact>();
        if(session_list!=null){
        	session_ls.addAll(session_list);
        }
        if(session_list1!=null){
        	session_ls.addAll(session_list1);
        }
        
        if(cname!=null &cname!=""){
        
        	 ls=contactService.testlist(cname);
            if(ls!=null && session_ls!=null){
           	ls=myUtils.chkContactList(ls, session_ls);
           	}
            
        	contactQueryVo cv=new contactQueryVo();
            cv.setSuccess(1);
            cv.setListContact(ls);
             
             
              return cv;
        }
        
        return null;
	}
	
//	@RequestMapping("/searchContact1")
//    @ResponseBody
//    public contactQueryVo getContact1(@RequestBody  Contact contact ,HttpSession session)  {
//	    String cname=contact.getName();
//      
//
//        List<Contact> ls=new ArrayList<Contact>();
//        List<Contact> session_list=(List<Contact>)session.getAttribute("contactSourceList");
//        List<Contact> session_list1=(List<Contact>)session.getAttribute("contactTargetList");
//        List<Contact> session_ls=new ArrayList<Contact>();
//        if(session_list!=null){
//        	session_ls.addAll(session_list);
//        }
//        if(session_list1!=null){
//        	session_ls.addAll(session_list1);
//        }
//        
//        if(cname!=null &cname!=""){
//        
//        	ls=contactService.findContactByLikeNameOrID(cname);
//            if(ls!=null && session_ls!=null){
//        	ls=myUtils.chkContactList(ls, session_ls);
//        	}
//        }
//        
//
//        contactQueryVo cv=new contactQueryVo();
//        cv.setSuccess(1);
//        cv.setListContact(ls);
//       
//       
//        return cv;
//	}
//	

	@RequestMapping("/addContactTargetList")
    @ResponseBody
    public  List<Contact>  addContactTargetList(@RequestBody  Contact contact, HttpServletRequest request,HttpSession session,HttpServletResponse response)  {

			String contID=contact.getContID();
			List<Contact> list=null;
			Contact cont=contactService.findContactBycontID(contID);
			if(cont!=null){
				
				
				list=new ArrayList<Contact>();
				if(myUtils.chkContactList(list,cont)==0){
				list.add(cont);
				session.setAttribute("contactTargetList", list);					
				}
				
			}
			
			return list;
		
	}
	@RequestMapping("/addContactSourceList")
    @ResponseBody
    public  List<Contact>  addContactSourceList(@RequestBody  Contact contact, HttpServletRequest request,HttpSession session,HttpServletResponse response)  {

			String contID=contact.getContID();
			List<Contact> list=(List<Contact>)session.getAttribute("contactSourceList");
			if(list==null){
				list=new ArrayList<Contact>();
			}

			Contact cont=contactService.findContactBycontID(contID);
			if(cont!=null){
				if(myUtils.chkContactList(list,cont)==0){
				list.add(cont);
				session.setAttribute("contactSourceList", list);					
				}
				
			}
			
			return list;
		
	}
	
	@RequestMapping("/removeContactSession")
	@ResponseBody
    public  List<Contact>  removeContactSession(@RequestBody delSession delsession, HttpSession session)  {
		String contid=delsession.getComID();
		String session_name=delsession.getSessionName();

		List<Contact> ls = (List<Contact>)session.getAttribute(session_name);
		List<Contact> newls=new ArrayList<Contact>();
		 if(ls!=null){
		 for (Contact contact : ls) {
			if(!(contact.getContID().trim().equals(contid))){
				newls.add(contact);
			}
		}
		
		 session.setAttribute(session_name, newls);
		 }

		 return newls;
	}
	
	@RequestMapping(value="/mergeContact",produces="application/json")
    public @ResponseBody MergeResult mergeContact(HttpSession session) throws InterruptedException{
		List<Contact> oldList=(List<Contact>)session.getAttribute("contactSourceList");
		List<Contact> newList=(List<Contact>)session.getAttribute("contactTargetList");
		String message="合并完成";
		int code=0;
		 
		if(oldList==null||oldList.size()<=0){
			message="操作失败，请选择需要合并的联系人";
			code=2;
			
		}else if(newList==null || newList.size()<=0){
			message="操作失败，请选合并后的新联系人";
			code=2;
		}else{
		//开始合并
		code=contactService.MergerContact(oldList, newList.get(0));

		}
		
		if(code==1){
			
			message="合并失败，请联系管理员";
		}else if(code==0)
		{
			session.setAttribute("contactSourceList",null);
			session.setAttribute("contactTargetList",null);
		}
		
		
		MergeResult result=new MergeResult();
		result.setCode(code);
		result.setMessage(message);
		return result;
		
	}
	
	@RequestMapping("/listContacthistory")
	@ResponseBody
    public  resultContactList listContactHistory(ContactMerge key)  {
    	int code=0;
    	List<ContactMerge> list=new ArrayList<ContactMerge>();
    	try{
    		list=contactService.getContactListHistory();
    		
    	}catch(Exception e)
    	{
    		code=1;
    	}
    	resultContactList result=new resultContactList();
    	result.setData(list);
    	result.setCode(code);

		return result;
	}
	@RequestMapping("/resumeContact")
	@ResponseBody
	public MergeResult resumeContact(@RequestBody  ContactMerge contactMerge){
		int res=1;		
		String msg="不能空参";
		MergeResult result=new MergeResult();
		if(contactMerge!=null){
		int id=contactMerge.getAutoID();
		res=contactService.resumeContact(id);
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
