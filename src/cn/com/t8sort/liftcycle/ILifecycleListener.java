package cn.com.t8sort.liftcycle;
/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年1月4日 上午9:22:34 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public interface ILifecycleListener {

	
	/**
     * 对生命周期事件进行处理
     * 
     * @param event 生命周期事件
     */
    public void lifecycleEvent(LifecycleEvent event);
    
}
