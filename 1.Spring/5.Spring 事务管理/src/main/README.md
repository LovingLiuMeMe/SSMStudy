##Spring事务
###1.什么是事务
事务是正确执行一系列的操作（或动作），使得数据库从一种状态转换 成另一种状态，且保证操作全部成功，或者全部失败。  
###2.事务原则是什么
事务必须服从ISO/IEC所制定的ACID原则。  
ACID原则的具体内涵如下： 事务简介  
原子性（ Atomicity ）：  
即不可分割性，事务要么全部被执行，要么就全部不被执行。

一致性（ Consistency ）：  
事务的执行使得数据库从一种正确状态转换成另一种正确状态。

隔离性（ Isolation ）：  
在事务正确提交之前，它可能的结果不应显示给任何其他事务。

持久性（ Durability ）：  
事务正确提交后，其结果将永久保存在数据库中。

###3.java事务
**Java事务的产生**  
程序操作数据库的需要。以Java编写的程序或系统，实现ACID的操作。  
**Java事务实现**  
通过JDBC相应方法间接来实现对数据库的增、删、改、查，把事 务转移到Java程序代码中进行控制；  
确保事务—要么全部执行成功，要么撤销不执行。  
总结：Java事务机制和原理就是操作确保数据库操作的ACID特性。

####4.事务读取类型说明
1.脏读  
事务还没有提交，就开始读取数据。读到的数据肯定不是最新的
2.不可重复读  
两次读取的内容不一致
```
事务1会对 一个数据多次读取 如: age:18 。当事务1第一次读取出来的数据，为{age:18}。结下了事务2对数据进行了修改
改为{age:20}，此时事务1再一次读取时数据为{age:20}。
```
3.幻读
事务不是独立执行时所发生的一种非预期现象。
```
原表内容{A:男,B:男,C:男} 事务1 对表进行修改 将所有性别改为女。这时事务1刚结束,事务2又再次向表中插入一体哦啊数据{D:女}
再来一个事务3 对该表进行读取时 数据为{A:女,B:女,C:女,D:男}。给人一种错觉,就是明明已经全部修改了啊,怎么还留了一个。
```

##Spring 事务
Spring中的事务管理：Spring提供了一组接口进行事务的管理。

