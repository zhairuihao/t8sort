package cn.com.t8sort.selecttask;

import java.util.List;

import cn.com.t8sort.thread.IMulThreadService;
import junit.framework.TestCase;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2016年12月14日 下午3:08:17 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class SelectTaskMulThreadServiceTest extends TestCase implements ISelectTask<String,Boolean>{

    public void testExecute() throws Exception {
        IMulThreadService<String,Boolean> mulThreadService = new SelectTaskMulThreadService(this);
        long start = System.currentTimeMillis();
        List<Boolean> result = mulThreadService.execute();
        for (Boolean e : result){
            if(!e){
                System.out.println("任务处理失败");
            }
        }
        System.out.println("所有任务处理完成，耗时"+(System.currentTimeMillis()-start)+",任务成功数"+result.size());
    }
    /**
     * Created with IntelliJ IDEA.
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

    /**
     * @param 'a 传递参数
     * @return a 回类型
     * @throws
     * @Title: a
     * @Description: 获取一批任务
     * @author ddys
     * @date 2015-11-15 21:09
     */
    public String[] getTaskItem() {
        String [] taskItems = new String[100];
        for (int i=0;i<100;i++){
            taskItems[i]="任务"+i;
        }
        return taskItems;
    }
}