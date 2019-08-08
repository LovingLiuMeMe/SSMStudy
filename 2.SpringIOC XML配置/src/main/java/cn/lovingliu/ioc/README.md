##DEMO1
### IOC 控制反转
实际上就是将传统手动的创建对象 转换成 使用Spring创建对象，将创建对象的权力交给Spring

### DI 以来注入

##DEMO2
### 三种实现Bean实例化的方式
1.使用类构造器实例化(默认使用的是无参构造函数)

2.使用静态工厂方法实例化(简单工厂模式)

3.使用实例工厂方法实例化(工厂方法模式)

```
    注意: ClassPathXmlApplicationContext是在配置文件加载完成时，就会实例化相应的对象
    所有在允许demo2的时候，demo1的构造函数也会执行。
```
## DEMO3
### Bean的作用域
作用域 | 描述
---|---
singleton | 在spring IoC容器仅存在一个Bean实例，Bean以单例方式存在，bean作用域范围的默认值。
prototype | 每次从容器中调用Bean时，都返回一个新的实例，即每次调用getBean()时，相当于执行newXxxBean()。
request | 每次HTTP请求都会创建一个新的Bean，该作用域仅适用于web的Spring WebApplicationContext环境。
session | 同一个HTTP Session共享一个Bean，不同Session使用不同的Bean。该作用域仅适用于web的Spring WebApplicationContext环境。
application | 限定一个Bean的作用域为ServletContext的生命周期。该作用域仅适用于web的Spring WebApplicationContext环境。

