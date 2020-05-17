package com.hzg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.hzg.entity.ContactEmail;


public interface ContactEmailDao extends JpaRepository<ContactEmail,Integer>,JpaSpecificationExecutor<ContactEmail> {
	 @Query(value="From ContactEmail where contID = ?")
	 public List<ContactEmail> findAllByContactID(String contID);

}
