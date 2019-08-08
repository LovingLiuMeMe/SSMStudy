##DEMO1
使用注解完成类的IOC
1.applicationContext.xml的改变
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <!--开启注解扫描:-->
    <context:component-scan base-package="cn.lovovingliu.demo1" />

</beans>
```
3.使用@Component完成IOC  
同时`@Component`实现的类的IOC,同时Spring还提供了`@Service`,`@Controller`,`@Repository`对Service层和Controller层还有Dao层进行IOC
目前实现的功能基本一致，但Spring可在后续的版本中持续进行增强。

4.对于基本数据类型可以使用`@Value`实现属性的依赖注入  
值得注意的是@Value 不依赖于set方法(即:即使不实现set方法 也可完成属性的依赖注入)。若是实现了set方法，需要将`@Value`写在那个set方法上

5.对于数据类型为对象的使用`@AutoWired`完成自动装配
```
1.自动装配和依赖注入之间的关系
依赖注入的本质就是装配，装配是依赖注入的具体行为。也就是说这本来就是一个东西
大家可以看到用xml装配bean是一件很繁琐的事情，而且我们还要找到对应类型的bean才能装配。
装配的概念：创建应用对象之间协作关系的行为称为装配。也就是说当一个对象的属性是另一个对象时，实例化时，需要为这个对象属性进行实例化。这就是装配。

如果一个对象只通过接口来表明依赖关系，那么这种依赖就能够在对象本身毫不知情的情况下，用不同的具体实现进行切换。
但是这样会存在一个问题，在传统的依赖注入配置中，我们必须要明确要给属性装配哪一个bean的引用，一旦bean很多，就不好维护了。
基于这样的场景，spring使用注解来进行自动装配，解决这个问题。
自动装配就是开发人员不必知道具体要装配哪个bean的引用，这个识别的工作会由spring来完成。
与自动装配配合的还有“自动检测”，这 个动作会自动识别哪些类需要被配置成bean，进而来进行装配。这样我们就明白了，自动装配是为了将依赖注入“自动化”的一个简化配置的操作。

2.自动装配的类型
装配分为四种：byName, byType, constructor, autodetect。
byName就是会将与属性的名字一样的bean进行装配。
byType就是将同属性一样类型的bean进行装配。
constructor就是通过构造器来将类型与参数相同的bean进行装配。
autodetect是constructor与byType的组合，会先进行constructor，如果不成功，再进行byType。

具体选择哪一种装配方式，需要配置<bean>标签的autowire属性，如果没有配置，默认是byName类型，就是会根据属性的名字来进行自动装配。上面最常用的还是byName和byType。
自动装配时，装配的bean必须是唯一与属性进行吻合的，不能多也不能少，有且只有一个可以进行装配的bean，才能自动装配成功。
否则会抛出异常。如果要统一所有bean的自动装配类型，可以在<beans>标签中配置default-autowire属性。
当然如果配置了autowire属性，我们依然可以手动装配属性，手动装配会覆盖自动装配

Spring2.5之后提供了注解方式的自动装配。
但是要使用这些注解，需要在配置文件中配置<context:annotation-config />。
只有加上这一配置，才可以使用注解进行自动装配，默认情况下基于注解的装配是被禁用的。
常用的自动装配注解有以下几种：@Autowired，@Resource，@Inject，@Qualifier，@Named。

@AutoWired+@Qualifier("userDao") == @Resource(name="userDao")
@Autowired注解是byType类型的，因此会将接口的实现类取代接口，自动装配给控制类
```
##DEMO2 
验证了自动装配的重要性
##DEMO3
1.在使用xml配置的时候 可以使用`init-method`，`destroy-method` 在对象实例化的创建和销毁的时候调用。  
注: destroy-method 方法只有在scoped属性为singleton的时候才生效
```xml
<bean init-method="initMethod" destroy-method="destoryMethod" ></bean>
```
2.使用注解自动装配的时候  
@PostConstruct 代替 init-method
@PreDestory 代替 destroy-method

3.可以使用`@Scope`来设置作用域

##DEMO4 注解开发和xml配置开发混合使用

