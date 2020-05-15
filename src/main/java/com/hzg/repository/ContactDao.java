package com.hzg.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hzg.entity.Company;
import com.hzg.entity.Contact;
@Repository
public interface ContactDao  extends JpaRepository<Contact,Integer>,JpaSpecificationExecutor<Contact>{
	
	@Query(value="From Contact where comID = ?")
	 public List<Contact> findAllByComID(String companyComID);
	@Query(value="From Contact ct ,Company cp where ct.comID=cp.comID and (contid=?1 or name=?1)")
	public List<Object[]> findAllByContIDOrName(String ContID);
	@Query(value="From Contact where contID = ?")
	 public Contact findByContID(String contID);
}
