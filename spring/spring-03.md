# Spring5.0整合mybatis

## 需要的开发包有

* 连接池
```
<dependency>
<groupId>com.zaxxer</groupId>
<artifactId>HikariCP</artifactId>
<version>3.2.0</version>
</dependency>

<!--hikaricp和c3p0两者当中选一个-->
<!-- https://mvnrepository.com/artifact/com.mchange/c3p0 -->

<dependency>
<groupId>com.mchange</groupId>
<artifactId>c3p0</artifactId>
<version>0.9.5.2</version>
</dependency>
```
* mybtis

* spring
* log4j
```
<dependency>
<groupId>org.apache.logging.log4j</groupId>
<artifactId>log4j-core</artifactId>
<version>2.11.1</version>
</dependency>
<dependency>
<groupId>org.apache.logging.log4j</groupId>
<artifactId>log4j-api</artifactId>
<version>2.11.1</version>
</dependency>
 ```
* jdbc驱动
 