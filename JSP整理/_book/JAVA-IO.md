# 流的概念

 在Java语言中，输入和输出是完全基于"流"的概念。通过流，程序可以从各种输入设备读入数据，向各种输出设备输出数据

流(Stream)的概念源于Unix中管道(Pipe)的概念。

在Unix中，管道是一条不间断地字节流。用来实现程序或者是进程间地通信，或者是读写外围设备，外部文件等。

"流"是用于计算机中进行数据传输的机制，就像水管里的水流，在水管的一端供水，而在水管的另一端看到的是一股连续不断的水流。一个流，必须有远端和目的端，它们可以是计算机内存的某些区域，也可以是磁盘文件，还可以是键盘，显示器等物理设备，甚至可以是Internet上的某个URL地址。数据有两个传输方向，实现数据从外部源到程序的流成为输入流。通过输入流可以把外部的数据传送到程序中来处理。实现数据从程序到外部源的流称作输出流。通过输出流，我们可以把程序处理的结果数据传送到目标设备。

## Java流类的层次结构

Java中的流类都处于Java.io包或者是Java.nio包中

Java中流的分类如下：

* 按数据传送的方向分，可以分为输入流和输出流
* 按数据处理传输的单位分，可以分为字节流和字符流

分别由四个抽象类来表示:InputStream(字节输入流)，OutputStream(字节输出流)，Writer(字符输出流)，Reader(字符输入流)。这四个类的基类都是Object类，Java中其他多种多样变化的流类，均是由他们派生出来的

## 流类的派生结构

* InputStream
   * FileInputStream
   * PipedInputStream
   * StringBufferInputStream
   * FilterInputStream
      * LineNumberInputStream
      * DataInputStream
      * BufferedInputStream
      * PushBackInputStream
   * ByteArrayInputStream
   * SequenceInputStream
   * ObjectInputStream
****************************************
* OutputStream
   * FileOutputStream
   * PipedOutputStream
   * FilterOutputStream
      * PrintStream
      * DataOutputStream
      * BufferedOutputStream
   * ByteArrayOutputStream
   * ObjectOutInputStream
****************************************************
* Reader
   * BufferedReader
      * LineNumberInputStream
   * CharArrayReader
   * InputStreamReader
      * FileReader
   * FilterReader
      * PushbackReader
   * PipedReader
   * StringReader
********************************************************
* Writer
   * BufferedWriter
   * CharArrayWriter
   * OutputStreamWriter
      * FileWriter
   * FilterWriter
   * PipedWriter
   * StringWriter
   * printWriter


其中的InputStream和OutputStream，在早期的Java版本中就已經存在了，它们是基于字节流的，所以有时候也把InputStream和OutputStream直接称为输入流和输出流，而基于字符流的Reader和Writer时候来补充的，直接使用它们的英文类名。以上的层次图是Java类库中基本的层次体系。

## 预定义流

Java程序在运行时会自动导入一个java.lang包，这个包定义了一个名为System的类，该类封装了运行环境的多个方面。例如，使用它的某些方法，能获得当前时间(System.currentTimeMillis)和与系统相关的不同属性。

System同时包含3个预定义的流变量：in,out和err。这些成员在System中被定义为public和static类型，即意味着它们可以不引用特定的System对象，而直接被用于程序的特定地方。

System.in对应键盘，表示"标准输入流"。它时InputStream类型的，使用System.in可以读取从键盘上输入的数据

System.out对应显示器，表示"标准输出流"。它是PrintStream类型的，PrintStream是OutputStream的一个子类，使用System.out可以将数据输出到显示器上。键盘可以被当作一个特殊的输入文件，显示器可以被当作一个特殊的输出文件。

System.err表示"标准"错误输出流。此流已打开并准备接收输出数据

通常，此流对应于显示器输出或者是由主机环境或者是用户指定的另一个输出目标。按照惯例，此输出流用于显示错误消息，或者显示那些即使用户输出流(变量out的值)已经重定向到通常不被连续监视的某一文件或者是其他没有标，也应该立刻引起用户注意的其他信息

## 标准输入输出

通过采用标准输入System.in，分别从键盘输入字符串类型，整形和双精度类型的数据，并通过标准输出System.out在控制台输出3中数据类型的结果

import java.io.*;

public class StandardIO{

