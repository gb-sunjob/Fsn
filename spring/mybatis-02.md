#

## 1.首先找maven，依赖查询，选择版本，复制网址

1. pom.xml文件

* mysql jdbc驱动开发包
* mybatis开发包
* log4j 开发包,最好是使用mybatis开发包当中被建议使用的那一个版本的log4j(optional)

* 多个开发包就是用maven来进行管理，pom.xml文件点击保存，即自动下载开发包，即.jar文件

2. src/main/resource文件夹

* log4j 还有一个配置文件，需要手动进行拷贝  
    log4j配置文件(log4j.properties)
* 配置文件都放入resource资源文件夹当中，双击改配置文件  
    点击source,看到日志的打印level级别#error/warn/info/debug#
    这四个单词越往右靠的英文单词，log4j记录的日志内容就越发的详细，导致其速度也就越慢，因为日志记得多，适合开发使用；  
    越往左边靠的英文单词，其日志当中记录的内容就相对而言要少一些，速度相对来说要快一些；  
    debug属于详细级别，开发时使用  
    项目真正上线即应当使用往左边靠的英语单词了，这就看情况使用了

log4j.properties  

```
#to console#
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss}  %m%n
#to file#
log4j.appender.file=org.apache.log4j.FileAppender
log4j.appender.file.File=accp.log
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss}  %l  %m%n
#error/warn/info/debug#
log4j.rootLogger=debug, stdout, file
```

