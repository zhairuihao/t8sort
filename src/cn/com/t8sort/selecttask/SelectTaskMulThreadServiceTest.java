package cn.com.t8sort.selecttask;

import java.util.List;

import cn.com.t8sort.thread.IMulThreadService;
import junit.framework.TestCase;

/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2016��12��14�� ����3:08:17 
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
                System.out.println("������ʧ��");
            }
        }
        System.out.println("������������ɣ���ʱ"+(System.currentTimeMillis()-start)+",����ɹ���"+result.size());
    }
    /**
     * Created with IntelliJ IDEA.
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

    /**
     * @param 'a ���ݲ���
     * @return a ������
     * @throws
     * @Title: a
     * @Description: ��ȡһ������
     * @author ddys
     * @date 2015-11-15 21:09
     */
    public String[] getTaskItem() {
        String [] taskItems = new String[100];
        for (int i=0;i<100;i++){
            taskItems[i]="����"+i;
        }
        return taskItems;
    }
}