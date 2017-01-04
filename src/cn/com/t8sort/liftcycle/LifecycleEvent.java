package cn.com.t8sort.liftcycle;
/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年1月4日 上午9:23:51 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public final class LifecycleEvent {

	private LifecycleState state;

    public LifecycleEvent(LifecycleState state) {
        this.state = state;
    }

    /**
     * @return the state
     */
    public LifecycleState getState() {
        return state;
    }
}