    public static void main(String[] args){
        //io操作必须捕获io异常
        try{
            //先使用System.in构造InputStreamReader,再构造BufferedReader
            BufferedReader stdin=new BufferedReader(new InputStream(System.in));
            //读取并输出字符串
            System.out.println("Enter input string");
            System.out.println(stdin.readLine());
            //读取并输出整型数据
            System.out.println("Enter input an integer");

            //将字符串解析为带符号的十进制整数
            int num1=Integer.parseInt(stdin.readLine());
            System.out.pritln(num1);
            //读取并且输出double数据
            System.out.println("Enter input an double");
            //将字符串解析为带符号的double数据
            double num2=Double.parseDouble(stdin.readLine());
            System.out.println(num2);
        }
        catch(IOException e){
            System.out.println("IOException");
        }
    }

}

## Java流相关类

Java的流式输入/输出建立在4个抽象类的基础上：InputStream,OutputStream,Reader和Writer.

InputStream，OutputStream被设计为字节流类，而Reader和Writer被设计为字符流类。

一般情况下，处理字节或者是二进制对象时使用字节流类，而处理字符或者是字符串时应该使用字符流类

### 字节流

1. InputStream(输入流)

在Java中,用InputStream类来描述所有字节输入流的抽象概念。InputStream类时所有字节输入流的直接或者是间接的父类，定义了所有Java字节输入流都具有的共同特性。它是一个抽象类，所以不能通过"new InputStream()"方式来实例化对象。InputStream提供了一系列和读取数据有关的方法

* int available() 返回此输入流  下一个方法  调用 的可以不受阻塞地从此输入流读取(或者跳过)的估计字节数
* void close() 关闭此输入流并且释放与该流关联的所有系统资源
* void mark(int readlimit) 在此输入流中标记当前的位置。readlimit参数告知此输入流在标记位置失效之前允许读取的字节数
* boolean markSupported() 测试此输入流是否支持mark和reset方法
* abstract int read() 从输入流中读取数据的下一个字节
* int read(byte[] b) 从输入流中读取一定数量的字节，并将其存储在缓冲区数组b中
* int read(byte[] b,int off,int len) 将输入流中最多len个数据字节读入byte数组
* void reset() 将此流重新定位到最后一个对此输入流调用mark方法时的位置
* long skip(long n) 跳过和丢弃此输入流中数据的n个字节

2. OutputStream(输出流)

在Java中，用OutputStream类来描述所有字节输出流的抽象概念。OutputStream类是所有字节输出流的父类。定义了所有Java字节输出流都具有的基本操作。它是一个抽象类，所以不能被实例化。OutputStream提供了一系列和写入数据有关的方法

* void close() 关闭此输出流并释放与此流有关的所有系统资源
* void flush() 刷新此输出流并强制写出所有缓冲的输出字节
* void write(byte[] b) 将b.length个字节从制定的byte数组写入输出流
* void write(byte[] b,int off,int len) 将指定byte数组中从偏移量off开始的len个字节写入此输出流
* abstract void write(int b) 将指定的字节写入此输出流。write的常规协定是：向输出流写入一个字节。要写入的字节是参数b的八个低位。b的24个高位将被忽略

3. ByteArrayInputStream(字节数组输入流)

ByteArrayInputStream类可以将字节数组转化为输入流。利用它可以从字节数组中以流的形式读取byte型数据。在创建ByteArrayInputStream型实例时，程序内要提供一个byte类型数组，作为输入流的数据源。

它由两个构造函数：

* ByteArrayInputStream(byte buf):使用buf作为参数指出输入流的源
* ByteArrayInputStream(byte buf,int offset,int length):使用buf作为输入流的源，参数offset指定从数组中开始读数据的起始下标位置，length指定从数组中读取的字节个数

## ByteArrayInputStream类的应用

给定一个字节数组，先在控制台输出字节数组的内容，然后通过字节数组输入流，把这个字节数组作为数据源，再以流的形式读取其中的内容并显示出来，比较两次输出的内容

import java.io.*;
public class ByteArrayIn{
    public static void main(String[] args){
        int b;
        byte[] buff=new byte[]{1,2,3};//声明字节数组并初始化
        for(int i=0;i< buff.length;i++){
            System.out.println("******************");
            //声明字节数组输入流，并指出字节数组buff为输入流的源
            ByteArrayInputStream bin=new ByteArrayInputStream(buff);
            while((b=bin.read()) !=-1){
                //从流中每次读取一个字节，循环输出流中所有内容
                System.out.println(b);
            }
        }
    }
}

4. ByteArratOutStream(字节数组输出流)

ByteArrayOutputStream类实现向内存中的字节数组中写入数据，它把字节数组类型转换为输出流类型，使程序能够对字节数组进行写操作。

在创建它的实例时，程序中应创建一个byte类型的数组，然后利用ByteArrayOutputStream的实例方法获取内存中字节数组的数据

