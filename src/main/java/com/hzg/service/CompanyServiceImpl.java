package com.hzg.service;

import com.hzg.entity.Company;
import com.hzg.entity.CompanyMergeHistory;
import com.hzg.entity.Quote;
import com.hzg.repository.CompanyDao;
import com.hzg.repository.CompanyMergeHistoryDao;
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

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



@Service
@ContextConfiguration(locations = "classpath:ApplicationContext-dao.xml")
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private QuoteDao quoteDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private ContactDao contacdDao;
    
    @Autowired
    private CompanyMergeHistoryDao comMerge;
   
    
    
	@Override
	@Transactional(timeout = 60,rollbackFor=Exception.class)
	//如要回滚，需引用 throw new RuntimeException();方法
	public int MergerCompany(List<Company> oldCompanList,Company newCompany){
		int result=0;
		String new_Comp_id=newCompany.getComID();
		
		
		
		
		for (Company company : oldCompanList) {
			String old_Com_id=company.getComID();
			// 操作quote表
			List<Quote> list=quoteDao.findAllByComID(company.getComID());
			if(list!=null && list.size()>0){
			for (Quote quote : list) {
				try {
					
					CompanyMergeHistory comHistory=comMerge.findCompanyMergeHistoryByComID(old_Com_id);
					if(comHistory==null){
					comHistory= new CompanyMergeHistory();
					comHistory.setCreatime(new Timestamp(System.currentTimeMillis()));
					}
					comHistory.setCompanyID_Old(old_Com_id);
					comHistory.setCompanyID_New(new_Comp_id);
					comHistory.setTableID(quote.getAutoID());
					comHistory.setTableName("Quote");

					comMerge.save(comHistory);
					quote.setComID(new_Comp_id);
					
					quoteDao.save(quote);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			
			
			
			
			
			
			// 更新CompanyInfo Deltag
			Company cp=companyDao.findCompanyByComID(company.getComID());
			cp.setDelTag(1);
			try {
				companyDao.save(cp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		}
	
		
		
		return result;
	}
    
    private int toHistory(List<Company> oldCompanList,Company newCompany,Object object){
    	
    	

    	
    	
    	return 0;
    	
    }
    
    
    
    
    
    @Override
    public List<Company> findCompanyByLikeNameOrID(String companyNameOrID){
    	Pageable pg=new PageRequest(0, 30);
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
		System.out.println(spec.toString());
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
    	public int ConvertCompany(String sourceCompanyID, String targetCompanyID) {
    		int result=0;
    		
    		if(sourceCompanyID!=null &&sourceCompanyID!=""&&targetCompanyID!=null && targetCompanyID!="" ){
    			try{
    			if(comMerge.findCompanyMergeHistoryByComID(sourceCompanyID)==null){
    			CompanyMergeHistory compMergeHistory=new CompanyMergeHistory();
    			compMergeHistory.setCompanyID_New(targetCompanyID);
    			compMergeHistory.setCompanyID_Old(sourceCompanyID);
    			compMergeHistory.setCreatime(new Timestamp(System.currentTimeMillis()));
    			comMerge.save(compMergeHistory);
    			
    			result=1;
    			}
    			}catch(Exception e)
    			{
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
    	
}
 