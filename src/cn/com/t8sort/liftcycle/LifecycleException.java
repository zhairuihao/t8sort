package cn.com.t8sort.liftcycle;

import java.io.IOException;

/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2017��1��4�� ����9:21:22 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class LifecycleException extends Exception{

	public LifecycleException(String formatString, Throwable t) {
		// TODO Auto-generated constructor stub
	}

	public LifecycleException(IOException e) {
		// TODO Auto-generated constructor stub
	}

	
	public LifecycleException(String msg) {
		// TODO Auto-generated constructor stub 
		 super(msg);  
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
