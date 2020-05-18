package com.hzg.service;

import com.hzg.entity.Address;
import com.hzg.entity.Company;
import com.hzg.entity.Contact;
import com.hzg.entity.ContactAddress;
import com.hzg.entity.ContactEmail;
import com.hzg.entity.ContactMerge;
import com.hzg.entity.ContactMergeItem;
import com.hzg.entity.ContactPhone;
import com.hzg.entity.Interaction;
import com.hzg.entity.Orders;
import com.hzg.entity.Quote;
import com.hzg.repository.AddressDao;
import com.hzg.repository.ContactAddressDao;
import com.hzg.repository.ContactDao;
import com.hzg.repository.ContactEmailDao;
import com.hzg.repository.ContactMergeDao;
import com.hzg.repository.ContactMergeItemDao;
import com.hzg.repository.ContactPhoneDao;
import com.hzg.repository.InteractionDao;
import com.hzg.repository.OrderDao;
import com.hzg.repository.QuoteDao;
import com.hzg.utils.myUtils;
import com.hzg.vo.contactResult;
import com.hzg.vo.resultContactList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



@Service
@ContextConfiguration(locations = "classpath:ApplicationContext-dao.xml")
public class ContactServiceImpl implements ContactService{
    
    @Autowired
    private QuoteDao quoteDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ContactPhoneDao contactPhoneDao;
    @Autowired
    private ContactAddressDao contactAddressDao;
    @Autowired
    private ContactEmailDao contactEmailDao;
   
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private ContactMergeDao contMergeDao;
    @Autowired
    private ContactMergeItemDao contMergeItemDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private InteractionDao interactionDao;
    
    
	@Override
	@Transactional(rollbackFor=Exception.class)
	@Rollback(value=false)
	//如要回滚，需引用 throw new RuntimeException();方法
	public int MergerContact(List<Contact> oldContactList,Contact newContact){
		int result=1;
		String new_Cont_id=newContact.getContID();
		
		for (Contact contact : oldContactList) {
			String old_Cont_id=contact.getContID();
			

			
			// 更新quote表
			List<Quote> Quote_list=quoteDao.findAllByContactID(old_Cont_id);
			if(Quote_list!=null && Quote_list.size()>0){
			
				for (Quote quote : Quote_list) {
					try {
						saveHistory("Quote",contact,newContact,quote.getAutoID());
						quote.setContactID(new_Cont_id);
						quoteDao.save(quote);
					} catch (Exception e) {
						
						throw new RuntimeException();
					}
				}
			}
			
			
			// 更新order表
			List<Orders> Order_list=orderDao.findAllByContactID(old_Cont_id);
			if(Order_list!=null && Order_list.size()>0){
				for (Orders orders : Order_list) {
					try {
						saveHistory("Order",contact,newContact,orders.getAutoID());
						orders.setContactID(new_Cont_id);
						orderDao.save(orders);
					} catch (Exception e) {
						// TODO: handle exception

						throw new RuntimeException();
					}
				}
			}
			
			// 更新ContactPhone表
			List<ContactPhone> contPhone_list=contactPhoneDao.findAllByContactID(old_Cont_id);
				if(contPhone_list!=null && contPhone_list.size()>0){
					for (ContactPhone contPhone : contPhone_list) {
						try {
							saveHistory("ContactPhone",contact,newContact,contPhone.getAutoID());
							contPhone.setContID(new_Cont_id);
							contactPhoneDao.save(contPhone);
						} catch (Exception e) {
							// TODO: handle exception
							throw new RuntimeException();
						}
					}
				}
				
				// 更新ContactEmail表
				List<ContactEmail> contEmail_list=contactEmailDao.findAllByContactID(old_Cont_id);
					if(contEmail_list!=null && contEmail_list.size()>0){
						for (ContactEmail contEmail : contEmail_list) {
							try {
								saveHistory("ContactEmail",contact,newContact,contEmail.getAutoID());
								contEmail.setContID(new_Cont_id);
								contactEmailDao.save(contEmail);
							} catch (Exception e) {
								// TODO: handle exception
								throw new RuntimeException();
							}
						}
					}
				
					// 更新ContactAddress表
					List<ContactAddress> contAddress_list=contactAddressDao.findAllByContactID(old_Cont_id);
						if(contAddress_list!=null && contAddress_list.size()>0){
							for (ContactAddress contAddress : contAddress_list) {
								try {
									saveHistory("ContactAddress",contact,newContact,contAddress.getAutoID());
									contAddress.setContID(new_Cont_id);
									contactAddressDao.save(contAddress);
								} catch (Exception e) {
									// TODO: handle exception
									throw new RuntimeException();
								}
							}
						}

						// 更新Address表
						List<Address> address_list=addressDao.findAllByContID(old_Cont_id);
							if(address_list!=null && address_list.size()>0){
								for (Address address : address_list) {
									try {
										saveHistory("Address",contact,newContact,address.getAutoID());
										address.setContID(new_Cont_id);
										addressDao.save(address);
									} catch (Exception e) {
										// TODO: handle exception
										throw new RuntimeException();
									}
								}
							}

							// 更新Address表
							List<Interaction> interaction_list=interactionDao.findAllByContID(old_Cont_id);
								if(interaction_list!=null && interaction_list.size()>0){
									for (Interaction interaction : interaction_list) {
										try {
											saveHistory("Interaction",contact,newContact,interaction.getAutoID());
											interaction.setContID(new_Cont_id);
											interactionDao.save(interaction);
										} catch (Exception e) {
											// TODO: handle exception
											throw new RuntimeException();
										}
									}
								}
			
			//更新Contact删除标记
						 	
			Contact cont=updateContDelTag(old_Cont_id,1);
			System.out.println("==============================");
			System.out.println(cont.getAutoID());
			saveHistory("Contact",contact,newContact,cont.getAutoID());
		}
		

	
		
		result=0;
		return result;
	}
    
	
	
