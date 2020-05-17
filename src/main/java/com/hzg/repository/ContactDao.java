package com.hzg.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hzg.entity.Contact;
@Repository
public interface ContactDao  extends JpaRepository<Contact,Integer>,JpaSpecificationExecutor<Contact>{
	
	@Query(value="From Contact where comID = ?")
	 public List<Contact> findAllByComID(String companyComID);
	@Query(value="From Contact ct ,Company cp where ct.comID=cp.comID and (contid=?1 or name=?1)")
	public List<Object[]> findAllByContIDOrName(String ContID);
	@Query(value="select ct.autoID,ct.contID,ct.name,ct.comID,cp.companyname,ct.delTag From Contact ct,Company cp  where ct.comID=cp.comID and contID = ?")
	 public Object[] findByContID(String contID);
//	 @Query(nativeQuery = true,value="select DISTINCT ct.AutoID,ct.ContID,ct.name,ct.companyID,cp.companyname from contact ct left join  ct.company where ct.ContID=?1 or ct.name=?1")			  
//	 public List<Object> getByContIDorName(String contID);
	 @Query(value="From Contact ct,Company cp where ct.comID=cp.comID and (ct.contID like %?1% or ct.name like %?1%) and (ct.delTag <> 1 or ct.delTag is null)" )			  
	 public Page<Object[]> getByContIDorName(String contID,Pageable pageable);
}
