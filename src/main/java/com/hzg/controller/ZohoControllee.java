package com.hzg.controller;

import com.hzg.entity.Company;
import com.hzg.entity.CompanyMergeHistory_Total;
import com.hzg.service.CompanyService;
import com.hzg.service.CompanyServiceImpl;
import com.hzg.utils.myUtils;
import com.hzg.vo.CompanyConvert;
import com.hzg.vo.MergeResult;
import com.hzg.vo.QueryVo;
import com.hzg.vo.content;
import com.hzg.vo.delSession;
import com.hzg.vo.resultList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ZohoControllee {
		@Autowired
		private CompanyService customerService;
		String zoho_login_client_id = "1000.1IZ7HX3BWDNXAUDB25NZ4F74RSICXH";
		String zoho_login_client_secret = "3ba2ec4276263ac5757ce3e1ad7e850aa16a356605";
		String secret_key = zoho_login_client_id;
		String zoho_login_redirect_uri = "http://3017319g5m.qicp.vip/comMergeTest/zoho/callback.action";
		private static final String APPLICATION_JSON = "application/json";

	  	@RequestMapping("/zoho/login")
	    public String zoho_login(HttpSession session,Model model)  {
	  		String zoho_auth_url = "https://accounts.zoho.com/oauth/v2/auth";
	  		String scope="ZohoBooks.settings.READ";
	  		String full_url = zoho_auth_url + "?scope=" + scope + "&client_id=" + zoho_login_client_id + "&response_type=code&redirect_uri=" 
	                 + zoho_login_redirect_uri + "&access_type=offline";
	  		model.addAttribute("scope",scope);
	  		model.addAttribute("client_id",zoho_login_client_id);
	  		model.addAttribute("response_type","code");
	  		model.addAttribute("redirect_uri",zoho_login_redirect_uri);
	  		model.addAttribute("access_type","offline");
	  	    return "redirect:"+zoho_auth_url;
	  		 
	    }
	  	
	  	@RequestMapping("/zoho/callback")
	    public String zoho_callback(HttpServletRequest request,HttpServletResponse response) throws Exception  {
	  		 String code = request.getParameter("code");

	  		 System.out.println("123");
	  		 String data = "{"+
	  		        "\"grant_type\": \"authorization_code\","+
	  		        "\"client_id\":"+ zoho_login_client_id+","+
	  		        "\"client_secret\":"+ zoho_login_client_secret+","+
	  		        "\"redirect_uri\": \"http://3017319g5m.qicp.vip/comMergeTest/zoho/callback.action\","+
	  		        "\"code\":"+ code+"}";
	  		 String token_url = "https://accounts.zoho.com/oauth/v2/token";
	         HttpPost httpPost = new HttpPost(token_url);
	         httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
	         String encoderJson = URLEncoder.encode(data, HTTP.UTF_8);
	         StringEntity se = new StringEntity(encoderJson);		
	         httpPost.setEntity(se);
	         DefaultHttpClient httpClient = new DefaultHttpClient();
	         httpClient.execute(httpPost);
	         
	  		
	  		 
	  		
	  		 
	  		 return token_url;
	  		 
	  	}
	
}
