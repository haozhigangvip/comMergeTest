package com.hzg.service;

import com.hzg.entity.Company;
import com.hzg.entity.CompanyMergeHistory;
import com.hzg.entity.CompanyMergeHistory_Total;

import com.hzg.entity.Contact;
import com.hzg.entity.Invoice;
import com.hzg.entity.Orders;
import com.hzg.entity.Quote;
import com.hzg.repository.CompanyDao;
import com.hzg.repository.CompanyMergeHistoryDao;
import com.hzg.repository.CompanyMergeHistory_TotalDao;
import com.hzg.repository.ContactDao;
import com.hzg.repository.InvoiceDao;
import com.hzg.repository.OrderDao;
import com.hzg.repository.QuoteDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



@Service
@ContextConfiguration(locations = "classpath:ApplicationContext-dao.xml")
public class AccountServiceImpl implements AccountService{
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private QuoteDao quoteDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private CompanyMergeHistory_TotalDao comMerge_Total;
    @Autowired
    private CompanyMergeHistoryDao comMerge;
   
    
    
	@Override
	@Transactional(rollbackFor=Exception.class)
	@Rollback(value=false)
	//如要回滚，需引用 throw new RuntimeException();方法
	public int MergerCompany(List<Company> oldCompanList,Company newCompany){
		int result=1;
		String new_Comp_id=newCompany.getComID();
		String new_Comp_name=newCompany.getCompanyname();
		for (Company company : oldCompanList) {
			String old_Com_id=company.getComID();
			String old_Com_name=company.getCompanyname();
			// 更新quote表
			List<Quote> Quote_list=quoteDao.findAllByComID(old_Com_id);
			if(Quote_list!=null && Quote_list.size()>0){
			
				for (Quote quote : Quote_list) {
					try {
						saveHistory("Quote",old_Com_id,old_Com_name,new_Comp_id,new_Comp_name,quote.getAutoID());
						quote.setComID(new_Comp_id);
						quoteDao.save(quote);
					} catch (Exception e) {
						
						throw new RuntimeException();
					}
				}
			}
			
			
			// 更新order表
			List<Orders> Order_list=orderDao.findAllByComID(old_Com_id);
			if(Order_list!=null && Order_list.size()>0){
				for (Orders orders : Order_list) {
					try {
						saveHistory("Order",old_Com_id,old_Com_name,new_Comp_id,new_Comp_name,orders.getAutoID());
						orders.setComID(new_Comp_id);
						orderDao.save(orders);
					} catch (Exception e) {
						// TODO: handle exception

						throw new RuntimeException();
					}
				}
			}
			
			// 更新Invoice_info表
			List<Invoice> Invoice_list=invoiceDao.findAllByComID(old_Com_id);
			if(Invoice_list!=null && Invoice_list.size()>0){
				for (Invoice invoice : Invoice_list) {
					try {
						saveHistory("Invoice_info",old_Com_id,old_Com_name,new_Comp_id,new_Comp_name,invoice.getAutoID());
						invoice.setComID(new_Comp_id);
						invoiceDao.save(invoice);
					} catch (Exception e) {
						// TODO: handle exception

						throw new RuntimeException();

					}
				}
			}
			// 更新Contact表
			List<Contact> Contact_list=contactDao.findAllByComID(old_Com_id);
			if(Contact_list!=null && Contact_list.size()>0){
				for (Contact contact : Contact_list) {
					try {
						saveHistory("Contact",old_Com_id,old_Com_name,new_Comp_id,new_Comp_name,contact.getAutoID());
						contact.setComID(new_Comp_id);
						contactDao.save(contact);
					} catch (Exception e) {
						// TODO: handle exception

						throw new RuntimeException();

					}
				}
			}
			
			//更新CompanyInfo删除标记
			
			Company cp=updateCompanyinfoDeltag(old_Com_id,1);
			System.out.println("==============================");
			System.out.println(cp.getAutoID());
			saveHistory("CompanyInfo",old_Com_id,old_Com_name,new_Comp_id,new_Comp_name,cp.getAutoID());

		}
		

	
		
		result=0;
		return result;
	}
    
	
	//更新CompanyInfo删除标记
	public Company updateCompanyinfoDeltag(String old_Com_id,int tag){
		// 更新CompanyInfo Deltag
					Company cp=companyDao.findCompanyByComID(old_Com_id);
					System.out.println(old_Com_id);
					cp.setDelTag(tag);
					try {
						companyDao.save(cp);
						
					} catch (Exception e) {
						// TODO: handle exception


						throw new RuntimeException();

					}finally {
						return cp;
					}
	}
	
	
	
	//保存到更新历史记录
	private void saveHistory(String TableName,String oldComID,String oldComName,String newComID,String newComName,int tableKeyID){
		if(oldComID!=null  & newComID!=null){
		Timestamp createtime=new Timestamp(System.currentTimeMillis());
		CompanyMergeHistory_Total comHistory_Total=comMerge_Total.findCompanyMergeHistoryTotalKey(oldComID, newComID);
		
		
		CompanyMergeHistory comHistory=new CompanyMergeHistory();
		comHistory.setTableID(tableKeyID);
		comHistory.setTableName(TableName);
			comHistory.setCreatime(createtime);
			if(comHistory_Total==null){
 			comHistory_Total=new CompanyMergeHistory_Total();
			comHistory_Total.setCompanyID_New(newComID);
			comHistory_Total.setCompanyName_New(newComName);
			comHistory_Total.setCompanyID_Old(oldComID);
			comHistory_Total.setCompanyName_Old(oldComName);
			comHistory_Total.setCreatime(createtime);	
		}
		comHistory.setComMergeHistory_Total(comHistory_Total);
		comHistory_Total.getComMergeHistory().add(comHistory);
			comMerge_Total.save(comHistory_Total);	
		
		}
	
		
		
	}
	
	
    private int toHistory(List<Company> oldCompanList,Company newCompany,Object object){
    	
    	

    	
    	
    	return 0;
    	
    }
    
    
    
    
    
