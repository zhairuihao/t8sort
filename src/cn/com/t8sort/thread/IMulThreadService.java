package cn.com.t8sort.thread;

import java.util.List;

/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2016��12��14�� ����2:52:54 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@SuppressWarnings("hiding")
public interface IMulThreadService<String,Boolean> {

	List<Boolean> execute(String[] taskItems);

	List<Boolean> execute();

}
