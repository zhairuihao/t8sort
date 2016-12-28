package cn.com.t8sort.thread;

import java.util.List;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2016年12月14日 下午2:52:54 
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
