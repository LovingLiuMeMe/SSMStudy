## 反射
```
Java 反射机制在程序运行时，对于任意一个类，都能够知道这个类的所有属性和方法；
对于任意一个对象，都能够调用它的任意一个方法和属性。
这种 动态的获取信息 以及 动态调用对象的方法 的功能称为 java 的反射机制。
```
###DEMO1
在demo1 中的test1 和 test2 中成功的获得了变量和方法等信息
###DEMO2
对象是无法访问或操作类的私有变量和方法的，但是，通过反射，我们就可以做到。

```
注意:test1()方法中
setAccessible(true)并不是将privateMethod的访问权限设置为public
而是是的test2()获得访问 privateMethod的访问权限
在test2()实现了对私有属性的变量(注意是变量)
```
能修改私有常量吗？
常量是指使用 final 修饰符修饰的成员属性，与变量的区别就在于有无 final 关键字修饰。
Java 虚拟机（JVM）在编译 `.java `文件得到 `.class `文件时，会优化我们的代码以提升效率。其中一个优化就是：JVM 在编译阶段会把引用常量的代码替换成具体的常量值，如下所示（部分代码）。
编译前的` .java `文件：
```java
//注意是 String  类型的值
private final String FINAL_VALUE = "hello";

if(FINAL_VALUE.equals("world")){
    //do something
}
```
编译后得到的 .class 文件（当然，编译后是没有注释的）：
```java
private final String FINAL_VALUE = "hello";
//替换为"hello"
if("hello".equals("world")){
    //do something
}
```
但是，并不是所有常量都会优化。经测试对于 int 、long 、boolean 以及 String 这些基本类型 JVM 会优化，而对于 Integer 、Long 、Boolean 这种包装类型，或者其他诸如 Date 、Object 类型则不会被优化。
总结来说：对于基本类型的静态常量，JVM 在编译阶段会把引用此常量的代码替换成具体的常量值。
如test3()
```java
package cn.lovingliu.aop.demo2;

public class DemoClass {
    private String msg = "Hello Demo";
    private final String  FINAL_VALUE= "常量";

    private void demoMethod(String a,Integer b){
        System.out.println(a+b);
    }
    public String getMsg(){
        return this.msg;
    }
    public String getFINAL_VALUE() {
        return FINAL_VALUE;
    }
}
```
当实际上JVM在将该.java文件编译为.class文件后
```java
public class DemoClass {
    private String msg = "Hello Demo";
    private final String  FINAL_VALUE= "常量";

    private void demoMethod(String a,Integer b){
        System.out.println(a+b);
    }
    public String getMsg(){
        return this.msg;
    }
    public String getFINAL_VALUE() {
        return "常量";
    }
}
```
###强行修改private的常量
1.在声明的时候不进行赋值操作(还可以这样操作)
```java
package cn.lovingliu.aop.demo2;

public class DemoClass {
    private String msg = "Hello Demo";
    private final String  FINAL_VALUE;
    public DemoClass(){
        FINAL_VALUE = "FINAL_VALUE";
    }

    private void demoMethod(String a,Integer b){
        System.out.println(a+b);
    }
    public String getMsg(){
        return this.msg;
    }
    public String getFINAL_VALUE() {
        return FINAL_VALUE;
    }
}
//Before Modify：FINAL_VALUE = FINAL_VALUE
//After Modify：FINAL_VALUE = 私有常量修改
//Actually ：FINAL_VALUE = 私有常量修改
```
##DEMO3
## 代理详解
1.静态代理  
    静态代理很简单，咱们自己在写代码的时候都会写到这种类似静态代理的代码。
    简单来说，就是把被代理类作为参数传给代理类的构造方法，让代理类替被代理类实现更强大的功能。
```
1.需求:需要在保存User的时候 打印一下日志 记录一下时间

```

