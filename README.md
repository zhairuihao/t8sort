# 身为一把梭的日常 -.-

1  字符串判断的总结（cn.com.t8sort.isnull）

2  java8新特性  流和lambda的使用练习（cn.com.t8sort.lambda）

3  LIST去重  linkHashSet & HashSet (cn.com.t8sort.linkhashset)

4  java实现微信红包的算法 （cn.com.t8sort.redpacket）

5  我也忘记是干嘛的了 （cn.com.t8sort.selecttask）

6  数组排序的n种方法 （cn.com.t8sort.sort）

7  java实现ssh加密 未完待续...（cn.com.t8sort.ssh）

8  线程相关...（cn.com.t8sort.thread）

9  别踩白块的核心代码实现 （JS-WEB/GAME-BLACK-AND-WHITE）

10 2048的核心代码实现 （JS-WEB/2048）

11 java 生命周期管理机制
	
	新生 -> 初始化中 -> 初始化完成 -> 启动中 -> 启动完成 -> 正在暂停 -> 已经暂停 -> 正在恢复 -> 已经恢复 ->    
	
	正在销毁 -> 已经销毁
	
	生命周期中的各种行为规范，也需要一个接口来定义（ILifecycle）
	
	监听者也由一个接口来定义其行为规范。（ILifecycleListener ）
	
	生命周期事件由LifecycleEvent来表示。（LifecycleEvent）
	
	有了ILifeCycle接口以后，任何实现了这个接口的类将会被作为一个生命周期管理对象，这个类可以是一
	
	个socket监听服务，也可以代表一个特定的模块，等等。那我们是不是只要实现ILifeCycle就可以了? 可以这么
	
	说，但考虑到各个生命周期管理对象在生命周期的各个阶段会有一些共同的行为，比如说：
	
	*设置自身的生命周期状态
	*检查状态的转换是否符合逻辑
	*通知监听者生命周期状态发生了变化
	
	因此，提供一个抽象类AbstractLifeCycle，作为ILifeCycle的骨架实现是有重要意义的，这样避免了很多的重
	
	复代码，使得架构更加清晰。这个抽象类会实现ILifeCycle中定义的所有接口方法，并添加对应的抽象方法，供子
	
	类实现。
	
	象类的骨架实现中做了几件生命周期管理中通用的事情，检查状态之间的转换是否合法(比如说start之前必须
	
	要init)，设置内部状态，以及触发相应的监听者。
	
	抽象类实现了ILifeCycle定义的方法后，又留出了相应的抽象方法供其子类实现，如上面的代码所示，其留出来的
	
	抽象方法有以下这些:
	
	*protected abstract void init0() throws LifecycleException;
	*protected abstract void start0() throws LifecycleException;
	*protected abstract void suspend0() throws LifecycleException;
	*protected abstract void resume0() throws LifecycleException;
	*protected abstract void destroy0() throws LifecycleException;
	
	到目前为止，我们已经定义了接口ILifeCycle，以及其骨架实现AbstractLifeCycle，并且增加了监听者机

	制。貌似我们可以开始写一个类来继承AbstractLifecycle，并重写其定义的抽象方法了
	
	我们的实现类是否对所有的抽象方法都感兴趣？
	
	*是否每个实现累都需要实现init0, start0, suspend0, resume0, destroy0?
	*是否有时候，我们的那些有生命的类或者模块并不支持暂停(suspend),恢复(resume)?
	*直接继承AbstractLifeCycle，就意味着必须实现其全部的抽象方法。
	
	因此，我们还需要一个默认实现，DefaultLifeCycle，让它继承AbstractLifeCycle，并实现所有抽象方
	
	法，但它并不做任何实际的事情, do nothing。只是让我们真正的实现类来继承这个默认的实现类，并重写感兴趣
	
	的方法。
	
	对于DefaultLifeCycle来说，do nothing就是其职责。
	因此接下来我们可以写一个自己的实现类，继承DefaultLifeCycle，并重写那些感兴趣的生命周期方法。
	
# MARKDOWN 画流程图

  	st=>start: Start
	op=>operation: Your Operation
	sub=>subroutine: My Subroutine
	cond=>condition: Yes or No?
	io=>inputoutput: catch something...
	e=>end: End
	
	st->op->cond
	cond(yes)->io->e
	cond(no)->sub(right)->op