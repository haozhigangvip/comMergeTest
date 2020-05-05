package comMerge;
import com.hzg.repository.CompanyDao;
import com.hzg.entity.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//声明spring提供的单元测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring容器的配置文件，locations为本地
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")

public class test {
    @Autowired //转入companyDao
    private CompanyDao companyDao;
    @Test
    /**
     *  根据ID查询一条记录(实时加载）
     *  Getone方法
     *  使用前必须开启开启事务 @Transactional
     */
    public void testFindAll(){
        List<Company> list=companyDao.findAll();
        for (Company company : list) {
			System.out.println(company.toString());
		}
    }

}
