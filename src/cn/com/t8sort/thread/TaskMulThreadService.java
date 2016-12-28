package cn.com.t8sort.thread;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2016年12月14日 下午2:52:21 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class TaskMulThreadService implements IMulThreadService<String, Boolean>{

	public TaskMulThreadService(TaskMulThreadServiceTest taskMulThreadServiceTest) {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unused")
	@Override
	public List<Boolean> execute(String[] taskItems) {
		// TODO Auto-generated method stub
		List<Boolean> ls = new ArrayList<Boolean>();
		
		for(String ss:taskItems){
			//dosomething
			
			ls.add(false);
		}
		
		return ls; 
	}
	@Override
	public List<Boolean> execute() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
