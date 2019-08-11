package cn.lovingliu.aop.demo3.cglibproxy;

import org.junit.Test;

public class DemoTest1 {
    @Test
    public void test1(){
        ProductDao productDao = new ProductDao();
        CglibProxy cglibProxy = new CglibProxy(productDao);
        ProductDao proxy = (ProductDao)cglibProxy.getTargetProxy();
        //productDao.save();
        proxy.save();
        proxy.delete();
        productDao.update();
        productDao.find();
    }

}