Spring提供事务管理的3个接口：  
【1】PlatformTransactionManager：事务管理器，用来管理事务的接口，定义了事务的提交、回滚等方法。  
【2】TransactionDefinition：事务定义信息（隔离级别、传播行为、是否超时、是否只读）  
 ```
 1.事务的隔离级别
     ISOLATION_DEFAULT：使用后端数据库默认的隔离界别，MySQL默认采用的REPEATABLE_READ隔离级别，Oracle默认采用的READ_COMMITTED隔离级别。
     ISOLATION_READ_UNCOMMITTED：最低的隔离级别，允许读取，允许读取尚未提交的的数据变更，可能会导致脏读、幻读或不可重复读。
     ISOLATION_READ_COMMITTED：允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生。
     ISOLATION_REPEATABLE_READ：对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生。
     ISOLATION_SERIALIZABLE：最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就说，该级别可以阻止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。
 2.事务的传播行为
     当事务方法被另一个事务方法调用时,必须指定事务应该如何传播
     
     PROPAGATION_REQUIRED（XML文件中为REQUIRED)  
     表示当前方法必须在一个具有事务的上下文中运行，如有调用端有事务在进行，那么被调用端将在该事务中运行，否则的话重新开启一个事务。（如果被调用端发生异常，那么调用端和被调用端事务都将回滚）
     
     PROPAGATION_SUPPORTS(XML文件中为SUPPORTS）  
     表示当前方法不必需要具有一个事务上下文，但是如果有一个事务的话，它也可以在这个事务中运行
     
     PROPAGATION_MANDATORY(XML文件中为MANDATORY）  
     表示当前方法必须在一个事务中运行，如果没有事务，将抛出异常
     
     PROPAGATION_NESTED(XML文件中为NESTED)  
     表示如果当前方法正有一个事务在运行中，则该方法应该运行在一个嵌套事务中，被嵌套的事务可以独立于被封装的事务中进行提交或者回滚。如果封装事务存在，并且外层事务抛出异常回滚，那么内层事务必须回滚，反之，内层事务并不影响外层事务。如果封装事务不存在，则同PROPAGATION_REQUIRED的一样
     
     PROPAGATION_NEVER（XML文件中为NEVER)  
     表示当方法务不应该在一个事务中运行，如果存在一个事务，则抛出异常
     
     PROPAGATION_REQUIRES_NEW(XML文件中为REQUIRES_NEW）  
     表示当前方法必须运行在它自己的事务中。一个新的事务将启动，而且如果有一个现有的事务在运行的话，则这个方法将在运行期被挂起，直到新的事务提交或者回滚才恢复执行。
     
     PROPAGATION_NOT_SUPPORTED（XML文件中为NOT_SUPPORTED）  
     表示该方法不应该在一个事务中运行。如果有一个事务正在运行，他将在运行期被挂起，直到这个事务提交或者回滚才恢复执行
     
 3.是否只读
    利用数据库事务的“只读”属性，进行特定优化处理 。  
    注意：  
    事务的是否“只读”属性，不同的数据库厂商支持不同。  
    通常而言：只读属性的应用要参考厂商的具体支持说明，比如: 
       Oracle的“readOnly”不起作用，不影响其增删改查；
       Mysql的“readOnly”为true，只能查，增删改则出异常。
 4.是否超时
    事务超时就是事务的一个定时器，在特定时间内事务如果没有执行完毕， 那么就会自动回滚，而不是一直等待其结束。
    设计事务时注意点：
     
    为了使应用程序很好地运行，事务不能运行太长的时间。因为事务可能涉 及对后端数据库的锁定，所以长时间的事务会不必要的占用数据库资源
 ```
【3】TransactionStatus：事务具体运行状态（事务是否提交，事务是否有保存点，事务是否是新事物等状态）。
```
提供了获取事务状态的方法（例如：hasSavepoint()事务是否有保存点，isCompleted()事务是否已经完成，isNewTransaction(）是否是新的事务)。
```  
Sring事务管理时,这三个接口是有联系的，Spring首先会根据事务定义信息TransactionDefinition获取信息,然后由事务管理器PlatformTransactionManager进行管理，在事务管理过程中，会产生一个事务的状态，这个状态就保存在事务具体运行状态TransactionStatus中了。  


