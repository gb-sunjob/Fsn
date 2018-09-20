## 回顾
* CS 桌面应用程序，客户端
* BS 网站，web应用程序，所有的资源都被放置在服务器上，要则取

### 网址的组成 
   * HTTP协议(超文本传输协议)+IP地址+端口号+工程名+文件名

### 工程名的缺省
   1. 先创建一个web工程步骤
      * 在Tomcat的安装目录下找到webapps文件夹目录
      * 在该目录下新建一个s67-jsp-02的文件夹
      * 在s67-jsp-02该文件夹下,新建一个a.html文件，并在a.html页面中进行编辑输入"hello world"等内容
      * 打开startup.bat,即开启服务
      * 在浏览器中输入网址进行运行
   2. 运行成功以后，打开Tomcat安装目录下的webapps文件夹，将刚刚新建的s67-jsp-02这个文件夹改为大写首字母的"Root"工程
   3. 重启服务。将startup.bat关闭，然后再重新打开一次
   4. 经过测试在浏览器中输入网址
   http://127.0.0.1:8080/a.html
   即可，运行之后，"Root"会自动变为下划线
   5. 注意："Root"需要在进行输入网址在网页中运行过一遍之后才会变成"_"下划线

### 端口号的缺省
   1. 先创建一个web工程步骤
      * 在Tomcat的安装目录下找到webapps文件夹目录
      * 在该目录下新建一个s67-jsp-02的文件夹
      * 在s67-jsp-02该文件夹下,新建一个a.html文件，并在a.html页面中进行编辑输入"hello world"等内容
      * 打开startup.bat,即开启服务
      * 在浏览器中输入网址进行运行
   2. 运行成功以后，打开Tomcat安装目录下的conf目录文件夹，在conf目录文件夹下找到server.xml文件，该文件可专用来改写端口号
   3. 打开conf目录文件下的server.xml文件
   4. 找到
      * &lt; Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8444" /&gt;
      * 将标签中的port一项的值"8080"改写为"80"即可,保存
      * 原因HTTP协议的默认端口号为80
   3. 重启服务。将startup.bat关闭，然后再重新打开一次
   4. 经过测试在浏览器中输入网址
   http://127.0.0.1/s67-jsp-02/a.html
   5. 如果之前将工程名省略了的话，那么网址的输入方式为
   http://127.0.0.1/a.html

### 文件名的缺省(默认打开的文件)
   1. 先创建一个web工程步骤
      * 在Tomcat的安装目录下找到webapps文件夹目录
      * 在该目录下新建一个s67-jsp-02的文件夹
      * 在s67-jsp-02该文件夹下,新建一个a.html文件，并在a.html页面中进行编辑输入"hello world"等内容
      * 打开startup.bat,即开启服务
      * 在浏览器中输入网址进行运行
   2. 运行成功以后，打开Tomcat安装目录下的conf目录文件夹，在conf目录文件夹下找到web.xml文件，该文件用来改写默认打开的文件
   3. 打开conf目录文件下的web.xml文件,并直接翻到该文件的底部
   4. 找到

      &lt; welcome-file-list &gt;

      &lt; welcome-file &gt;index.html &lt;/welcome-file &gt;

      &lt; welcome-file &gt;index.htm &lt;/welcome-file &gt;

      &lt; welcome-file &gt;index.jsp &lt;/welcome-file &gt;

      &lt; /welcome-file-list &gt;

   4. 在 &lt; welcome-file &gt;index.jsp &lt;/welcome-file &gt; 的后面在输入&lt; welcome-file &gt;a.html &lt;/welcome-file &gt;即可，进行保存
   3. 重启服务。将startup.bat关闭，然后再重新打开一次
   4. 经过测试在浏览器中输入网址
   http://127.0.0.1:8080/s67-jsp-02
   5. 如果之前将工程名缺省了的话，那么网址的输入方式为
   http://127.0.0.1:8080
   6. 如果之前将端口号和工程名一并缺省了的话，那么网址的输入方式为
   http://127.0.0.1
   7. 以上打开的都将会是a.html页面

# JSP
 
 * 通过jsp页面从数据库中进行查找数据，并显示在浏览器当中
 * java服务器页面(java server page,JSP)以扩展名/后缀名.jsp来进行保存
 * 能够有效地控制动态内容的生成
 * 在java server page里面使用java编程语言，和类库
 * HTML用于表示页面，而java代码用于访问动态内容
 * java-jsp-服务器(jsp使用java访问动态内容)
 
 ## jsp的页面元素
 * 静态内容 HTML静态文本，CSS,图片，JavaScript
 * 指令 以"<%@"开始，以"%>"结束
    * 比如 <%@ page contentType="text/html" %>
    * 一般将指令放置在文档的最上面
    * 存在有三大指令
       * page 页面指令
          * 用于<%@ page contentType="text/html" charset="utf-8" %>表示文档类型以及编码的方式，需要注意的是文档编码类型需要和浏览器的类型保持一致，否则将会导致乱码
       * taglib 标签库
       * include 包含
 * 表达式 <%=java表达式%>
 * 小脚本 <%java代码%>
    * 小脚本当中不能放置方法，否则将会导致内部服务器错误，即代码错误，状态码为500
 * 声明 <%!函数或者是方法%>
 * 动作 以"&lt;jsp:动作名"开始，以"&lt;jsp:动作名&gt;"结束
    * 比如 &lt;jsp:include page="Filename"/&gt;
 * 注释 
    * &lt;!--这是注释，但是在客户端的console可以查看得到--&gt;
    * &lt;%--这是注释，在客户端的console无法查看得到--&gt;

## JSP的执行过程



