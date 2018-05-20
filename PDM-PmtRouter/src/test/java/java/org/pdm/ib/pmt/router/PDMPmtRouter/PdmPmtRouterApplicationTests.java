package java.org.pdm.ib.pmt.router.PDMPmtRouter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pdm.ib.pmt.router.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PdmPmtRouterApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void contextLoads() {

        Assert.assertNotNull(accountRepository);

    }

}
