package com.hzg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.hzg.entity.Orders;
public interface OrderDao  extends JpaRepository<Orders,Integer>,JpaSpecificationExecutor<Orders>{
	 @Query(value="From Orders where comID=?")
	 public List<Orders> findAllByComID(String companyComID);
}
