package cn.lovingliu.aop.demo5;

import cn.lovingliu.aop.demo4.StudentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class DemoTest {

    @Resource(name = "studentDao")
    private StudentDao studentDaoBefore;

    @Resource(name = "studentDaoProxy")
    private StudentDao studentDaoAfter;


    @Test
    public void test1(){
        studentDaoBefore.save();
        studentDaoBefore.delete();
        studentDaoBefore.find();
        studentDaoBefore.update();
        //增加学生
        //删除学生
        //删除学生
        //修改学生
    }

    @Test
    public void test2(){
        studentDaoAfter.save();
        studentDaoAfter.delete();
        studentDaoAfter.find();
        studentDaoAfter.update();

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
    }

}
