package comMerge;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.hzg.entity.Contact;
import com.hzg.repository.CompanyDao;
import com.hzg.repository.CompanyMergeHistoryDao;
import com.hzg.repository.CompanyMergeHistory_TotalDao;
import com.hzg.repository.ContactDao;
import com.hzg.repository.InvoiceDao;
import com.hzg.repository.OrderDao;
import com.hzg.repository.QuoteDao;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring容器的配置文件，locations为本地
@ContextConfiguration(locations = "classpath:ApplicationContext-dao.xml")
public class test {
		@Autowired
	    private CompanyDao companyDao;
	    @Autowired
	    private QuoteDao quoteDao;
	    @Autowired
	    private OrderDao orderDao;
	    @Autowired
	    private InvoiceDao invoiceDao;
	    @Autowired
	    private ContactDao contactDao;
	    @Autowired
	    private CompanyMergeHistory_TotalDao comMerge_Total;
	    @Autowired
	    private CompanyMergeHistoryDao comMerge;
	   
	
@Test
public void test(){
	
	List<Object[]> list=contactDao.findAllByContIDOrName("CT10000004");
	for (Object[] objects : list) {
		for(Object obj:objects){
			System.out.println(obj.toString());
		}
	}
	
	
}

}
