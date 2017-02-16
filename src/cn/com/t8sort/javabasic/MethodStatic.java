package cn.com.t8sort.javabasic;
/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年2月8日 下午2:20:01 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class MethodStatic {

	
	static final String $1 = "1";
	final String $2 = "2";
	/**
	 * 声明为static的方法有以下几条限制（main也是）：
	 * A，它们仅能调用其他的static 方法
	 * B，它们只能访问static数据
	 * C，它们不能以任何方式引用this 或super(this涉及到对象，super 与继承有关）
	 */
	public static void method1(){
		System.out.println("开始");
	}
	/*public static final void method1(){
		System.out.println("开始");
	}*/
	public static final void method1(String s){
		System.out.println("重载");
	}
	public String method2(){
		MethodStatic.method1();
		
		return $1;
	}
}
