package cn.com.t8sort.javabasic;
/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年2月8日 下午2:23:56 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */ 
public class SonOfMethodStatic extends MethodStatic {
	/**
	 * 在成员的声明前面加上关键字static就能创建这样的成员。
	 * 如果一个成员被声明为static，它就能够在它的类的任何对象创建之前被访问，而不必引用任何对象（跟类是否有static修饰无关）。
	 */
	public static final SonOfMethodStatic soms = new SonOfMethodStatic();
	SonOfMethodStatic soms_ = new SonOfMethodStatic();
	//Void
	Integer i = 0;
	Character s = 'A';
	
	public static  void method1(){
		System.out.println("重写");
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
