package cn.lovingliu.ioc.demo4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第五步:初始化前执行的方法");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第八步:初始化后执行的方法");
        if("userDao".equals(beanName)){
            // 对指定类进行增强
            Object proxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if("save".equals(method.getName())){
                        System.out.println("权限校验------------------------------");
                        method.invoke(bean,args);
                    }
                    return method.invoke(bean,args);
                }
            });
            return proxy;
        }
        return bean;
    }
}
