package cn.lovingliu.aop.demo6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext3.xml")
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

        //环绕前增强-------------
        //增加学生
        //环绕后增强-------------
        //环绕前增强-------------
        //删除学生
        //环绕后增强-------------
        //环绕前增强-------------
        //删除学生
        //环绕后增强-------------
        //环绕前增强-------------
        //修改学生
        //环绕后增强-------------
        //环绕前增强-------------
        //CustomerDao 保存
        //环绕后增强-------------
        //环绕前增强-------------
        //CustomerDao 查询
        //环绕后增强-------------
        //环绕前增强-------------
        //CustomerDao 删除
        // 环绕后增强-------------
        //环绕前增强-------------
        //CustomerDao 更新
        //环绕后增强-------------
    }

}