## ByteArrayOutputStream类的应用

创建一个字节数组输出流的实例，把一个字符串转化为字节数组，作为字节数组输出流的数据源，然后把输出流中的内容转换成一个字节数组并且显示出来。

import java.io.*;

public class ByteArrayOut{

    public static void main(String[] args)throws IOException{
        byte[] buff=null;
        String msg="请不要说英语,ok！";
        //声明一个字节数组输出流对象
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try{
            //把字符串转换为字节数组，并送入输入流
            buff=out.toByteArray();
            out.close();
        }catch(IOException e){
            System.out.println(new String(buff,"UTF-8"));
        }
    }
}

5. FileInputStream(文件输入流)

FileInputStream是InputStream的子类，用来从指定的文件中读取数据，FileInputStream操作的单位也是字节，所以它不但可以读写文本文件，也可以读写图片，声音，影像文件，这一特点非常有用，可以把各种文件变成流，然后在网络上进行传输

通过它的构造函数来指定文件路径和文件名，通过使用从InputStream的继承的read方法，可以读取文件的一个字节，几个字节或者是整个文件。创建FileInputStream实例对象时，指定的文件应当时存在和可读的，否则在进行读取操作的时候将会抛出异常

FileInputStream类的构造方法有如下两种

* FileInputStream(String filename):用文件名作为参数创建文件输入流对象，这里的filename包含文件地址信息
* FileInputStream(File f): 用一个File对象作为参数来指出流的源端

## FileInputStream类的应用

读取指定位置的文件内容，并且统计从文件中读取到的字节数

import java.io.*;

public class TestFileInputStream{

    public static void main(String[] args){

        FileInputStream files=null;
        int b=0;
        try{
            files=new FileInputStream("d:/test.txt");
        }catch(FileNotFoundException e){
            System.out.println("系统文件不存在");
            System.exit(-1);
        }
        try{
            long num=0;
            while((b=files.read())!=-1){
                System.out.println(char(b));
                num++;
            }
            files.clse();
            System.out.println("读取成功");
            System.out.println("共读取了"+num+"个字节");
        }catch(IOException e){
            System.out.println("此文件读取出错");
            System.exit(-1);
        }
    }
}

6. FileOutputStream(文件输出流)

FileOutputStream是OutputStream的直接子类，可以用来实现向文件中写入数据，写入数据的基本单位是字节。在实例化FileOutputStream对象时，如果指定的文件不存在，则会自动创建一个空的文件，如果指定的文件名称已经存在，则原文件将会被覆盖，如果文件是不可写的，则会抛出FileNotFountException异常。FileOutputStream类的构造方法有如下三种

* FileOutputStream(File f) 用一个File对象作为参数来指出流的目的地
* FileOutputStream(String filename) 用文件名作为参数创建文件输出流对象，这里的filename包含文件路径信息
* FileOutputStream(String filename,boolean apppend) 其中append参数指定写入的方式，如果设置为true,则文件不存在就会新建一个文件；如果文件存在，写入数据附加至文件末端

## FileOutputStream类的应用

通过输入输出流操作，把"face.gif"文件复制一份，同时将其保存在同一个文件夹下，新文件名为"newFace.gif"

import java.io.*;

public class FileOut{

    public static void main(String[] args){
        //创建流文件读入与输出类
        FileInputStream inStream=new FileInputStream("face.gif");
        FileOutputStream outputStream=new FileOutputStream("newFace.gif");
        //通过avaliable方法获取得到流的最大字节数
        byte[] inOutb=bew byte[inStream.avaliable()];
        inStream.read(inOutb);//读入流数据，保存在byte数组
        outStream.write(inOutb);//写出流数据，保存在文件newFace.gif中
        inStream.close();
        outStream.close();
    }
}

7. DataInputStream(数据输入流)

过滤流在读/写数据的同时，可以对数据进行处理，它提供了同步机制，使得某一时刻只有一个线程可以访问一个I/O流，以防止多个线程同时对一个I/O流进行操作所带来的意想不到的结果。类FilterInputStream和FileterOutputStream是所有过滤输入流和输出流的父类。因为InputStream类声明的数据读取方法在很多情况下使用起来比较复杂，所以FilterInputStream还扩展了输入流的读取功能

DataInputStream类是过滤输入流(FilterInputStream)的子类，DataInputStream不仅可以读取数据流，还可以通过与机器无关的方式从基本输入流中读取Java语言中各种各样的基本数据类型，例如int,float,String等。因为这些类型的数据在文件中与在内存中的表示方式一样，因此无需进行编码转换

