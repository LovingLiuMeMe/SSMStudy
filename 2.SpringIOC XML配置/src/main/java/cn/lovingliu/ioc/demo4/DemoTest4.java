package cn.lovingliu.ioc.demo4;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest4 {

    @Test
    public void test1(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("demo4.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao"); // 接口结耦

        userDao.save();
        userDao.delete();
        userDao.find();
        userDao.update();
    }

}
