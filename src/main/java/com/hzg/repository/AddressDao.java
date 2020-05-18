package com.hzg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.hzg.entity.Address;

public interface AddressDao  extends JpaRepository<Address,Integer>,JpaSpecificationExecutor<Address>{
	 @Query(value="From Address where contID = ?")
	 public List<Address> findAllByContID(String contactID);
}
