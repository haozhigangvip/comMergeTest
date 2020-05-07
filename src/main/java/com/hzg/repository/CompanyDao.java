package com.hzg.repository;
import com.hzg.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 符合SpringDataJpa的dao层接口规范需要继承如下接口
 * JpaRepository，其中泛型T为操作实体类类型，ID为实体类中主键的属性的数据类型
 *  以上接口封装了CRUD操作
 * JpaSpecificationExecutor 其中泛型T为操作实体类类型
 * 以上接口封装了复查查询操作（包括分页操作）
 */
@Repository

public interface CompanyDao extends JpaRepository<Company,Integer>, JpaSpecificationExecutor<Company> {

    /**
     * JPQL查询方法
     * 根据Company进行查询
     * jpql语句: from Company where companyname=?
     * 配置JPQL语句，方法上加入@Query注解,其中value为以上jpql语句
     */

    @Query(value="From Company where companyname like  %?1%")
    public List<Company> findCompanyByName(String companyname);
    @Query(value="From Company where comID = ?")
    public Company findCompanyByComID(String companyComID);
    

}
