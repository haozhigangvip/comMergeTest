package com.hzg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.hzg.entity.Contact;
import com.hzg.entity.Invoice;

public interface InvoiceDao  extends JpaRepository<Invoice,Integer>,JpaSpecificationExecutor<Invoice>{
	 @Query(value="From Invoice where comID = ?")
	 public List<Invoice> findAllByComID(String companyComID);
}