mysql jdbc,mybatis,log4j开发包  
[maven官网链接](https://mvnrepository.com/ "maven官网链接")
```
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.25</version>
</dependency>

<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
<groupId>log4j</groupId>
     <artifactId>log4j</artifactId>
     <version>1.2.17</version>
</dependency>
```

> 三拷：mysql jdbc ,mybatis,log4j(以及log4j.properties)

## 三配

1. 配pojo
2. 配置映射文件
3. 配总的映射文件

* 数据库当中有n张表，就配n个pojo,以及n个配置文件，1个总的配置文件  

## <font color = #FF0000 size = 5><b>映射文件的作用</b></font>  

1. 映射文件  
    描述pojo类与数据库当中的表之间的映射关系  
    即让pojo类与数据库当中的表建立关系，称之为orm,对象关系映射  
    o:Object，对象  
    r:relation,关系，代指关系型数据库  
    m:mapping，映射

2. DAO层的实现类，DAO层用以访问数据库  
    DAO：data access object  
    以前的DAO层用以来写数据库当中的sql语句，即专门有一个类来写DAO  
    现在映射文件的作用即相当于该DAO，即将sql语句写入到配置文件当中  
    通过映射的方式来进行访问数据库  
    mybatis将sql语句写入到配置文件当中一次来访问数据库  

## 总的配置文件  

重要内容即为写连接数据库的参数，即用户名，密码，驱动的参数等等  
即配置以前的三步骤  

```
//1.拷贝驱动(pom.xml粘贴maven当中复制过来的网址即可进行自动下载，在maven的library当中就可以看到)

//2. 加载驱动(具体的驱动类，不同的数据库就不同的驱动类)
Class.forName("com.mysql.jdbc.Driver");

//3.得到连接对象
//(通过ip,port，具体的数据库名称,username,password等来获取连接对象)
Connection connection=DriverManager.getConnection(
    "jdbc:mysql://127.0.0.1:3306/s67",
    "root",
    "123"
    );
```

## 读取文件的顺序，加载文件的顺序  

读取总的配置文件----》再进行读取配置文件----》最后读取配置文件当中导入的映射文件

# 映射文件

映射文件当中即包含pojo当中的信息  
pojo类文件需要实现Serializable序列化接口  
与pojo相关的配置文件需要配置在src/main/resource文件夹下  
可以为映射文件专门建一个包，名为com.pojo  
映射文件的文件格式，为Basic xml，属于xml文件  
取的名字尽量具有可读性，即取的名字与pojo中类的名字一样，小写首字母

xml映射文件当中需要拷贝内容,即xml映射文件的头文件  
该头文件的作用是使文件具有提示性的标签/属性/类名称等内容

```
<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE mapper  
PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN" 
"http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd">

```

再次强调映射文件的作用  

1. 描述pojo类与数据库当中的表的关系
2. 相当于DAO层的作用，写sql语句

* 如果数据库当中的表的字段名称与pojo类当中的属性名称是一致，即一样的话，可以不对该pojo类与数据库当中的表的关系，两者进行描述  
* 即，当数据库当中的表中的内容与pojo类当中的属性名称不一致的情况下，那么就需要进行对两者的关系进行描述
* ALT+/ 快捷键，提示性文字  
* 出现提示性文字的前提不仅仅在于按了ALT+/ ，还需要联网的状态，因为头文件是一个网址形式的头文件

    http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd

* 若在联网的情况下依旧没有出现提示性的标签名称属性名称等等，就将该配置文件进行关闭，再重新打开一次即可

&lt;mapper namespace="com.dao.UserDAO"&gt;&lt;/mapper&gt;  
当中namespace当中的内容是没有提示性文字的  
name 名字  
space 空间  
namespace 命名空间  

映射文件的作用相当于DAO层，即写sql语句进行对数据库的访问  
DAO 数据访问对象  data access object  

```
<mapper namespace="com.dao.StudentDAO">

//sql语句

</mapper>
```

sql语句标签  

```
//查询标签
<select id="findAll" resultType="com.pojo.Student">
select xh,xm,cs,money from student
</select>

//新增标签,增删改标签没有返回类型
//默认就是返回数值，即影响数据库记录行数的数值
//参数为学生对象，即当字段名和pojo类当中的属性名称一样的话，那么将会自动注入到要赋值的位置上
//即将对象的属性值自动放入到需要填充的字段值的位置上
<insert id="addStu" parameterType="com.pojo.Student">
insert into student(xh,xm,cs,money) values(#{xh},#{xm},#{cs},#{money})
</insert>

//修改标签，传递的参数为map
//即在调用页面，map当中存在键名"xm"/"cs"/"xh"/"money",值即为具体的参数值
<update id="updateStu" parameterType="map">
update student set xm=#{xm},cs=#{cs},money=#{money} where xh=#{xh}
</update>

//删除标签,当只有一个参数的时候，可以直接写value
<delete id="delStu" parameterType="int">
delete from student where xh=#{value}
</delete>
```

在一个映射文件当中，映射文件中的id不可以相同  
可以将映射文件理解为一个DAO实现类  
com.pojo相当于该映射文件(DAO 实现类)所处在src/main/resource文件夹下所处的包  
student.xml该文件名即相当于DAO的名称，即pojo类当中接口DAO的实现类的名称  
而一个映射文件student.xml当中的id即就类似于一个类当中的方法名称  
两个类当中的映射文件的id可以相同，但是一个映射文件当中的id不能够相同  

```
package com.dao;

import com.pojo.Student;

public interface StudentDAO{

    //查找所有的学生
    public List<Student> findAll();

    //通过主键xh来进行查找某一个学生
    public Student findById(int xh);

}
```

如果com.dao.StudentDAO 接口当中方法的返回类型为List集合或者是Set集合类型  
那么在src/main/resource文件夹下的com/pojo包下的stuent.xml映射文件当中该方法的id就应当是findAll,而返回类型则稍不同一点  
返回的应当是com.dao.StudentDAO当中List集合所泛型元素的类型，也就是com.pojo.Student类型  
即配置文件中的resultType返回类型为泛型的元素类型 resultType="完整的路径"  
如果坚持resultType当中返回的内容直接写list集合的话，则当sql语句执行完毕，查询出来的内容将会是一个com.pojo.Student类的对象  
即实现了数据库当中的记录到对象之间的自动转化  
那么如果返回类型如果是list集合的话  
而查出来的内容又是一个个的com.pojo.Student对象,就导致了数据无法进行转化的错误  

```
//映射文件相当于DAO层接口的实现类，namespace就相当于具体实现的是哪一个接口
<mapper namespace="com.dao.StudentDAO">

//具体实现com.dao.StudentDAO接口当中的findAll()方法
<select id="findAll" resultType="com.pojo.Student">
select xh,xm,cs,money from student
</select>

//具体实现com.dao.StudentDAO接口当中的findById(int xh)方法
<select id="findById" resultType="com.pojo.Student" parameterType="int" >
select xh,xm,cs,money from student where xh=#{value}
</select>

</mapper>
```


#{} 相当于问号，仅仅只有当sql语句当中只有一个问号，即只有一个参数需要被赋值的时候就是用#{value}

存在有两种表达式，一种是OGNL表达式，一种是EL表达式

## 面试题

## <font color = #FF0000 size = 5><b>OGNL表达式和EL表达式的区别</b></font>  

两者的结果虽然是一样的，但是日志，内容的解析不同  

映射文件的作用

1. 描述pojo类和数据库当中具体表的关系  
    如果字段名和属性名称相同的话，那么就不再需要对其关系进行描述  
    如果字段名和属性名称不相同的话，那么其关系是需要进行描述的
2. 写sql语句，其实际相当于DAO层的实现类

存在工具可生成sql语句，mybatisplus,可以预习一下

三配

配置好了pojo类以及映射文件之后，即需要配置总的配置文件  
同样是在src/main/resource文件夹下进行配置config.xml文件，文件的格式同样是Basic xml格式

* 配置文件同样具有头文件,用以得到提示性文字

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "mybatis-3-config.dtd">
```

>配置文件当中需要进行配置两个内容

1. 配置数据库的环境  
    使用多个数据库，那么就配置多个数据库的环境，oracle,mysql等等

    * 配置事务
    * 数据资源，区分大小写  

2. 连接池
    * 使用连接池 pooled
    * 不使用连接池 unpooled

3. 自带的数据库连接池的性能低
    * 配置四样内容username,password,url,driver
    * 双引号当中的内容少部分需要自己手写
    * mysql版本5和mysql版本6的url，driver略有区别
    * 连接数据库的字符串，可以进行连接mysql，也可以进行连接中间件mycat,mycat连接时，端口需要改变，mycat的端口号为8066，mysql的端口号是3306

4. 将映射文件导入到总的配置文件config.xml当中来

## 总结一下

* 三拷  
    mysql jdbc驱动 开发包  
    mybatis开发包  
    log4j日志的开发包以及其log4j配置文件，准确来说是属性文件，后缀为.properties  
    (日志的级别level,分有四种越往左边的日志记录的越少，速度快；越往右边的日志记录得就越多，速度慢)

* 三配  
    pojo类  
    pojo类的映射文件(作用1.描述pojo类和数据库当中表的关系；作用2.写sql语句)  
    写总的配置文件config.xml(配置数据库的环境，以及导入pojo类的映射文件)

1. 通过io包的Reader以阻塞方式读取config.xml文件
2. 通过Reader reader创建sqlSessionFactory，创建工厂是极耗资源的一件事情，所以最好只建立一个session工厂，一个数据库一个sessionFactory
3. 通过sqlSessionFactory打开得到session  
    session操作数据，每操作一次数据，就通过sqlSessionFactory创建一个session  
    sessionFactory重量级，session轻量级  
    以前学习到的session是和服务器的会话，现在得到的session是与mysql服务器的会话  
    每一个session对应着jdbc中的一个connection对象，connection对象操作数据库  
4. CRUD增删改查  
5. 提交事务  
6. 关闭session  

* 读取总的配置文件config.xml
* 创建sqlSessionFactory工厂
* 打开session
* 增删改查
* 提交事务  
    查询可以不需要有事务，可要可不要  
    事务的作用：1. 提交到数据库当中 2.回滚，撤销  
    事务特性：原子性，隔离性，一致性，持久性  
    增删改一定需要事务，如果不做事务，增删改的操作对于数据库而言将不起丝毫作用
* 关闭session
* JUnit或者是通过main函数进行测试

```
//注意不需要在配置文件前加/
//导入ibatis包中的Resource
//ibatis该包并不该为mybaits的缘故即在于需要兼容以前的Resource,所以未改名为mybaits包
Reader reader=Resource.getResourceAsReader("config.xml");

/*
创建sqlSessionFactory工厂
hibernate中的SqlSessionFactory中并没有Sql前缀，即直接为SessionFactory，ibatis(mybaits)中的是SqlSessionFactory，需要区分的一点
如果过未出现SqlSessionFactory或者是SqlSession等单词的出现，那么极有可能是开发包没有下好的缘故，查看Maven library是否下载好了；
又或者是在c盘找到其.m2目录删除开发包，进行重新下载
build创建，sqlSessionFactory一个数据库只产生一个，每做一次与数据库相关的操作即产生一个sqlSession
*/
SqlSessionFactory sqlSessionFactory=SqlSessionFactoryBuilder().build(reader);

//打开得到sqlSession对象
SqlSession sqlSession=sqlSessionFactory.openSession();
```

sqlSession查看所有学生的方法为selectList()  
sqlSession查看某一个学生的方法为selectOne()  

```
//无参数
List<Student> list=sqlSession2.selectList("com.dao.StudentDAO.findAll");

//有参数,查找学号xh为1号的学生
Student student=sqlSession3.selectOne("com.dao.StudentDAO.findById",1);

//查询可以进行提交事务，也可以不进行提交事务
sqlSession.commit();

//关闭sqlSession,
//关闭sqlSession并非是关闭了连接，而是将连接又重新放回了连接池当中
sqlSession.close();
```

表达式  

* OGNL表达式  
* EL表达式  

## 面试题

## <font color = #FF0000 size = 5><b>${} 和 #{} 的区别</b></font>

如果使用${}，将会导致有sql注入的问题，${}无问号  
动态表名可以使用${}，查询使用#{}

## <font color = #FF0000 size = 5><b>hibernate,mybatis的区别</b></font>

