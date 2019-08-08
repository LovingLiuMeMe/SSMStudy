package cn.lovovingliu.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest2 {
    @Test
    public void test1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonController personController = (PersonController)applicationContext.getBean("personController");
        System.out.println(personController.say());//我是一名:老师 我叫:唐雨----我是一名:学生 我的名字叫:刘波s
    }
}