### Bean的生命周期
![avatar](http://img2018.cnblogs.com/blog/1528637/201811/1528637-20181105211348802-396856434.png)
```
1.instantiate bean对象实例化
2.populate properties 封装属性
3.如果Bean实现BeanNameAware执行setBeanName (获得配置文件<bean>上的name或id的值)
4.如果Bean实现BeanFactoryAwar或ApplicationContextAwar设置工厂setBeanFactory或上下文对象setApplicationContext
5.如果存在类实现BeanPostProcessor(后处理Bean),执行postProcessBeforeInitialization(重要)
6.如果Bean实现InitializingBean执行afterPropertiesSet
7.调用自定义的init-method方法
8.如果存在类实现BeanPostProcessor(处理Bean),执行postProcessAfterInitialization
9.执行业务处理
10.如果Bean实现DisposableBean执行destroy
11.调用自定义的destroy-method

对于springbean的生命周期，我们需要关注的主要有两个方法：
1.增强bean的功能可以使用后处理Bean，BeanPostProcessor
2.如果需要初始化或销毁操作，我们可以使用init-method方法和destory-method方法。
同时还需要注意一点：destory-method方法是只针对于scope=singleton的时候才有效果！
```
##DEMO4
### BeanPostProcessor 的应用实现类的增强
有用到的东西有

1.动态代理

2.反射

3.匿名内部类

##DEMO5
### DI依赖注入
```
Spring注入属性的主要是两种方式
1.构造函数注入
2.set方法注入
```
以上为基础有p名称空间的注入
```xml
    <!-- p名称空间注入 -->
    <!-- 增加xmlns -->
    <bean id="people" class="cn.lovingliu.ioc.demo5.People" p:name="小王" p:age="24" p:cat-ref="cat"/>
```
还有处理复杂依赖关系的Spel表达式
```xml
    <!-- spel方式的属性注入 处理对象关系之间复杂的依赖 -->
    <bean id="category" class="cn.lovingliu.ioc.demo5.spel.Category">
        <property name="name" value="#{'电子产品'}" />
    </bean>
    <bean id="productutil" class="cn.lovingliu.ioc.demo5.spel.ProductUtil">
    </bean>

    <bean id="product" class="cn.lovingliu.ioc.demo5.spel.Product">
        <property name="name" value="#{'ReadMi K20Pro'}" />
        <property name="price" value="#{2499-300}" /> <!--支持简单的表达式-->
        <property name="category" value="#{category}" /> <!--支持实例对象 等同于 ref-->
        <property name="discountPrice" value="#{productutil.crateDiscountPrice()}" /> <!--甚至可以支持 实力对象的方法-->
    </bean>
```
##DEMO6
最后就是复杂数据类型的注入
1.数组类型的注入
```xml

```
2.List
3.Set
4.Map
5.Properties

常用的Spring Bean的配置方式
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:util="http://www.springframework.org/schema/util"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
        http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.1.xsd">
    
    <!-- 
        配置bean
        class:bean的全类名，通过反射的方式在IOC容器中创建bean 
        id：表示容器中的bean，id唯一
    -->
    <!-- 通过setter注入配置bean的属性 -->
    <bean id="helloWorld" class="me.spring.beans.HelloWorld">
        <property name="name" value="Spring"></property>
    </bean>
    <!-- 通过构造方法配置bean的属性 -->
    <bean id="car" class="me.spring.beans.Car">
        <constructor-arg value="Audi"></constructor-arg>
        <constructor-arg value="ShangHai"></constructor-arg>
        <constructor-arg value="300000"></constructor-arg>
    </bean>

    <!-- 
        使用构造器注入的属性值可以指定参数的类型和参数的位置，以区分重载的构造器
        如果字面值包含特殊字符，可以使用<![CDATA[]]>包裹起来
        属性值也可以使用value子节点进行配置
     -->
    <bean id="car2" class="me.spring.beans.Car">
        <constructor-arg value="Baoma"></constructor-arg>
        <constructor-arg type="java.lang.String">
            <value><![CDATA[<Beijing>]]></value>
        </constructor-arg>
        <constructor-arg value="240" type="int"></constructor-arg>
    </bean>
    
    <!-- 可以使用property的ref属性建立bean之间的引用关系 -->
    <bean id="person" class="me.spring.beans.Person">
        <property name="name" value="Tom"></property>
        <property name="age" value="24"></property>
        <!-- 
        <property name="car" ref="car2"></property>
         -->
         
        <!--  
        <property name="car">
            <ref bean="car2"/>
        </property>
        -->
        
        <!-- 内部bean，不能被外部引用 -->
        <property name="car">
            <bean class="me.spring.beans.Car">
                <constructor-arg value="Ford"></constructor-arg>
                <constructor-arg value="ChangAn"></constructor-arg>
                <constructor-arg value="2354395" type="double"></constructor-arg>
            </bean>
        </property>
    </bean>
    
    <bean id="person2" class="me.spring.beans.Person">
        <constructor-arg value="Jerry"></constructor-arg>
        <constructor-arg value="25"></constructor-arg>
        <constructor-arg ref="car2"></constructor-arg>

        <!-- 测试赋值null -->
        <!--  
        <constructor-arg><null/></constructor-arg>
        -->
        <!-- 
            为级联属性赋值 
            注意:属性需要初始化后才可以为级联属性赋值，和Struts2不同
            这里必须依据person的setter和getter方法，不能为car2
        -->
        <property name="car.price" value="4546"></property>
    </bean>
    
    <!-- 测试如何配置集合属性 -->
    <bean id="person3" class="me.spring.beans.collections.Person">
        <property name="name" value="Mike"></property>
        <property name="age" value="34"></property>
        <property name="cars">
        <!-- 使用list结点为属性为list的属性赋值 -->
            <list>
                <ref bean="car"/>
                <ref bean="car2"/>
                <bean class="me.spring.beans.Car">
                    <constructor-arg value="Ford"></constructor-arg>
                    <constructor-arg value="ChangAn"></constructor-arg>
                    <constructor-arg value="2354395" type="double"></constructor-arg>
                </bean>
            </list>
        </property>
    </bean>
    <bean id="newPerson" class="me.spring.beans.collections.NewPerson">
        <property name="name" value="Rose"></property>
        <property name="age" value="23"></property>
        <property name="cars">
        <!-- 使用map结点及map的entry子节点配置Map类型的成员变量 -->
            <map>
                <entry key="AA" value-ref="car"></entry>
                <entry key="BB" value-ref="car2"></entry>
            </map>
        </property>
    </bean>
    
    <!-- 配置properties属性值 -->
    <bean id="dataSource" class="me.spring.beans.collections.DataSource">
        <property name="properties">
        <!-- 使用props和prop子节点来为properties属性值赋值 -->
            <props>
                <prop key="user">root</prop>
                <prop key="password">123456</prop>
                <prop key="jdbcURL">jdbc:mysql://localhost:3306/test</prop>
                <prop key="driverClass">com.mysql.jdbc.Driver</prop>
            </props>
        </property>
    </bean>
    
    <!-- 配置单例的集合bean，以供多个bean进行引用，需要导入util命名空间 -->
    <util:list id="cars">
        <ref bean="car"/>
        <ref bean="car2"/>
    </util:list>
    
    <bean id="person4" class="me.spring.beans.collections.Person">
        <property name="name" value="Jack"></property>
        <property name="age" value="34"></property>
        <property name="cars" ref="cars"></property>
    </bean>
    
    <!-- 通过p命名空间为bean的属性赋值，需要导入p命名空间,相对于传统的配置较为简洁 -->
    <bean id="person5" class="me.spring.beans.collections.Person" p:name="Queen" p:age="45" p:cars-ref="cars"></bean>
</beans>
```