* int readInt() 从输入流中读取int类型数据
* byte readByte() 从输入流中读取byte类型数据
* char readChar() 从输入流中读取char类型的数据
* long readLong() 从输入流中读取long类型的数据
* double readDouble() 从输入流中读取double类型的数据
* float readFloat() 从输入流中读取float类型的数据
* boolean readBoolean() 从输入流中读取boolean类型的数据
* String readUTF() 从输入流中读取若干字节，然后转化成UTF-8编码的字符串

为了使用过滤流，需要在创建过滤流时将过滤流连接到另一个输入(输出)流上。例如，可以将DataInputStream连接到一个FileInputStream流上，用户就可以方便的使用DataInputStream类的readXXX()方法类实现从标准输入中读取数据

## DataInputStream流的应用

使用DataInputStream流，从文件"b.txt"中读取一条记录，然后在控制台中输出记录的内容

import java.io.*;

public class DataIn{
    public static void main(String[] args){
        int n;
        String s;
        try{
            //声明DataInputStream流对象，并指定文件"d:/test/txt"为输入流的源
            DataInputStream dis=new DataInputStream(new FileInputStream("d:/test.txt"));
            n=dis.readInt();
            s=dis.readUTF();//读取UTF编码格式的字符串
            System.out.println(s+" "+n);
        }catch(Exception e){ }
}




## 对象流和序列化

### 序列化的概念

对象的寿命通常随着生成该对象的程序的终止而终止。
有时候，可能需要将对象的状态保存下来，在需要时再恢复对象。我们把对象的这种记录自己的状态一边将来再生的能力，叫做对象的持久性(Persistence).对象通过写出描述自己状态的数值来记录自己，这个过程叫对象的序列化(Serialization)

对象序列化的目的时将对象保存到磁盘上，或者允许在网络上传输对象，对象序列化机制就是把内存中的Java对象转换为平台无关的字节流，从而允许把这种字节流持久保存在磁盘上，通过网络将这种字节流传送到另一台主机上。其他程序一旦获得这种字节流，就可以恢复原来的Java对象

如果一个对象可以被存放到磁盘上，或者可以发送到另外一台主机并存放到存储器或者是磁盘上，那么这个对象就是可序列化的。Java对象序列化不仅保留一个对象的数据，而且递归保存对象引用的每个对象的数据

java序列化比较简单，不需要编写保存和恢复对象状态代码的定制代码。实现Java.io.Serializable接口的类对象可以转换成字节流或者从字节流恢复，不需要在类中增加任何代码。不过Serializable接口中并没有规范任何必须实现的代码，所以这里所谓的实现其实类似于为对象贴上一个标志，代表该对象是可以被序列化的。

序列化分为两项内容：序列化和反序列化

序列化是这个过程的第一部分，将数据分解成字节流，一边存储在文件中或在网络上进行传输；反序列化就是打开字节流并且重构对象

要序列化一个对象，必须与一定的对象输入/输出流联系起来，通过对象输出流将对象状态保存下来，再通过对象输入流将对象恢复

java.io包中，提供了ObjectInputStream和ObjectOutputStream将数据流功能扩展至可读写对象。在ObjectInputStream中，使用readObject()方法，用户可以直接读取一个对象，在ObjectOutputStream中，用writeObject()方法，可以直接将对象保存到输出流中

## ObjectOutputStream

ObjectOutputStream是一个处理流，所以必须建立在其他节点流的基础之上，例如，先创建一个FileOutputStream输出流对象，再基于这个对象创建一个对象输出流

FileOutputStream fileOut=new FileOutputStream("book.txt");

ObjectOutputStream objectOut=new ObjectOutputStream(fileOut);

writeObject()方法用于将对象写入到流中。所有的对象(包括String和数组)都可以通过writeObject写入，可以同时将多个对象或基元写入流中，代码如下：

objectOut.writeObject("Hello");

objectOut.writeObject(new Date());

对象的默认序列化机制写入的内容是对象的类，类签名，以及非瞬态和非静态字段的值。其他对象的引用也会导致写入对象

ObjectInputStream的构造方法有两种：

* ObjecetOutputStream() 为完全重新实现ObjectOutputStream的子类提供一种方法，让它不必分配仅由ObjectOutputStream的实现使用的私有数据
* ObjectOutputStream(OutputStream out) 创建写入指定OutputStream的ObjectOutputStream

ObjectOutputStream类的常用方法

* void defaultWriteObject() 将当前类的非静态和非瞬态字段写入此流
* void flush() 刷新该流的缓冲
* void reset() 重置将丢弃已写入流中的所有对象的状态
* void




