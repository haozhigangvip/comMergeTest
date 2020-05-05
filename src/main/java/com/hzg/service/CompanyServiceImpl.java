package com.hzg.service;

import com.hzg.entity.Company;
import com.hzg.repository.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
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
   
    @Override
    public List<Company> findCompanyByLikeNameOrID(String companyNameOrID){
    	Pageable pg=new PageRequest(0, 10);
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
}
 