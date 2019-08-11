package cn.lovingliu.aop.demo3.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Object target;
    public CglibProxy(Object target) {
        this.target = target;
    }
    public Object getTargetProxy(){
        // 1.创建核心类
        Enhancer enhancer = new Enhancer();
        // 2.设置父类
        enhancer.setSuperclass(target.getClass());
        // 3.设置回调
        enhancer.setCallback(this);
        // 4.生成代理
        Object proxy = enhancer.create();
        return proxy;
    }
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable{
        if("save".equals(method.getName())){
            System.out.println("打印save方法的专属日志");
        }
        return methodProxy.invokeSuper(proxy,args);
    }
}