#### DEMO1 
使用原始的TransactionProxyFactoryBean 来实现事务。
只能对单个的方法实现一个事务管理  
```
<property name="transactionAttributes">
    <props>
        <!-- prop的格式:
            PROPAGATION: 事务的传播行为.
            ISOLATION: 事务的隔离级别.
            readOnly: 是否只读
            Exception: 发生那些异常事务不会滚
        -->
        <prop key="transfer">PROPAGATION_REQUIRED,ISOLATION_REPEATABLE_READ,readOnly,-java.lang.NullPointerException,+java.lang.ArithmeticException</prop>
        <!-- 解释以下:
            事务的传播行为是:PROPAGATION_REQUIRED
            事务的隔离级别:ISOLATION_REPEATABLE_READ
            readOnly:只读（若尝试进行增删改操作 会报错）
            -java.lang.NullPointerException:当发生java.lang.NullPointerException异常时 回滚事务
            +java.lang.ArithmeticException:当发生java.lang.ArithmeticException异常时 不回滚事务
        -->
    </props>
</property>
``` 
###DEMO2 基于AspectJ的XML方式的配置
1.<aop:advisor>配置
2.<aop:aspect> 配置
```java
package x.y;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;
import org.springframework.core.Ordered;

public class SimpleProfiler implements Ordered {

    private int order;

    // allows us to control the ordering of advice
    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    // this method is the around advice
    public Object profile(ProceedingJoinPoint call) throws Throwable {
        Object returnValue;
        StopWatch clock = new StopWatch(getClass().getName());
        try {
            clock.start(call.toShortString());
            returnValue = call.proceed();
        } finally {
            clock.stop();
            System.out.println(clock.prettyPrint());
        }
        return returnValue;
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/tx
        https://www.springframework.org/schema/tx/spring-tx.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="fooService" class="x.y.service.DefaultFooService"/>

    <!-- this is the aspect -->
    <bean id="profiler" class="x.y.SimpleProfiler">
        <!-- execute before the transactional advice (hence the lower order number) -->
        <property name="order" value="1"/>
    </bean>

    <tx:annotation-driven transaction-manager="txManager" order="200"/>

    <aop:config>
            <!-- this advice will execute around the transactional advice -->
            <aop:aspect id="profilingAspect" ref="profiler">
                <aop:pointcut id="serviceMethodWithReturnValue"
                        expression="execution(!void x.y..*Service.*(..))"/>
                <aop:around method="profile" pointcut-ref="serviceMethodWithReturnValue"/>
            </aop:aspect>
    </aop:config>

    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"/>
        <property name="url" value="jdbc:oracle:thin:@rj-t42:1521:elvis"/>
        <property name="username" value="scott"/>
        <property name="password" value="tiger"/>
    </bean>

    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

</beans>
```
###DEMO3 基于注解的开发
```java
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    public void increasePriceOfAllProductsInCategory(final String category) {
        List productsToChange = this.productDao.loadProductsByCategory(category);
        // ...
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return this.productDao.findAllProducts();
    }

}
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/tx
        https://www.springframework.org/schema/tx/spring-tx.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- SessionFactory, DataSource, etc. omitted -->

    <bean id="transactionManager"
            class="org.springframework.orm.hibernate5.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory"/>
    </bean>

    <tx:annotation-driven/>

    <bean id="myProductService" class="product.SimpleProductService">
        <property name="productDao" ref="myProductDao"/>
    </bean>

</beans>
```
## 总结
课程总结：介绍了四种Spring做事务控制的方法
 手动编写代码做事务管理（很少使用）
 为每个进行事务管理的类，配置一个TransactionProxyFactoryBean进行增强（很少使用）
 基于AspectJ的XML方式（经常使用）， 一旦配置好之后，类上不需要添加任何东西
```xml
<!-- 配置事务管理器 -->
<bean id="transactionManager"
    class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 配置事务的通知（事务的增强） -->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
    <!-- 
    propagation 事务传播行为
    isolation : 事务的隔离级别
    read-only 只读
    rollback-for 发生哪些异常回滚
    no-rollback-for 发生哪些异常不回滚
    timeout 过期信息
    -->
    <tx:method name="transfer" propagation="REQUIRED" isolation="DEFAULT" 
        read-only="false" rollback-for="" timeout="" no-rollback-for=""/>
    </tx:attributes>
</tx:advice>
<!-- 配置切面 -->
<aop:config>
    <!-- 配置切入点 -->
    <aop:pointcut expression="execution(* cn.muke.spring.demo3.AccountService+.*(..))" 
        id="pointcut1"/>
    <!-- 配置切面 -->
    <aop:advisor advice-ref="txAdvice" pointcut-ref="pointcut1"/>
</aop:config>
```
4. 基于注解的方式（经常使用）
配置文件中开启注解驱动
```xml
<!-- 配置事务管理器 -->
<bean id="transactionManager" 
        class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 开启注解事务 -->
<tx:annotation-driven transaction-manager="transactionManager"/>
```
代码中直接使用@Transactional注解
```java
/**
 * @Transactional 注解中的属性
 * propagation 事务的传播行为
 * isolation 事务的隔离级别
 * 
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
public class AccountServiceImpl implements AccountService{
    //其他省略
}
```
但是 @Transactional 不光可以用在类上，也可以使用在方法上


