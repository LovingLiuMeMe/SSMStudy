package cn.lovingliu.aop.demo4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DemoTest{

    @Resource(name = "studentDao")
    private StudentDao studentDaoBefore;//增强之前的

    @Resource(name = "studentDaoProxy")
    private StudentDao studentDaoAfter;//增强之前的
    @Test
    public void test1(){
        studentDaoBefore.find();
        studentDaoBefore.delete();
        studentDaoBefore.update();
        studentDaoBefore.save();

        //删除学生
        //删除学生
        //删除学生
        //修改学生
    }
    @Test
    public void test2(){
        studentDaoAfter.find();
        studentDaoAfter.delete();
        studentDaoAfter.find();
        studentDaoAfter.update();

        //前置通知-----------------
        //删除学生
        //前置通知-----------------
        //删除学生
        //前置通知-----------------
        //删除学生
        //前置通知-----------------
        //修改学生
    }
}
