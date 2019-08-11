package cn.lovingliu.aop.demo7;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext4.xml")
public class DemoTest {
    @Resource(name = "studentDao")
    private StudentDao studentDao;
    @Resource(name = "customerDao")
    private CustomerDao customerDao;

    @Test
    public void test1(){
        studentDao.save();
        studentDao.find();
        studentDao.delete();
        studentDao.update();

        customerDao.save();
        customerDao.find();
        customerDao.delete();
        customerDao.update();
    }

}