	//保存到更新历史记录
	private void saveHistory(String TableName,Contact oldContact,Contact newContact,int tableKeyID){
		String oldContID=oldContact.getContID();
		String newContID=newContact.getContID();
		String oldContName=oldContact.getName();
		String newContName=newContact.getName();
		String oldComID=oldContact.getComID();
		String newComID=newContact.getComID();
		String oldComName=oldContact.getCompanyname();
		String newComName=newContact.getCompanyname();
		
		if(oldContID!=null  & newContID!=null){
		Timestamp createtime=new Timestamp(System.currentTimeMillis());
		ContactMerge contMerge=contMergeDao.findContactMergeByKey(oldContID, newContID);
		
		
		ContactMergeItem contMergeItem=new ContactMergeItem();
		contMergeItem.setTableID(tableKeyID);
		contMergeItem.setTableName(TableName);
		contMergeItem.setCreatime(createtime);
			if(contMerge==null){
				contMerge=new ContactMerge();
				contMerge.setContactID_New(newContID);
				contMerge.setContactName_New(newContName);
				contMerge.setContactID_Old(oldContID);
				contMerge.setContactName_Old(oldContName);
				contMerge.setCreatime(createtime);	
				contMerge.setCompanyID_Old(oldComID);
				contMerge.setCompanyName_Old(oldComName);
				contMerge.setCompanyID_New(newComID);
				contMerge.setCompanyName_New(newComName);
				
			}
			
			contMergeItem.setContactMerge(contMerge);
			contMerge.getContactMergeItem().add(contMergeItem);
			contMergeDao.save(contMerge);	
		
		}
	
		
		
	}
	
