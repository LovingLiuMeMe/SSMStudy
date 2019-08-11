package cn.lovingliu.aop.demo3.staticproxy;

import org.junit.Test;

public class UserServiceImpl {
    @Test
    public void test1(){
        UserDao userDao = new UserDaoImpl();
        userDao.save();//保存UserDao
    }

    @Test
    /*
    * 静态代理
    * */
    public void test2(){
        UserDao userDao = new UserDaoImpl();
        StaticProxy staticProxy = new StaticProxy(userDao);
        staticProxy.save();
        //保存前的时间戳:1565331870796
        //保存UserDao
        //保存后的时间戳:1565331870797
    }
}
