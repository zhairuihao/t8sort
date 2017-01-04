package cn.com.t8sort.liftcycle;
/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2017��1��4�� ����9:20:22 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public interface ILifecycle {

	 /**
     * ��ʼ��
     * 
     * @throws LifecycleException
     */
    public void init() throws LifecycleException;

    /**
     * ����
     * 
     * @throws LifecycleException
     */
    public void start() throws LifecycleException;

    /**
     * ��ͣ
     * 
     * @throws LifecycleException
     */
    public void suspend() throws LifecycleException;

    /**
     * �ָ�
     * 
     * @throws LifecycleException
     */
    public void resume() throws LifecycleException;

    /**
     * ����
     * 
     * @throws LifecycleException
     */
    public void destroy() throws LifecycleException;

    /**
     * ����������ڼ�����
     * 
     * @param listener
     */
    public void addLifecycleListener(ILifecycleListener listener);

    /**
     * ɾ���������ڼ�����
     * 
     * @param listener
     */
    public void removeLifecycleListener(ILifecycleListener listener);
}
