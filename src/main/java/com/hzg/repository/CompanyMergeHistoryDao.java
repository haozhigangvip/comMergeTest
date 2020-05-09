package com.hzg.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hzg.entity.Company;
import com.hzg.entity.CompanyMergeHistory;


/**
 * 符合SpringDataJpa的dao层接口规范需要继承如下接口
 * JpaRepository，其中泛型T为操作实体类类型，ID为实体类中主键的属性的数据类型
 *  以上接口封装了CRUD操作
 * JpaSpecificationExecutor 其中泛型T为操作实体类类型
 * 以上接口封装了复查查询操作（包括分页操作）
 */
@Repository
public interface CompanyMergeHistoryDao extends JpaRepository<CompanyMergeHistory,Integer>, JpaSpecificationExecutor<CompanyMergeHistory> {
		@Query(value="From CompanyMergeHistory where CompanyID_Old =?")
	    public CompanyMergeHistory findCompanyMergeHistoryByComID(String ComID);

   
}
