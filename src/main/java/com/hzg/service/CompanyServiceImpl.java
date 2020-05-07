package com.hzg.service;

import com.hzg.entity.Company;
import com.hzg.entity.CompanyMergeHistory;
import com.hzg.repository.CompanyDao;
import com.hzg.repository.CompanyMergeHistoryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
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
    private CompanyMergeHistoryDao comMerge;
   
    @Override
    public List<Company> findCompanyByLikeNameOrID(String companyNameOrID){
    	Pageable pg=new PageRequest(0, 30);
    	Specification<Company> spec=new Specification<Company>() {

			@Override
			public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<Object> companyname =root.get("companyname");
				Path<Object> comID =root.get("comID");
				Predicate likename= cb.like(companyname.as(String.class), "%"+companyNameOrID+"%");
				Predicate eqid= cb.like(comID.as(String.class), "%"+companyNameOrID+"%");
				Predicate or =cb.or(likename,eqid);
				// TODO Auto-generated method stub
				return or;
			}
		};
		System.out.println(spec.toString());
        Page<Company> page=companyDao.findAll(spec,pg);
        
        return page.getContent();
    }
    
    	@Override
    	public int UpdateCompanyDelTarg(String companyID,int value){
    		int result=0;
    		Company com=companyDao.findCompanyByComID(companyID);
    		if(com!=null){
    			com.setDelTarg(value);
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
    			CompanyMergeHistory compMergeHistory=new CompanyMergeHistory();
    			compMergeHistory.setCompanyID_New(targetCompanyID);
    			compMergeHistory.setCompanyID_Old(sourceCompanyID);
    			compMergeHistory.setCreatime(new Timestamp(System.currentTimeMillis()));
    			comMerge.save(compMergeHistory);
    			result=1;
    			}catch(Exception e)
    			{
    				System.out.println(e.getMessage());
    			}
    		}
    		
    		return result;	
    	}
}
 