package cn.com.t8sort.liftcycle;
/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2017��1��4�� ����9:19:19 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public enum LifecycleState {


    NEW, //����

    INITIALIZING, INITIALIZED, //��ʼ��

    STARTING, STARTED, //����

    SUSPENDING, SUSPENDED, //��ͣ

    RESUMING, RESUMED,//�ָ�

    DESTROYING, DESTROYED,//����

    FAILED;//ʧ��
	
}
