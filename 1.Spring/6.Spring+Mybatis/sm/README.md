```
Dao层是数据库层的 主要负责建立数据库连接 执行数据库命令 并返回结果

Entity主要是实体层 主要放置实体  就是一个个的类  
```
```sql
create table department(
	id int primary key auto_increment,
	name varchar(20) not null,
	address varchar(100)
);
create table staff(
	id int primary key auto_increment,
	account varchar(20) not null,
	password varchar(20) not null,
	status varchar(10) not null,
	did int,
	name varchar(20),
	sex char(2),
	id_number char(18),
	work_time datetime,
	leave_time datetime,
	born_date date,
	info varchar(200)
);
create table log(
	opr_time datetime not null, 
	type varchar(10) not null,
	operator varchar(20) not null,
	module varchar(20) not null,
	opration varchar(20) not null,
	result varchar(100) not null
);

alter table staff add constraint fk_staff_dep foreign key(did) references department(id);
```
Filter 的处理
```xml
<!-- web.xml -->
<init-param>
   <param-name>encoding</param-name>
   <param-value>UTF-8</param-value>
</init-param>
```

### mybatis resultMap 配置
resultMap标签是为了映射select查询出来结果的集合，其主要作用是将实体类中的字段与
数据库表中的字段进行关联映射。
注意：当实体类中的字段与数据库表中的字段相同时，可以将resultMap标签中的关联关系
忽略不写。当实体类中的字段与数据库表中的字段不相同时，就需要在resultMap标签中将实体类
字段与数据库字段一 一进行关联映射。

#### mapper 中的namespace
Mybatis中namespace用于绑定dao接口，dao接口的方法对应mapper中的sql语名。  
**官方文档**  
命名空间（Namespaces）在之前版本的 MyBatis 中是可选的，容易引起混淆因此是没有益处的。现在的命名空间则是必须的，目的是希望能比只是简单的使用更长的完全限定名来区分语句更进一步。
命名空间使得你所见到的接口绑定成为可能，尽管你觉得这些东西未必用得上，你还是应该遵循这里的规定以防哪天你改变了主意。出于长远考虑，使用命名空间，并将它置于合适的 Java 包命名空间之下
，你将拥有一份更加整洁的代码并提高了 MyBatis 的可用性。

#### resultMap 标签详解
```xml
<!--column不做限制，可以为任意表的字段，而property须为type 定义的pojo属性-->
<resultMap id="唯一的标识" type="映射的pojo对象">
  <id column="表的主键字段，或者可以为查询语句中的别名字段" jdbcType="字段类型" property="映射pojo对象的主键属性" />
  <result column="表的一个字段（可以为任意表的一个字段）" jdbcType="字段类型" property="映射到pojo对象的一个属性（须为type定义的pojo对象中的一个属性）"/>
  <association property="pojo的一个对象属性" javaType="pojo关联的pojo对象">
    <id column="关联pojo对象对应表的主键字段" jdbcType="字段类型" property="关联pojo对象的主席属性"/>
    <result  column="任意表的字段" jdbcType="字段类型" property="关联pojo对象的属性"/>
  </association>
  <!-- 集合中的property须为oftype定义的pojo对象的属性-->
  <collection property="pojo的集合属性" ofType="集合中的pojo对象">
    <id column="集合中pojo对象对应的表的主键字段" jdbcType="字段类型" property="集合中pojo对象的主键属性" />
    <result column="可以为任意表的字段" jdbcType="字段类型" property="集合中的pojo对象的属性" />  
  </collection>
</resultMap>
```