    @Override
    public List<Company> findCompanyByLikeNameOrID(String companyNameOrID){
    	Pageable pg=new PageRequest(0, 25);
    	Specification<Company> spec=new Specification<Company>() {

			@Override
			public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<Object> companyname =root.get("companyname");
				Path<Object> comID =root.get("comID");
				Path<Object> deltag =root.get("delTag");
				Predicate likename= cb.like(companyname.as(String.class), "%"+companyNameOrID+"%");
				Predicate eqid= cb.like(comID.as(String.class), "%"+companyNameOrID+"%");
				Predicate del= cb.notEqual(deltag.as(Integer.class), 1);
				Predicate del1= cb.isNull(deltag.as(Integer.class));
				Predicate or =cb.and(cb.or(likename,eqid),cb.or(del,del1));
				
				// TODO Auto-generated method stub
				return or;
			}
		};
        Page<Company> page=companyDao.findAll(spec,pg);
        
        return page.getContent();
    	}
    
    	@Override
    	public int UpdateCompanyDelTag(String companyID,int value){
    		int result=0;
    		Company com=companyDao.findCompanyByComID(companyID);
    		if(com!=null){
    			com.setDelTag(value);
    			try {
					companyDao.save(com);
					result=1;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
    		}
    		
    		return result;
    	}
    	

    	
    	
    	
    	 @Override
    	    public Company  findCompanyBycomID(String companyComID){
    	    	
    			
    	        Company com=companyDao.findCompanyByComID(companyComID);
    	        
    	        return com;
    	    }
    	 
    	 	@Override
    		public List<CompanyMergeHistory_Total> getListHistory(){
    			List<CompanyMergeHistory_Total> list=comMerge_Total.findAll();
    			for (CompanyMergeHistory_Total companyMergeHistory_Total : list) {
    				companyMergeHistory_Total.setComMergeHistory(null);
				}
    			
    			
    			return list;
    		}
    		
    		@Override
    		@Transactional(rollbackFor=Exception.class)
    		@Rollback(value=false)
    		public int resumeCompany(int id){
    			int res=1;
    			String CompanyID;
    			String CompanyID_New;
    			try{
    			CompanyMergeHistory_Total  comHistory_total=comMerge_Total.findOne(id);
    			if( comHistory_total!=null){
    				CompanyID=comHistory_total.getCompanyID_Old().trim();
    				CompanyID_New=comHistory_total.getCompanyID_New().trim();
    				Set<CompanyMergeHistory> set=comHistory_total.getComMergeHistory();
    				if(set!=null){
    				int tag=1;
    				for (CompanyMergeHistory companyMergeHistory : set) {
    					String tablename=companyMergeHistory.getTableName().trim();
    					int tid=companyMergeHistory.getTableID();
    					switch (tablename) {
						//更新QUOTE表
    					case "Quote":
						Quote quote=quoteDao.findOne(tid);
						if(quote!=null&&quote.getComID().trim().equals(CompanyID_New)){
							quote.setComID(CompanyID);
							quoteDao.save(quote);
						}else{
							tag=0;
							break;
						}
						break;
							
						//更新ORDER表
    					case "Order":
    						Orders order=orderDao.findOne(tid);
    						if(order!=null&&order.getComID().trim().equals(CompanyID_New)){
    							order.setComID(CompanyID);
    							orderDao.save(order);
    						}else{
    							tag=0;
    							break;
    						}	
    						break;
						
						//更新Invoice表
    					case "Invoice_info":
    						Invoice invoice=invoiceDao.findOne(tid);
    						if(invoice!=null&&invoice.getComID().trim().equals(CompanyID_New)){
    							invoice.setComID(CompanyID);
    							invoiceDao.save(invoice);
    						}else{
    							tag=0;
    							break;
    						}
    						
    						break;
						
						//更新contact表
    					case "Contact":
    						Contact contact=contactDao.findOne(tid);
    						if(contact!=null&&contact.getComID().trim().equals(CompanyID_New)){
    							contact.setComID(CompanyID);
    							contactDao.save(contact);
    						}else{
    							tag=0;
    							break;
    						}
    						
    						break;	
    					case "CompanyInfo":
    						//更新CompanyInfo删除标记
    						updateCompanyinfoDeltag(CompanyID,0);
						}
					}
    				//判断各表记录是否缺失，0为缺失
    				if(tag==0){
    					res=2;
        			  
    			       throw new RuntimeException();
    				}else{
    				
				
					comMerge_Total.delete(comHistory_total);
					res=0;}
    				
    				}
    			}}catch(Exception e){
    				System.out.println(e.getMessage()+e.getCause());
    				  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    				  
    			}finally {
    				return res;
				}
    			
    			
    			
    			
    		}
    	
}
 