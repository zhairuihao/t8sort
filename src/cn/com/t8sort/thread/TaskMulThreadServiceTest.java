package cn.com.t8sort.thread;

import java.util.List;

import junit.framework.TestCase;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2016年12月14日 下午2:45:06 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 * 测试多线程处理任务
 */
public class TaskMulThreadServiceTest extends TestCase implements ITaskHandle<String,Boolean>{

    public void testExecute() throws Exception {
        String [] taskItems = new String[100];
        for (int i=0;i<100;i++){
            taskItems[i]="任务"+i;
        }
        IMulThreadService<String,Boolean> mulThreadService = new TaskMulThreadService(this);
        long start = System.currentTimeMillis();
        List<Boolean> result = mulThreadService.execute(taskItems);
        for (Boolean e : result){
            if(!e){
                System.out.println("任务处理失败");
            }
        }
        System.out.println("所有任务处理完成，耗时"+(System.currentTimeMillis()-start)+",任务成功数"+result.size());
    }

    /**
     * 执行任务，返回所有执行的结果
     * className: TaskMulThreadService
     *
     * @author: ddys
     * @version 1.0
     * Date Time:
     */
    public Boolean execute(String s) {
        System.out.println(Thread.currentThread().getId()+"线程正在处理"+s);
        return true;
    }
}
