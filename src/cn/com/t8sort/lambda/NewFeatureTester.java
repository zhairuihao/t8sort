package cn.com.t8sort.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author ���� E-mail:
 * @date ����ʱ�䣺2016��12��29�� ����10:11:34
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

		// �������������ı��ʽ
		MathOperation addtion = (int a, int b) -> a + b;

		// û�����������ı��ʽ
		MathOperation subtraction = (a, b) -> a - b;

		// ���д����š����з������ı��ʽ
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// û�д����źͷ������ı��ʽ
		MathOperation division = (int a, int b) -> a / b;

		// ���
		/*
		 * System.out.println("10+5= " +t.operation(10, 5, addtion));
		 * System.out.println("10-5= " +t.operation(10, 5, subtraction));
		 * System.out.println("10*5= " +t.operation(10, 5, subtraction));
		 * System.out.println("10/5= " +t.operation(10, 5, division));
		 */

		GreetingService g = message -> System.out.println(salutation + message);
		// g.sayMessage("Marry");

		/**
		 * ��������
		 */

		List<String> names = Arrays.asList("Peter", "Linda", "Smith", "Zack", "Bob");
		// ͨ��System.out::println��������ķ���
		// names.forEach(System.out::println);

		/**
		 * @FunctionalInterface �����������ڽӿ��������
		 * @FunctionalInterface �Ľӿڣ�ֻ������һ�����󷽷������������Ҳ�ᱨ��
		 */
		// ����ʽ�ӿڵ�ʹ��
		List<Integer> l = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

		/*
		 * System.out.println("ALL OF THE NUMBERS: "); eval(l, n-> true);
		 * System.out.println("Even numbers: "); eval(l, n-> n%2 ==0);
		 * System.out.println("Numbers that greater than  5: "); eval(l, n->
		 * n>5);
		 */
		//�ڲ���ĵ���
		NewFeatureTester.Younger younger = t.new Student();
		//younger.print();
		
		/***
		 * optional��
		 * ����һ������Ϊnull����������
		 * ���ֵ������ isPresent() �����᷵�� true ������ get()�����᷵�ظö���
		 */
		
		Integer v1 = null;
		Integer v2 = new Integer(5);
		
		//ofNullable ������ʱ���� null
		Optional<Integer> a = Optional.ofNullable(v1);
	
		//������ݵĲ���Ϊnull, ��ôof ���׳���ָ���쳣
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
		
		//�����ǰ���ص��Ǵ����Ĭ��ֵ��orElse��������
		Integer v1 = a.orElse(new Integer(0));
		//get ���ڻ��ֵ�����������ֵ�������
		Integer v2 = b.get();
		
		return v1+v2;
		
	}
}
