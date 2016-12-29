package cn.com.t8sort.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2016年12月29日 上午10:11:34
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@SuppressWarnings("unused")
public class NewFeatureTester {

	final static String salutation = "HELLO ";

	public static void main(String[] args) {

		NewFeatureTester t = new NewFeatureTester();

		// 带有类型声明的表达式
		MathOperation addtion = (int a, int b) -> a + b;

		// 没有类型申明的表达式
		MathOperation subtraction = (a, b) -> a - b;

		// 带有大括号、带有返回语句的表达式
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// 没有大括号和返回语句的表达式
		MathOperation division = (int a, int b) -> a / b;

		// 输出
		/*
		 * System.out.println("10+5= " +t.operation(10, 5, addtion));
		 * System.out.println("10-5= " +t.operation(10, 5, subtraction));
		 * System.out.println("10*5= " +t.operation(10, 5, subtraction));
		 * System.out.println("10/5= " +t.operation(10, 5, division));
		 */

		GreetingService g = message -> System.out.println(salutation + message);
		// g.sayMessage("Marry");

		/**
		 * 方法引用
		 */

		List<String> names = Arrays.asList("Peter", "Linda", "Smith", "Zack", "Bob");
		// 通过System.out::println引用输出的方法
		// names.forEach(System.out::println);

		/**
		 * @FunctionalInterface 进行声明。在接口中添加了
		 * @FunctionalInterface 的接口，只允许有一个抽象方法，否则编译器也会报错。
		 */
		// 函数式接口的使用
		List<Integer> l = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

		/*
		 * System.out.println("ALL OF THE NUMBERS: "); eval(l, n-> true);
		 * System.out.println("Even numbers: "); eval(l, n-> n%2 ==0);
		 * System.out.println("Numbers that greater than  5: "); eval(l, n->
		 * n>5);
		 */
		//内部类的调用
		NewFeatureTester.Younger younger = t.new Student();
		//younger.print();
		
		/***
		 * optional类
		 * 它是一个可以为null的容器对象。
		 * 如果值存在则 isPresent() 方法会返回 true ，调用 get()方法会返回该对象。
		 */
		
		Integer v1 = null;
		Integer v2 = new Integer(5);
		
		//ofNullable 允许传参时给出 null
		Optional<Integer> a = Optional.ofNullable(v1);
	
		//如果传递的参数为null, 那么of 将抛出空指针异常
		Optional<Integer> b = Optional.of(v2);
		
		System.out.println(t.sum(a, b));
		

	}

	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

	private int operation(int a, int b, MathOperation mathOperation) {

		return mathOperation.operation(a, b);
	}

	public static void eval(List<Integer> list, Predicate<Integer> predicate) {

		for (Integer i : list) {
			if (predicate.test(i)) {
				System.out.println(i + "  ");
			}
		}

	}

	interface Younger {
		default void print() {
			System.out.println("I am a younger.");
		}

		static void sayHi() {
			System.out.println("Young is the capital.");
		}
	}

	interface Learner {
		default void print() {
			System.out.println("I am a learner.");
		}
	}

	class Student implements Younger, Learner {
		public void print() {
			Younger.super.print();
			Learner.super.print();
			Younger.sayHi();
			System.out.println("I am a student!");
		}
	}
	
	public Integer sum(Optional<Integer> a, Optional<Integer> b){
		
		System.out.println("a is present:"+ a.isPresent());
		System.out.println("b is present:"+ b.isPresent());
		
		//如果当前返回的是传入的默认值，orElse将返回他
		Integer v1 = a.orElse(new Integer(0));
		//get 用于获得值，条件是这个值必须存在
		Integer v2 = b.get();
		
		return v1+v2;
		
	}
}
