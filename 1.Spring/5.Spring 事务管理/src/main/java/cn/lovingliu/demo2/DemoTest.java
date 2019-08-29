package cn.lovingliu.demo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class DemoTest {
    @Resource(name = "accountService")// 自动增强
    private AccountService accountService;
    @Test
    public void test1(){
        accountService.transfer("aaa","bbb",200.0);
    }
}
