package com.hzg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.hzg.entity.Interaction;

public interface InteractionDao  extends JpaRepository<Interaction,Integer>,JpaSpecificationExecutor<Interaction>{
	 @Query(value="From Interaction where contID = ?")
	 public List<Interaction> findAllByContID(String contactID);
}
