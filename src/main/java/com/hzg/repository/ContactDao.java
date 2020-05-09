package com.hzg.repository;

import java.util.List;

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
}
