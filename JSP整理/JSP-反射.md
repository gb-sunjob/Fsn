# 反射

> 反射的原理

 * 生活当中用得最多的反射就是照镜子(reflect),通过镜子可以看到整个物体
 * java同样存在反射
 * 反射的作用
    * 每一个类都会有一个镜子
    * 通过反射可以了解到一个类中的所有的属性，方法...
 * Class最初用oop指的是用java语言去描述一个java类；
 * 描述一个类通过属性和方法来描述一个类；
 * 镜子即相当于反射类；
 * 一个类由属性和方法组成；
 * 每一个类当中包含了很多的信息，例如属性，方法，访问修饰符... 
 * 所以Java中定义了一个Class类，这个类专门用来描述java中的类的信息
 * 类=属性+方法
 * 小写"c"的class是一个关键字，用以来声明一个类
 * 大写"C"的Class是一个类，专用来描述java中的类的信息的类

 
 > 得到镜子的方式

 1. 通过类名来获取镜子
    * 类名.class
    * Class class=Student.class;
    * 得到镜子c,那么镜子c描述的即为Student类
    * Class class2=class.getSuperClass();
    * class代表的是Student类的映射，如果Student并没有继承任何的父类/没有指定的父类，那么class2即指的是Object类。
    * 即class.getSuperClass()得到的是Student类的映射的父类，倘若有指定的父类，那么class2具体描述的就是父类；若没有指定的父类，那么class2具体描述的就是Object类
    * Object类是所有子类直接或者是间接的父类。
 2. 通过对象来获取镜子
    * 对象.getClass()
    * Student student=new Student(); 
    *  Class class3=student.getClass();
    

  

    






