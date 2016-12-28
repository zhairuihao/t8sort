package cn.com.t8sort.thread;

import java.util.List;

import junit.framework.TestCase;

/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2016��12��14�� ����2:45:06 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 * ���Զ��̴߳�������
 */
public class TaskMulThreadServiceTest extends TestCase implements ITaskHandle<String,Boolean>{

    public void testExecute() throws Exception {
        String [] taskItems = new String[100];
        for (int i=0;i<100;i++){
            taskItems[i]="����"+i;
        }
        IMulThreadService<String,Boolean> mulThreadService = new TaskMulThreadService(this);
        long start = System.currentTimeMillis();
        List<Boolean> result = mulThreadService.execute(taskItems);
        for (Boolean e : result){
            if(!e){
                System.out.println("������ʧ��");
            }
        }
        System.out.println("������������ɣ���ʱ"+(System.currentTimeMillis()-start)+",����ɹ���"+result.size());
    }

    /**
     * ִ�����񣬷�������ִ�еĽ��
     * className: TaskMulThreadService
     *
     * @author: ddys
     * @version 1.0
     * Date Time:
     */
    public Boolean execute(String s) {
        System.out.println(Thread.currentThread().getId()+"�߳����ڴ���"+s);
        return true;
    }
}
