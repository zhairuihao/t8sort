package cn.com.t8sort.javabasic;
/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2017��2��8�� ����2:20:01 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class MethodStatic {

	
	static final String $1 = "1";
	final String $2 = "2";
	/**
	 * ����Ϊstatic�ķ��������¼������ƣ�mainҲ�ǣ���
	 * A�����ǽ��ܵ���������static ����
	 * B������ֻ�ܷ���static����
	 * C�����ǲ������κη�ʽ����this ��super(this�漰������super ��̳��йأ�
	 */
	public static void method1(){
		System.out.println("��ʼ");
	}
	/*public static final void method1(){
		System.out.println("��ʼ");
	}*/
	public static final void method1(String s){
		System.out.println("����");
	}
	public String method2(){
		MethodStatic.method1();
		
		return $1;
	}
}
