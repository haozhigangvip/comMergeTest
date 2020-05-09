package com.hzg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.hzg.entity.Contact;
import com.hzg.entity.Order;

public interface OrderDao  extends JpaRepository<Order,Integer>,JpaSpecificationExecutor<Order>{
	 @Query(value="From Order where comID = ?")
	 public List<Order> findAllByComID(String companyComID);
}