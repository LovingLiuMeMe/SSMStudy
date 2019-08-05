# SSMStudy
```
javaEE分三层开发 WEB层，业务层，持久层
IOC:控制反转 将对象的创建的权利交给Spring，同时将关系对象之间的关系的维护也交给Spring

ocp原则:即open-close原则,对程序的扩展是允许的，但是对程序的源码是不允许的
```
```java
// 1.在传统的代码编写中 WEB层调用Service层方法
// 缺点:1.没有面向接口的编程  2.WEB层和Service层耦合严重
UserService us = new UserService();
us.getUserName();

// 2.预定义Service接口(面向接口编程)
// 缺点:WEB层和Service层耦合严重 同是不满足ocp原则(若还有一个接口的实现类StudentServiceImpl 则必须更改代码才可使用)
UserService us = new UserServiceImpl();
us.getUserName();

// 3.最完美的方法利用 接口+配置文件+反射实现
```
