package cn.lovingliu.ioc.demo3;

import cn.lovingliu.ioc.demo3.lifecycle.Woman;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest3 {
    @Test
    public void demo1(){
        //默认为单例模式
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        PeopleService userService1 = (PeopleService) applicationContext.getBean("peopleService");

        PeopleService userService2 = (PeopleService) applicationContext.getBean("peopleService");

        System.out.println(userService1);
        System.out.println(userService2);

        /*
        *
        * 内存地址一样 说明是同一个对象 (scoped="singleton" 和不写是一样的)
        cn.lovingliu.ioc.demo3.PeopleService@42eca56e
        cn.lovingliu.ioc.demo3.PeopleService@42eca56e
        *
        * */
    }

    @Test
    public void demo2(){
        //默认为单例模式
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService studentService1 = (StudentService) applicationContext.getBean("studentService");

        StudentService studentService2 = (StudentService) applicationContext.getBean("studentService");

        System.out.println(studentService1);
        System.out.println(studentService2);

        /*
        *
        * 内存地址不一样 说明每次产生的都是一个新的实例对象
        cn.lovingliu.ioc.demo3.StudentService@52f759d7
        cn.lovingliu.ioc.demo3.StudentService@7cbd213e
        *
        * */
    }

    @Test
    public void demo3(){
        //默认为单例模式
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Man man = (Man) applicationContext.getBean("man");

        System.out.println(man);

        applicationContext.close();
        /*
        *
        Man被实例化了------
        Man被初始化了------
        cn.lovingliu.ioc.demo3.Man@7cbd213e
        Man被销毁了------
        *
        * */
    }

    @Test
    public void demo4(){
        //默认为单例模式
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Man man = (Man) applicationContext.getBean("man");

        System.out.println(man);

        applicationContext.close();
        /*
        *
        Man被实例化了------
        Man被初始化了------
        cn.lovingliu.ioc.demo3.Man@7cbd213e
        Man被销毁了------
        *
        * */
    }

    @Test
    public void demo5(){
        //默认为单例模式
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("lifecycle.xml");
        Woman woman = (Woman) applicationContext.getBean("woman");
        woman.run();

        applicationContext.close();
        /*
        *
        第一步:被实例化
        第二步:populate properties 封装属性
        第三步:设置Bean的名称woman (其中name的就是配置文件中的id或name)
        第四步:了解工厂的信息org.springframework.context.support.ClassPathXmlApplicationContext@8807e25: startup date [Tue Aug 06 23:16:58 CST 2019]; root of context hierarchy
        第五步:初始化前执行的方法
        第六步:属性设置完成调用
        第七步:执行<bean init-method="initMethod"/> 中的方法
        第八步:初始化后执行的方法
        第九步:执行该类自定义的方法（自己的业务逻辑代码在此执行）
        第十步:执行Spring的销毁方法
        第十一步:<bean destory-method="destoryMethod"/>执行自定义的销毁方法
        *
        * */
    }


}
