package cn.com.t8sort.liftcycle;
/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年1月4日 上午9:19:19 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public enum LifecycleState {


    NEW, //新生

    INITIALIZING, INITIALIZED, //初始化

    STARTING, STARTED, //启动

    SUSPENDING, SUSPENDED, //暂停

    RESUMING, RESUMED,//恢复

    DESTROYING, DESTROYED,//销毁

    FAILED;//失败
	
}