	//更新Contact删除标记
	@Override
	public Contact updateContDelTag(String contID,int value){
		Contact cont=myUtils.arrayTOContact((Object[])contactDao.findByContID(contID)[0]);
		if(cont!=null){
			cont.setDelTag(value);
			try {
				contactDao.save(cont);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		
		return cont;
	}
	
    
    
    
    
    
    
    @Override
    public List<Contact> findContactByLikeNameOrID(String ContactNameOrID){
    	Pageable pg=new PageRequest(0, 25);
    	Specification<Contact> spec=new Specification<Contact>() {

			@Override
			public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<Object> Contactname =root.get("name");
				Path<Object> contID =root.get("contID");
				Path<Object> deltag =root.get("delTag");
				Predicate likename= cb.like(Contactname.as(String.class), "%"+ContactNameOrID+"%");
				Predicate eqid= cb.like(contID.as(String.class), "%"+ContactNameOrID+"%");
				Predicate del= cb.notEqual(deltag.as(Integer.class), 1);
				Predicate dell= cb.isNull(deltag.as(Integer.class));
				Predicate or =cb.and(cb.or(likename,eqid),cb.or(del,dell));
				System.out.println(or.getAlias());
				// TODO Auto-generated method stub
				return or;
			}
		};
	
        Page<Contact> page=contactDao.findAll(spec,pg);
        
        return page.getContent();
    	}
    
    	

    	
    	
    	
    	 	@Override
    	 	public Contact findContactBycontID(String contID){
    		Contact  ct=myUtils.arrayTOContact((Object[])contactDao.findByContID(contID)[0]);
    	      return ct;
    	    }
    	 	
    	 
    	 	@Override
    		public List<ContactMerge> getContactListHistory(){
    			List<ContactMerge> list=contMergeDao.findAll();
    			for (ContactMerge cont : list) {
    				cont.setContactMergeItem(null);
				}
    			
    			
    			return list;
    		}



	
    		
    		@Override
    		@Transactional(rollbackFor=Exception.class)
    		@Rollback(value=false)
    		public int resumeContact(int id){
    			int res=1;
    			String ContactID_Old;
    			String ContactID_New;
    			try{
    			ContactMerge  contactMerge=contMergeDao.findOne(id);
    			if( contactMerge!=null){
    				ContactID_Old=contactMerge.getContactID_Old().trim();
    				ContactID_New=contactMerge.getContactID_New().trim();
    				Set<ContactMergeItem> set=contactMerge.getContactMergeItem();
    				if(set!=null){
    				int tag=1;
    				for (ContactMergeItem contactMergeItem : set) {
    					String tablename=contactMergeItem.getTableName().trim();
    					int tid=contactMergeItem.getTableID();
    					switch (tablename) {
						//更新QUOTE表
    					
    					case "Quote":
						Quote quote=quoteDao.findOne(tid);
						if(quote!=null&&quote.getContactID().trim().equals(ContactID_New)){
							quote.setContactID(ContactID_Old);
							quoteDao.save(quote);
						}else{
							tag=0;
							break;
						}
						break;
							
						//更新ORDER表
    					case "Order":
    						Orders order=orderDao.findOne(tid);
    						if(order!=null&&order.getContactID().trim().equals(ContactID_New)){
    							order.setContactID(ContactID_Old);
    							orderDao.save(order);
    						}else{
    							tag=0;
    							break;
    						}	
    						break;
						
						//更新Invoice表
    					case "ContactPhone":
    						ContactPhone contactPhone=contactPhoneDao.findOne(tid);
    						if(contactPhone!=null&&contactPhone.getContID().trim().equals(ContactID_New)){
    							contactPhone.setContID(ContactID_Old);
    							contactPhoneDao.save(contactPhone);
    						}else{
    							tag=0;
    							break;
    						}
    						
    						break;
						
    						//更新Invoice表
    					case "ContactEmail":
    						ContactEmail contactEmail=contactEmailDao.findOne(tid);
    						if(contactEmail!=null&&contactEmail.getContID().trim().equals(ContactID_New)){
    							contactEmail.setContID(ContactID_Old);
    							contactEmailDao.save(contactEmail);
    						}else{
    							tag=0;
    							break;
    						}    						
    						break;    	
    						
    						//更新ContactAddress表
    					case "ContactAddress":
    						ContactAddress contactAddress=contactAddressDao.findOne(tid);
    						if(contactAddress!=null&&contactAddress.getContID().trim().equals(ContactID_New)){
    							contactAddress.setContID(ContactID_Old);
    							contactAddressDao.save(contactAddress);
    						}else{
    							tag=0;
    							break;
    						}    						
    						break;    						

       						//更新ContactAddress表
        					case "Address":
        						Address address=addressDao.findOne(tid);
        						if(address!=null&&address.getContID().trim().equals(ContactID_New)){
        							address.setContID(ContactID_Old);
        							addressDao.save(address);
        						}else{
        							tag=0;
        							break;
        						}    						
        						break;    	
        						
          					//更新ContactAddress表
        					case "Interaction":
        						Interaction interaction=interactionDao.findOne(tid);
        						if(interaction!=null&&interaction.getContID().trim().equals(ContactID_New)){
        							interaction.setContID(ContactID_Old);
        							interactionDao.save(interaction);
        						}else{
        							tag=0;
        							break;
        						}    						
        						break;    						

    						
    						
						//更新contact表删除标记
    					case "Contact":
    						Contact cont=updateContDelTag(ContactID_Old,0);
    						if(cont==null){
    							tag=0;
    						}
    						break;
						}
					}
    				//判断各表记录是否缺失，0为缺失
    				if(tag==0){
    					res=2;
        			  
    			       throw new RuntimeException();
    				}else{
    				//删除历史记录
					contMergeDao.delete(contactMerge);
					res=0;}
    				
    				}
    			}}catch(Exception e){
    				System.out.println(e.getMessage()+e.getCause());
    				  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    				  
    			}finally {
    				return res;
				}
    			
    			
    			
    			
    		}
    		@Override
    		public List<Contact> testlist(String key){
    	    	Pageable pg=new PageRequest(0, 25);

    			Page<Object[]> result=contactDao.getByContIDorName(key,pg);
    			
    			List<Contact> list=new ArrayList<Contact>();
    			for (Object[] row : result) {
    				
     				Contact cont = (Contact)row[0];
    				System.out.println(cont);
    				Company comp= (Company)row[1];
    				Contact rs=new Contact();
    				rs.setAutoID(cont.getAutoID());
	    			rs.setComID(cont.getComID());	
    			    rs.setContID(cont.getContID());
    			    rs.setName(cont.getName());
    			    rs.setCompanyname(comp.getCompanyname());
    			    
    			    list.add(rs);
    			}
    			return list;
    		}
    		
    		
    	
}
 