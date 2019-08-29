package cn.lovingliu.aop.demo3.activeproxy;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculateServiceImpl {
    @Test
    public void test1() throws Exception{
        /*
         * 参数:
         * 1.ClassLaoder 就是将UserDao加载进JVM内存的类加载器
         * 2.代理对象需要和目标对象实现相同接口 UserDao
         * */
        Class calculateDaoProxyClass = Proxy.getProxyClass(CalculateDao.class.getClassLoader(),CalculateDao.class);
        //得到有参构造器 $Proxy0(InvocationHandler h)
        Constructor constructor = calculateDaoProxyClass.getConstructor(InvocationHandler.class);
        //反射创建代理实例
        CalculateDao CalculateDaoProxyImpl = (CalculateDao) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return 10086;
            }
        });
        System.out.println(CalculateDaoProxyImpl.add(1,2));// 10086
    }
    //invocationHandler的invoke()方法中并没有写目标对象。因为一开始invocationHandler的invoke()里确实没有目标对象.需要我们手动new
    @Test
    public void test2() throws Exception{
        /*
         * 参数:
         * 1.ClassLaoder 就是将UserDao加载进JVM内存的类加载器
         * 2.代理对象需要和目标对象实现相同接口 UserDao
         * */
        Class calculateDaoProxyClass = Proxy.getProxyClass(CalculateDao.class.getClassLoader(),CalculateDao.class);
        //得到有参构造器 $Proxy0(InvocationHandler h)
        Constructor constructor = calculateDaoProxyClass.getConstructor(InvocationHandler.class);
        //反射创建代理实例
        CalculateDao CalculateDaoProxyImpl = (CalculateDao) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                CalculateDaoImpl calculateDaoImpl = new CalculateDaoImpl();
                Object result = method.invoke(calculateDaoImpl,args); // method.invoke(target,args) 调用目标对象的 指定的方法
                return result;
            }
        });
        System.out.println(CalculateDaoProxyImpl.add(1,2));// 3
    }

    //但这种写法不够优雅，属于硬编码,将获得代理对象的抽离出一个函数
    @Test
    public void test3() throws Exception{
        CalculateDao calculateDao = (CalculateDao)getProxy(new CalculateDaoImpl());
        System.out.println(calculateDao.divide(5,3));
    }


    public static Object getProxy(final Object target) throws Exception{
        Class targetCls = Proxy.getProxyClass(target.getClass().getClassLoader(),target.getClass().getInterfaces());
        Constructor constructor = targetCls.getConstructor(InvocationHandler.class);
        Object proxy = constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("当前执行的方法名称: "+method.getName()+" 方法执行之前");
                Object result = method.invoke(target,args);
                System.out.println("当前执行的方法名称: "+method.getName()+" 方法执行之后");
                return result;
            }
        });

        return proxy;
    }

    //不过实际编程中，一般不用getProxyClass()，而是使用Proxy类的另一个静态方法：Proxy.newProxyInstance()，
    //直接返回代理实例，连中间得到代理Class对象的过程都帮你隐藏
    @Test
    public void test4() throws Exception{
        CalculateDao target = new CalculateDaoImpl();
        CalculateDao calculateDao = (CalculateDao)getProxySimple(target);
        System.out.println(calculateDao.divide(5,3));
        //当前执行的方法名称: divide 方法执行之前
        //当前执行的方法名称: divide 方法执行之后
        //1.6666666666666667
    }

    public static Object getProxySimple(final Object target) throws Exception{
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("当前执行的方法名称: "+method.getName()+" 方法执行之前");
                Object result = method.invoke(target,args);
                System.out.println("当前执行的方法名称: "+method.getName()+" 方法执行之后");
                return result;
            }
        });
        return proxy;
    }
}
