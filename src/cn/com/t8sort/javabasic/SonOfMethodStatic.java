package cn.com.t8sort.javabasic;
/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2017��2��8�� ����2:23:56 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */ 
public class SonOfMethodStatic extends MethodStatic {
	/**
	 * �ڳ�Ա������ǰ����Ϲؼ���static���ܴ��������ĳ�Ա��
	 * ���һ����Ա������Ϊstatic�������ܹ�����������κζ��󴴽�֮ǰ�����ʣ������������κζ��󣨸����Ƿ���static�����޹أ���
	 */
	public static final SonOfMethodStatic soms = new SonOfMethodStatic();
	SonOfMethodStatic soms_ = new SonOfMethodStatic();
	//Void
	Integer i = 0;
	Character s = 'A';
	
	public static  void method1(){
		System.out.println("��д");
		SonOfMethodStatic.method1("");
		//soms_.method2();
	}
	
	public static void main(String[] args) {
		
		SonOfMethodStatic.method1();
		SonOfMethodStatic.method1("s");
		
		System.out.println(SonOfMethodStatic.soms.method2());
		
		SonOfMethodStatic soms_$ = new SonOfMethodStatic();
		soms_$.method2();
		SonOfMethodStatic.method1();
		
	}
}
