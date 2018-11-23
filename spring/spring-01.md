# 第一节 框架

## 需要学习两种构架

* 第一种架构模式为集群模式
* 第二种架构模式为分布式

[集群，分布式，微服务链接](https://blog.csdn.net/qq_37788067/article/details/79250623 "集群，分布式，微服务链接")

在BS(浏览器/客户端)当中，将会存在两种服务器

[BS 链接](https://baike.baidu.com/item/BS%E6%9E%B6%E6%9E%84/1297196?fr=aladdin "BS百科链接")

俄罗斯人发明的nginx，nginx俄罗语，英文翻译为engine,中文为引擎  

即以后存在的服务器有两种，一种是nginx引擎,一种是tomcat雄猫

## <font color = #FF0000 size = 5><b>nginx服务器与tomcat服务器的区别在于何处？</b></font>

tomcat专门为我们进行解析JSP文件以及servlet文件  

比如说，做了一个JSP文件或者是一个servlet文件，那么就需要将这JSP文件或者是servlet文件放入到tomcat当中，而不能够放入到nginx引擎服务器当中  

原因即在于nginx只能够识别静态的内容，比如说html,css,js等静态资源  

[nginx,apache,tomcat 链接](https://blog.csdn.net/zzjstudent/article/details/50856546 "nginx apache tomcat")

### <font color = #FF0000 size = 5><b>动静分离</b></font>

将动态的内容放到一个服务器当中进行保存，将静态内容也放到一个服务器当中进行保存  

当然也允许将动态的内容以及静态的内容同时都放入到同一个服务器当中进行部署  

图片等静态资源也专门放到一个服务器中，称之为文件服务器  

[文件服务器 链接](https://baike.baidu.com/item/%E6%96%87%E4%BB%B6%E6%9C%8D%E5%8A%A1%E5%99%A8 "文件服务器百度百科")

这样做的好处即在于tomcat用于来快速的进行解析JSP文件或者是servlet文件，此为tomcat的特长  

解析静态内容则使用nginx引擎或者是apache来实现,解析静态内容，apache相对于nginx来说，可谓是元老级别的人物。  
nginx或者是apache解析静态内容的速度很快，性能好  

所以以后IE浏览器(不一定是IE浏览器，firefox,google等一些使用的主流浏览器)都不再直接的进行访问tomcat，而是直接访问nginx引擎

即将静态内容放到nginx引擎服务器上，动态内容放到tomcat当中  

## 部署多个tomcat

原因即在于，一个tomcat再进行优化，2000人到顶，即同时具有2000个客户端进行访问tomcat服务器，一个tomcat来进行承受2000个客户端的请求访问，压力就已经很大了，就不用说3000甚至是4000人了，tomcat就早已是承受不住的状态了  
所以此时就需要多个tomcat的存在  
那么多个tomcat，究竟访问哪一个tomcat来进行相关的操作呢  
这就需要根据一定的策略来进行处理了  
多个tomcat，即多个客户端来直接访问nginx引擎，由nginx引擎根据一定的策略来进行访问多个tomcat当中的某一个，到底进行访问哪一个tomcat，就有nginx来进行相关的配置  
比如说  

* 平均分布。  
    即第一次访问第一个tomcat，第二次访问第二个tomcat，第三次访问第三个tomcat...(轮询机制)  

* 比较服务器之间的性能，配置。  
    比如说，这台电脑的性能配置较之其它的电脑性能较为优越一些，那么就多访问这台服务器即可  

* 对服务器的部署进行一个归类。  
    比如说，来自湖南省的客户端请求访问第一个服务器，来自湖北省的请求访问第一个服务器，来自广东省的请求访问第三个服务器  

* 根据一定的策略来进行访问tomcat服务器  

## 存在的问题

1. 用户第一次访问第一个tomcat,并且在第一个tomcat服务器上进行了登陆操作，则登陆成功势必就会存用户对象到session当中，即request.getSession().setAttribute("userinfo",userinfo);  

    那么如果当用户在第二个服务器上进行了登陆的话，就会需要再进行登陆一次，即重复再进行登陆一次，这样就比较麻烦了  

    原因即在于，当前的第二台服务器当中的session与第一台服务器tomcat当中的session并不相同  

    这样导致的后果就是第一台服务器的session当中存在用户登陆过的记录状态，而其他的服务器当中并不存在用户登陆的记录状态

    也就导致了，除了用户首次登陆的服务器不用再进行登陆的操作，其他的服务器都还需要再进行登陆的操作  

    而我们真正需要的并不是这样的结果，并不是登陆多次，相反的是，只需要登陆一次，即在session有效的时间段内，在其它的服务器上是无需在进行重复的用户登陆操作

```
    UserInfo userinfo=userDAO.login(username,password);

    if(userinfo!=null){

        System.out.println("数据库当中存在该用户，查询当前用户登陆成功");

        //登陆成功的相关操作
request.getSession().setAttribute("userinfo",userinfo);
        request.getRequestDispatcher("main.jsp").forward(request,response);

    }else{

        System.out.pritln("数据库当中不存在该用户，查询当前用户登陆失败");

        //登录失败的相关操作
        response.sendRedirect("login.jsp");
    }
```

## 问题1

在1号服务器上进行登陆的操作，在2号服务器上也就不再需再进行登陆操作  
这也就是著名的session共享问题  

[session 共享 链接](https://blog.csdn.net/qq_34666857/article/details/77112985 "session 共享 链接")

[session 共享 链接2](https://blog.csdn.net/beauty5188/article/details/78424844 "session 共享 链接")

## <font color = #FF0000 size = 5><b>什么叫做session共享，有哪些的解决办法</b></font>

## 问题2

多个tomcat(即tomcat集群),倘若集群当中，多个tomcat当中存在随机一个tomcat服务器挂掉了，也就是说万一哪一个tomcat死机了，该如何处理这样的情况  
倘若不进行处理，则客户端进行请求访问的时候，则将出现不友好，报错，即状态码500/404等的页面，这就神尴尬了

这一点即需要解决的是高可用  

## <font color = #FF0000 size = 5><b>什么叫做高可用，如何进行有效的高可用操作</b></font>

[高可用性 百度百科](https://baike.baidu.com/item/%E9%AB%98%E5%8F%AF%E7%94%A8%E6%80%A7/909038?fr=aladdin "高可用性 百度百科")

做这些不管是集群，session共享，分布式等，其目的的目的都在于的是解决高可用，高性能  

### 学习规划  

* nginx引擎
* 多个tomcat如何进行安装  
* 如何做到session共享  
* 如何做到检查多个tomcat是否可用

## tomcat访问数据库

tomcat进行访问数据库mysql  
mysql也有可能会发生挂掉的情况，如何解决这种情况  
同样mysql需要解决的问题也有很多，问题纷至沓来  
比如

* 安装多个mysql，mysql也同样做集群，如何做集群  
* 如何读取多条数据，一亿条数据如何进行快速的读取等  
* mysql的高可用，该如何处理  
* 多个mysql,写哪一个myswql
* 多个mysql不肯能同时写多个mysql,多个mysql之间的数据如何进行同步  
* mysql多个之间，如果某一个mysql挂掉了，那么mysql如何进行mysql之间的切换

mysql需要学习的技术有

* 主从复制
* 读写分离
* 分库分表

### <font color = #FF0000 size = 5><b>主从复制</b></font>

只写1号mysql数据库,其余的mysql数据库服务器当中均有数据  

### <font color = #FF0000 size = 5><b>读写分离</b></font>

如果都同时只写一个mysqk数据库，那么该mysql数据库极有可能承受不住；如果都同时只读一个mysql数据库,同样该mysql数据库也是承受不住的；就更不必说同时读写了  
此时tomcat的做法即为，读一个mysql数据库，写就写另外一个mysql数据库

### <font color = #FF0000 size = 5><b>分库分表</b></font>

如果一个订单表当中的记录日积月累，那么势必该订单表当中的记录将会产生很多，但是由于订单数据不可销毁的缘故，那这些订单数据就肯定需要保存下来，但是订单数据太多了，这时候就需要用到分库分表来解决这个问题了  
即将大的内容(一张表的内容分成几个表的内容，把一个数据库当中的内容分成几个数据库的内容)  
这也就是如何来进行解决海量数据的问题  

## 存在的问题  

1. 数据库的速度性能方面，即便是做了主从复制，分库分表，读写分离这三个优化，但是数据库的性能依旧是很低的

    原因即在于数据库当中的内容是被存在硬盘当中的，所以当读取数据的时候是需要从硬盘当中取读取数据的，当写入数据的时候，也是需要写入硬盘的

## 解决问题需要用到的技术  

* 需要运用到缓存  
    即tomcat可以直接访问数据库，也可以进行访问热数据  
    即可以运用到redis,memcached等，甚至可以使用到框架(mybatis,hibernate),都具有缓存技术，但是框架自带的缓存技术仅仅只适用于单机缓存，相对于集群而言，无太大的用处，我们需要的是分布式缓存  
    所谓的缓存，即说的就是内存，将数据库当中的内容存入到内存当中，当下次再进行访问，即直接从内存当中读取数据即可，这样一来，读取数据的速度就变得非常的快了

2. redis如果挂了，该如何处理，即内存数据库挂了，该如何处理  

    同样redis也需要采用集群的模式
    不管是redis，mysql,tomcat，甚至是nginx都有可能发生挂的情况，都需要做成集群的形式，每一个至少需要两个，redis至少需要6个，nginx需要2个，双引擎

## <font color = #FF0000 size = 5><b>redis的重要作用</b></font>

1. 做缓存
2. 做session共享  
    将session存入到redis当中，tomcat从redis集群当中读取session,由于是集群，即tomcat不管从哪一个redis当中读取的内容都是一样的，所取到的session也就是一样的，那么通过session也就可以知道该用户是已经登陆成功了的，也就能够实现session共享了  

* 多个redis,到底读取哪一个redis当中的数据，这也是需要解决的问题

## tomcat连接mysql

1. 如果使用jdbc连接mysql,即需要写死IP地址以及端口号  

```
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection=ConnectionManager.getConnection(
        "jdbc:mysql://127.0.0.1:3306/s67",
        "root",
        "123"
    );
```

## 遇到的情况  

* 换了数据源  
* 某一个mysql挂掉  
* 读写分离，即需要两个mysql数据库，读是一个数据库，写又用另外一个数据库

切换数据源；以及mysql集群当中某一个挂掉，那肯定是需要替换数据库的IP地址或者是端口号的，操作起来将相当的不方便  
读写分离，就肯定需要有两个mysql,即两个IP地址或者是port端口号

解决办法  

## <font color = #FF0000 size = 5><b>使用中间件</b></font>

即tomcat不再直接访问mysql,而是访问redis或者是mycat中间件，再由mycat来进行访问mysql  
这样做的原因即在于，哪一个mysql挂掉之后，mycat就会自动地切换成另外一个mysql,mycat也存在挂掉的风险，所以mycat也需要做成集群的形式，至少需要两个mycat，高可靠  

## 捋清思绪

当IE浏览器发起了请求，我们以前做的都是直接访问tomcat,以后浏览器直接访问引擎nginx引擎，由引擎再去访问tomcat,tomcat此时就需要处理session共享的问题，tomcat要么就访问redis,要么就访问mycat中间件来进行获取数据  
由于缓存是内存，不能够存放大量的数据，所以是不能够将数据库当中的所有数据都放入到缓存当中的，即最终还是需要访问到数据库当中的数据  
tomcat针对于热点数据(经常需要使用得到的数据就需要使用得到redis)，就从redis当中取出来  
tomcat也可以直接访问mycat,不直接访问数据库  

这样做的好处就在于不再需要得知mysql的IP地址或者是port端口号，由mycat自身根据策略来访问mysql，哪一个mysql用来进行读，哪一个mysql用以写  
mycat源于淘宝网的一个牛人所编写的，后来该人离职之后，mycat就开源了  

linux这一切需要在linux上进行部署，做服务器使用linux,也可以使用虚拟机  

linux将需要学习到其基本的命令(文件的操作，目录，权限，网络的操作等)  
在linux上学会装软件等，安装jdk,mycat,mysql,nginx等等  

需要学习到的内容  
tomcat如何去访问数据库或者是mycat  

1. jdbc  
2. 框架(mybatis,hibernate[ mybatis的前身为ibatis ])

jdbc是一套接口，访问数据库的缺点即在于需要写很多的连接语句，相当的麻烦  

## <font color = #FF0000 size = 5>经典面试题</font>

### <font color = #FF0000 size = 5><b>jdbc,mybatis和hibernate的区别  </b></font>

jdbc连接不怎么封装，mybatis为半封装，hibernate为完全封装  

框架开发性能更快，到底选择哪一种框架  
如果是使用分布式集群则使用mybatis,因为hibernate为完全封装  
如果想要进行优化定制，改制就很难了  

打个比方，jdbc相当于是自己建房子，mybatis是房子建好了就只等着来装修了，hibernate就相当于精装房  

mybatis可以局部改动，中小型项目使用hibernate,大型项目使用mybatis  
操作数据库的技术，jdbc,mybatis,hibernate  

## <font color = #FF0000 size = 5><b>JPA</b></font>

[JPA](https://blog.csdn.net/h_a_h_ahahah/article/details/80058336 "JPA")

在java中，组织专门规范  
存在两种规范，一种称之为jdbc,一种称之为JPA，都是接口，制定规范  

学习：mybatis以及hibernate

一个东西越容易使用，其内部构造越复杂，封装的越好其原理就越复杂  
要用简单，原理复杂，jdbc到hibernate  
mybatis和hibernate不管是用谁，其底层实现皆由jdbc来实现  
如果是使用mybatis,hibernate访问数据库，则势必需要拷jdbc驱动  
内部需要进行调用，mybatis，hibernate实现底层访问数据库的操作  
tomcat通过mybatis或者hibernate访问数据库，使用中间人spring，更加的安全可靠  
mybatis和hibernate用来操作数据库，不用spring也可以完成访问数据库的操作

## <font color = #FF0000 size = 5><b>为什么需要spring,spring的优点在哪里</b></font>  

1. 更加简单，在mybatis以及hibernate上又进行了简化(配置等)  
2. 耦合度降低，tomcat和mybatis或者是hibernate不直接联系而是通过spring来进行通讯  

[spring优缺点](https://blog.csdn.net/xingxiupaioxue/article/details/68943036 "spring优缺点")

spring框架的学习  
<font color = #FF0000><b>spring +springMVC+ mybatis 称之为ssm </b></font>  
<font color = #FF0000><b>spring +springMVC/Struts+ hibernate 称之为ssh</b></font>  
spring/spring mvc/struts 技术之间配合使用(架构)  

[mybaits百科链接](https://baike.baidu.com/item/MyBatis/2824918?fr=aladdin "mybaits百科链接")

持久层框架  持久：数据能够永久的保存起来，能够永久地保存起来，即保存到数据库当中去，操作数据库  

操作数据库的框架有：mybatis，hibernate，支持存储过程，高级映射等  
ibatis改名为mybatis，即ibatis即指的就是mybatis  

mybatis的准备工作  

* <font color = #FF0000><b>三拷三配，六个准备</b></font>  
* 三拷，即拷贝三个文件；三配，即存在有三个配置  
* 拷驱动
    * jdbc(底层基于jdbc驱动，所以需要jdbc开发包)
    * mybatis的开发包(框架即为别人所写好的开发包)  
    * log4j(j：java;log:日志；记载mybatis运行期间的内容，log4j为非必须要拷贝的，仅用于记录分析罢了)  
    * 拷驱动，可以手动拷，也可以通过maven来直接下载开发包  
    在开发包多的情况下，建议使用maven  
    * javaEE 5，javaEE 7 使用log4j起效用；  
    javaEE 6 使用log4j不起效用
