package com.hzg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.hzg.entity.Contact;
import com.hzg.entity.Quote;

public interface QuoteDao extends JpaRepository<Quote,Integer>,JpaSpecificationExecutor<Quote> {
	 @Query(value="From Quote where comID = ?")
	 public List<Quote> findAllByComID(String companyComID);
}
