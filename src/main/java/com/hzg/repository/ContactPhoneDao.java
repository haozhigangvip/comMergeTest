package com.hzg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.hzg.entity.ContactPhone;

public interface ContactPhoneDao extends JpaRepository<ContactPhone,Integer>,JpaSpecificationExecutor<ContactPhone> {
	 @Query(value="From ContactPhone where contID = ?")
	 public List<ContactPhone> findAllByContactID(String contID);

}
