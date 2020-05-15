package com.hzg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzg.entity.Company;
import com.hzg.entity.Contact;
import com.hzg.service.ContactService;
import com.hzg.utils.myUtils;
import com.hzg.vo.QueryVo;
import com.hzg.vo.contactQueryVo;
import com.hzg.vo.delSession;

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
        
        	ls=contactService.findContactByLikeNameOrID(cname);
            if(ls!=null && session_ls!=null){
        	ls=myUtils.chkContactList(ls, session_ls);
        	}
        }
        

        contactQueryVo cv=new contactQueryVo();
        cv.setSuccess(1);
        cv.setListContact(ls);
        
       
        return cv;
	}
	

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
	
	
}
