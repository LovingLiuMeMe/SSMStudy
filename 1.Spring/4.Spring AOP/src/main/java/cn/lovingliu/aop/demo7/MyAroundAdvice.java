package cn.lovingliu.aop.demo7;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("环绕前增强-------------");
        Object obj = invocation.proceed();// 可阻止目标方法不执行
        System.out.println("环绕后增强-------------");
        return obj;
    }
}
