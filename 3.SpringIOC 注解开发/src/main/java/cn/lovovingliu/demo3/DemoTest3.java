package cn.lovovingliu.demo3;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest3 {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext applicationContext  = new ClassPathXmlApplicationContext("applicationContext.xml");
        Bean1 bean1 = (Bean1) applicationContext.getBean("bean1");
        bean1.run();
        applicationContext.close();

        /*
        *
        实例化--------
        实例化前--------
        实例化完成并调用方法--------
        销毁后--------
        *
        * */
    }

    @Test
    public void test2(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Bean2 bean_2_1 = (Bean2) applicationContext.getBean("bean2");
        Bean2 bean_2_2 = (Bean2) applicationContext.getBean("bean2");
        System.out.println(bean_2_1);
        System.out.println(bean_2_2);
        /*
        *
        1.默认情况下为单例
        实例化--------
        实例化前--------
        cn.lovovingliu.demo3.Bean2@757277dc
        cn.lovovingliu.demo3.Bean2@757277dc
        *
        2.@Scope("prototype")
        实例化--------
        实例化前--------
        cn.lovovingliu.demo3.Bean2@37271612
        cn.lovovingliu.demo3.Bean2@ed7f8b4
        * */
    }

}
