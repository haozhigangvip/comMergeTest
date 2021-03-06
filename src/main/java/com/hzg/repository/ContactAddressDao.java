package com.hzg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.hzg.entity.Contact;
import com.hzg.entity.ContactAddress;
import com.hzg.entity.Quote;

public interface ContactAddressDao extends JpaRepository<ContactAddress,Integer>,JpaSpecificationExecutor<ContactAddress> {
	 @Query(value="From ContactAddress where contID = ?")
	 public List<ContactAddress> findAllByContactID(String contID);

}
