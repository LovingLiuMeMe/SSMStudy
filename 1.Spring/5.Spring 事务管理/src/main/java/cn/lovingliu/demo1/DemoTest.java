package cn.lovingliu.demo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DemoTest {
    // @Resource(name = "accountService") 原
    @Resource(name = "accountServiceProxy")
    private AccountService accountService;
    @Test
    public void test1(){
        accountService.transfer("aaa","bbb",123.4);
    }
}
