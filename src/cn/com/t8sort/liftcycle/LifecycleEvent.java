package cn.com.t8sort.liftcycle;
/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2017��1��4�� ����9:23:51 
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
