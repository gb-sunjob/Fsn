
//javac .\JosephCycle.java
//java JosephCycle
class Node{
	int num;				//创建类似于节点的内容
	Node nextNode=null;		//初始nextNode为空
	public Node(int num){	//在构造方法中初始化属性num
		this.num=num;
	}
}

class CycleLink{
	int len;				//总共的节点大小，即总共有多少个节点
	int k;					//节点的编号，类似于节点的下标号
	int m;					//节点的引用指向
	Node firstNode=null;		//头节点初始为空
	Node temp=null;				//尾节点初始为空

								//给各个属性进行初始化
	public void setLen (int len){ 
		this.len=len;
	}
	public void setK(int k){
		this.k=k;
	}
	public void setM(int m){
		this.m=m;
	}

	//创建循环链表
	public void createLink(){

		for(int i=1;i<=len;i++){		//循环len个次数  从i=1开始循环，循环到len，构建链表中的数据
										//当执行for循环的时候，当第一次循环的时候，开始的该数值即应当为头节点
										//即该第一个值应当在链表中的第一个，头节点
				if(i==1){
										//创建一个节点，其内容为i,即该节点的num为i，头节点的num=1
				Node node=new Node(i);	

										//firstNode初始为空，即firstNode的初始指向的是null
										//firstNode 相当于一个引用，由于这是第一次循环，第一次循环的该数值为头节点
										//为头节点firstNode进行赋值，赋值的内容即为刚刚内容num为1的一个节点
										//即firstNode该引用指向这个num=1的这个节点，因为这是在第一次循环当中
				firstNode=node;		
										//由于是循环链表，头尾的节点都是该节点
										//即将初始同样指向空null的尾节点temp的引用指向该num=1的节点node
				temp=node;
			}
										//所以首先，先得到生成一个节点，给该节点的num属性进行赋值为1，
										//又由于该是第一次循环，头节点，给该头结点进行赋值，
										//由于是循环链表，所以得到头节点的同时即得到了尾节点


			else if(i==len){			//如果该循环的次数是到了最后一次了，即相当于len是该链表当中的最后一个，即为尾节点

				Node node=new Node(i);	//创建一个节点Node,其内容为i,即该节点的num为i

				temp.nextNode=node;		//由于需要将该最后一个节点添加到该链表当中去，
										//即将该节点添加到该链表当中的最后一个，由链表当中的最后一个来进行添加
										//即给最后一个节点的下一个节点进行赋值即可，赋值的内容即为该节点
										//temp此时指向的是上一次循环当中添加到链表当中的节点
										//该步骤即相当于将节点添加到链表当中来

				temp=node;				//由于该链表刚刚进行了新增，
										//所以之前链表当中的最后一个已经不再是现在链表当中的最后一个了
										//一定要知道的是temp或者是firstNode都只是一个引用并不意味着一个真正永远不变的节点对象
										//一旦新增进来一个对象，temp就要发生变动，即temp相当于一个标识，
										//也就是表示新增进来的这个节点是该链表当中的最后一个节点的意思
										//所以就需要对temp该引用进行重新赋值，赋值的内容即为刚刚新生成的节点内容num为len的节点的地址

										/*
										赋值：
										Node temp=null;
														在栈中存在一个引用，
														该引用的名字叫做temp，
														该引用指向的地址即指向的是常量池当中为null的地址
														即可以说成是temp身上携带着的是一份地址信息，也就是说
														栈中名叫temp的引用像一个超链接一样，点击进去，发现该引用真正是一个什么东西
														打个比方：
														<a href="www.baidu.com">百度</a>
														有一个叫做百度的网站类型的链接，
														该链接中存的值是百度首页的网址，
														点击该链接，
														点击进去发现该链接打开了的是百度的首页
														那么进行类比
														Node temp就相当于说成是一个叫做temp的Node类型的链接，该链接中存的值是常量池当中值为null的一个地址
														那么点击该链接进去，发现该链接指向的是常量池当中的null值

														一定需要注意的是是先存在常量池当中值为null的一份地址，也就是说肯定是先存在百度这个首页
														百度的这个超链接才能够进行指向百度首页
														所以说常量池当中先存在有了null这个值的地址，
														而后栈中类型为Node的名叫作temp的引用才能对其进行指向
										Node node=new Node(i);
														该句话意为生成一个对象
														即在栈中有一个类型为Node类型的名称叫做node引用指向的是
														堆中内容为i的一个对象
														即栈中的这个引用身上携带的是堆中内容为i的这个对象的地址
														new Node(i)
														在堆中生成这个内容为i的这个对象之前
														先需要做一件事情，即判断在常量池当中存不存在内容为i的一份地址
														若在常量池当中已经存在这样一个内容为i的这样一份地址，
														那么就接着才在堆中进行开辟放置内容为i的这样一块内存
														若在常量池当中不存在这样一个内容为i的这样一份地址的话，
														那么就需要现在常量池当中先进行生成内容为i的这样一个地址
														然后才会去堆中进行生成开辟一块内容为i的对象的地址

														有new，即代表的是生成一个对象，
														即在堆中代表着开辟了一块内存，以供该对象存放数据，存放该对象的属性等等数据
														此位置是唯一的
														且在new时，栈中的引用指向的是堆中的这个对象的地址
														而并非是常量池当中的那一份地址
														
										temp=node;		
														栈中的temp引用原本身上携带的地址是常量池当中值为null的一份地址
														Node node=new Node(i);该node身上携带的是 堆中内容为i的对象的一份唯一的内存地址
														此操作称之为赋值
														赋的是什么值？
														注意栈中的temp引用身上携带的值是常量池当中值为null的那一份地址
														node身上携带的值是堆中内容为i的对象的那一份唯一的内存地址
														temp=node
														即将node身上携带的那一份在堆中内容为i的对象的那一份唯一的内存地址 
														复制一份
														给了栈中类型为Node类型的名叫作temp的引用，
														那么此时temp身上携带的那一份指向常量池当中值为null的地址将会
														新的被复制过来的内存地址信息给覆盖掉
														即栈中类型为Node类型的名叫作temp的引用与栈中类型为Node类型的名叫作node的引用
														都同时指向的是堆中内容为i的对象
														*/

				temp.nextNode=firstNode;				/*
														将链表当中的头节点该引用所携带的这一份地址复制给temp.nextNode
														即复制给了该链表当中的最后一个节点的下一个
														即循环链表头尾相连接起来
														*/
														
			}
			else{
				Node node=new Node(i);					/*
														只要是对于添加到链表当中的元素，都将会是成为一个节点，该节点的内容即为该元素
														*/
				temp.nextNode=node;						/*
														将添加到该链表当中的该元素添加到该链表当中的最后一个，即该链表当中的最后一个tail的下一个即为node
														*/
				temp=node;
														/*
														最后是为temp进行重新赋值
														由于在末尾新添加进来了一个元素
														即temp该标识需要指向的是该新添加进来的该元素
														*/
			}
		}
	}
	public void play(){
		temp=firstNode;									/*
														 循环链表，尾节点即被赋值成为头节点														 
													 	*/
		for(int i=1;i<k;i++){				
			temp=temp.nextNode;
		}												/*
															k代表的是该从第一个节点开始循环进行报数
															从1开始进行循环，循环到要报出的数k截止
															即循环一次，就将该temp引用所携带的地址进行更新一次
															即到k时，得到的就是被报数出来的数
														*/
		while(this.len!=1){
			for(int j=1;j<m-1;j++){
				temp=temp.nextNode;
			}											/*
														在进行了删除报出的那个数的操作之后
														进行循环
														判断如果该循环链表的长度不为1，即最后还剩下一个值
														循环报出下标数，得到的是该下标数-1的位置上的值
														即得到的是要被删除的数的节点的上一个节点
														temp此时被赋值成为的是被报数的那个节点的上一个节点的地址信息
														*/
			System.out.println("need delete nodes:"+temp.nextNode.num);
														/**
														如果删除删到最后得到的是被报数的那个节点的上一个节点的下一个节点如果为头节点的话
														代表的含义即为需要删除头节点
														即为那么为头节点的引用进行重新赋值，赋值
														赋值令头节点即为最后一个的下一个的下一个
														由于循环链表的特性
														所以可得最后一个尾节点的下一个即为头节点
														头节点的下一个即为当该头节点发生了引用改变的时候，要变成新的头节点的那一个节点
														 */
			if(temp.nextNode==firstNode){				
				firstNode=temp.nextNode.nextNode;
			}									
			temp.nextNode=temp.nextNode.nextNode;
			temp=temp.nextNode;
			this.len--;
														/*
														那么此时的头节点就应该为即将该最后一个节点的下一个节点头节点的下一个节点
														*/
		}
	}
	public void show(){
		temp=firstNode;
		do{
			System.out.println(temp.num);
			temp=temp.nextNode;
		}while(temp!=firstNode);
	}
}
public class JosephCycle{
	public static void main(String[] args){
		CycleLink cycleLink=new CycleLink();
		cycleLink.setLen(7);
		cycleLink.setK(3);
		cycleLink.setM(3);
		cycleLink.createLink();
		cycleLink.play();
		cycleLink.show();
	}
}