虽然静态代理实现比较简单，但是在实际项目中我们需要为每个类都写一个代理类，需要写很多重复冗余的代码，不利于代码的解耦与扩展。但是动态代理便很好的解决了上述问题，真真正正地实现了业务逻辑代码与增强功能代码的解耦。
2.动态代理
```
在Spring源码中，用到的动态代理主要有两种，JDK动态代理以及CGLib动态代理。两者主要区别是：

JDK动态代理一般针对实现了接口的类生成代理。（下面讲AOP遇到的坑时更能理解这句话的含义）
目标对象没有实现接口，则默认会采用CGLIB代理。如果目标对象实现了接口，可以强制使用CGLIB实现代理（添加CGLIB库)其实，上面的区别阐述虽然不够完全，但足以区分二者。

相同点：

两种动态代理本质上都是：字节码组装

AOP动态代理的应用场景：

日志
事务
权限
缓存
懒加载
```
2.1 JDK动态代理
```
1.通过实现InvocationHandler接口来自定义自己的InvocationHandler;
 
2.通过Proxy.getProxyClass获得动态代理类
 
3.通过反射机制获得代理类的构造方法，方法签名为getConstructor(InvocationHandler.class)
 
4.通过构造函数获得代理对象并将自定义的InvocationHandler实例对象传为参数传入
 
5.通过代理对象调用目标方法
```
```java
//1.UserDao JDK代理是基于接口的
public class UserDao{
    public void save();
    public void delete();
    public void find();
    public void update();
}
```
```java
//2.UserDaoImpl
public class UserDaoImpl implements UserDao{
        public void save(){
            System.out.println("save方法调用了");
        }
        public void delete(){
            System.out.println("delete方法调用了");
        }
        public void find(){
            System.out.println("find方法调用了");
        }
        public void update(){
            System.out.println("update方法调用了");
        }
}
```
```java 
// 3.创建一个实现了InvocationHandler的实现类
public class MyInvocationHandler implements InvocationHandler {
    private Object target;
    public MyInvocationHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("save".equals(method.getName())){
            System.out.println("save方法日志 "+System.currentTimeMillis());
        }
        return method.invoke(target,args);
    }
}}
```
```java
//4.测试类
public class DemoTest {
    @Test
    public void test(){
        UserDao target = new UserDaoImpl();//要代理的目标对象
        MyInvocationHandler invocationHandler = new MyInvocationHandler(target);
        UserDao proxy = (UserDao)Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),invocationHandler);
        proxy.save();
        //save方法日志 1565359655124
        //保存UserDao
    }
}
```
2.2 cglib动态代理
在目标类没有实现接口的时候，Spring底层会使用cglib完成
```java
//1.ProductDao 要代理的类（没有实现接口）
package cn.lovingliu.aop.demo3.cglibproxy;

public class ProductDao {
    public void save(){
        System.out.println("保存商品成功");
    }
    public void delete(){
        System.out.println("删除商品成功");
    }
    public void find(){
        System.out.println("查询商品成功");
    }
    public void update(){
        System.out.println("修改商品成功");
    }
}
```
```java
//2.获取代理对象的类
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
```
```java
//DemoTest1
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
```
总结:  
程序中应优先对接口创建代理，便于程序解耦维护  
标记为final的方法，不能被代理，因为无法进行覆盖  
    Jdk动态代理，是针对接口生成子类，接口中的方法不能使用final修饰
    CGLIB是正对目标类产生一个子类，因此类或方法 不能使用final的  
Spring 方法的连接点，不提供属性连接点
##AOP
### AOP相关术语
```
1.Joinpoint(连接点):
所谓连接点是指那些被拦截到的点。在Spring中，这些点指的是方法，因为Spring只支持方法类型的连接点
2.Pointcut(切入点):
所谓切入点是指我们要对那些Joinpoint进行拦截和定义
3.Advice(通知/增强):
所谓通知是指拦截到Joinpoint之后所要做的事情
通知分为前置通知,后置通知,异常通知,最终通知,环绕通知(切面要完成的功能)
4.Introdution(引介):
引介是一种特殊的通知 在不修改类的代码的前提下，Introdution可以运行期为类动态的添加一些方法或Field
5.Target(目标对象):
代理的目标对象(对谁增强 设就是对象)
6.Weaving(织入):
是指把增强应用到目标对象来创建新的代理对象的过程。Spring采用的动态代理植入,而AspectJ采用的是编译期织入和类装载期织入
7.Proxy(代理):
一个类被AOP织入增强后，就产生一个结果代理类
8.Aspect(切面):
是切入点和通知(引介)的结合
```
#### Spring AOP增强类型
Spring按照通知Advice在目标类方法的连接点位置，可以分为5类  
    1.前置通知 MethodBeforeAdvice  
    2.后置通知 AfterReturningAdvice  
    3.环绕通知 MethodInterceptor
    4.异常通知 ThrowsAdvice
    5.引介通知 IntroductionIntercepter  
    
#### Spring AOP切面类型
Advisor:代表一般切面,Advice本身就是一个切面,对目标类所有方法进行拦截  
PointcutAdvisor:代表具有切点的切面，可以指定拦截目标类的哪些方法  
IntroductionAdvisor:代表引介切面，针对引介通知而使用的切面（可不掌握)  

```
ProxyFactoryBean常用可配置属性
target:代理目标对象
proxyInterfaces:代理要实现的接口
    如果多个接口可使用以下格式赋值
    <list>
        <value></value>
        ...
    </list>
proxyTargetClass:是否对类代理而不是接口，设置为true时，使用cglib代理
interceptorNames:需要织入目标的Advice
singleton:返回代理是否为单实例，默认为单例
optimize:当设置为true时，强制使用CGLIB
``` 
### DEMO4
实现的是普通的Advisor作为切面，将对目标类所有的方法进行拦截，不够灵活，在实际开发中长采用带有切点的切面

### DEMO5
常用的带有切入点的实现类  
1.DefaultPointcutAdvisor 最常用的切面类型，它可以通过任意的Pointcut和Advice组合定义切面
2.JdkRegexpMethodPointcut 构造正则表达式切点

前面的案例中,每个代理都是通过ProxyFactoryBean织入切面代理，在实际开发中，非常多的Bean每个都配置ProxyFactoryBean开发维护量巨大  
解决方法  
1.BeanNameAutoProxyCreator 根据Bean名称创建代理
2.DefaultAdvisorAutoProxyCreator 根据Advisor本身包含的信息产生代理
3.AnnotationAwareAspectJAutoProxyCreator 基于Bean中的AspectJ注解进行自动代理
### DEMO6
第一种方式:BeanNameAutoProxyCreator
但是还是会对所有的方法进行增强 这不符合实际要求

### DEMO7
第二种方式:DefaultAdvisorAutoProxyCreator
