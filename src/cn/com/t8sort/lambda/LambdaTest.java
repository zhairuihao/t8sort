package cn.com.t8sort.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2016年11月10日 上午8:53:25 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class LambdaTest {
	
	static Predicate<String> startsWithJ = (n) -> n.startsWith("J");
	
	static Predicate<String> fourLetterLong = (n) -> n.length() == 4;
	
	/**
	 * list过滤
	 * @param names
	 * @param condition
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void filter(List<String> names, Predicate condition) {
		
	/*	for(String name: names)  {
			if(condition.test(name)) {
				System.out.println(name + " ");
			}
		}*/
		
		names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
			System.out.println(name + " ");
		});
		
/*		names.stream()
		.filter(startsWithJ.and(fourLetterLong))
		.forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));*/
		
	}
	
	/**
	 * 加%12的税
	 * @param costBeforeTax
	 */
	public  static void filter1(List<Integer> costBeforeTax){
		
		costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
	}
	/**
	 * 加税求和
	 * @param costBeforeTax
	 */
	public  static void filter2(List<Integer> costBeforeTax){
		
		double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);
	}
	
	/**
	 * 过滤创建list
	 */
	public static void filter3(List<String> strList){
		
		// 创建一个字符串列表，每个字符串长度大于2
		List<String> filtered = strList.stream().filter(x -> x=="Java").collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
	}
	/**
	 * 对列表的每个元素应用函数
	 * @param args
	 */
	public static void filter4(List<String> G7){
		// 将字符串换成大写并用逗号链接起来
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(G7Countries);
	}
	/**
	 * 复制不同的值，创建一个子列表
	 * @param G7
	 */
	public static void filter5(List<Integer> numbers){
		// 用所有不同的数字创建一个正方形列表
		List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
	}
	/**
	 * 计算集合元素的最大值、最小值、总和以及平均值
	 * @param numbers
	 */
	public static void filter6(List<Integer> primes){
		
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
		
		System.out.println("Highest prime number in List : " + stats.getMax());
		System.out.println("Lowest prime number in List : " + stats.getMin());
		System.out.println("Sum of all prime numbers : " + stats.getSum());
		System.out.println("Average of all prime numbers : " + stats.getAverage());
	}
	public static void main(String[] args) {
		
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

	/*	System.out.println("Languages which starts with J :");
		filter(languages, (str)->((String) str).startsWith("J"));

		System.out.println("Languages which ends with a ");
		filter(languages, (str)->((String) str).endsWith("a"));

		System.out.println("Print all languages :");
		filter(languages, (str)->true);

		System.out.println("Print no language : ");
		filter(languages, (str)->false);

		System.out.println("Print language whose length greater than 4:");
		filter(languages, (str)->((String) str).length() > 4);
		*/
		//加%12的税
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
       
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		
		filter1(costBeforeTax);
		filter2(costBeforeTax);
		filter3(languages);
		filter4(G7);
		filter5(numbers);
		filter6(primes);
		
		
	}
}
