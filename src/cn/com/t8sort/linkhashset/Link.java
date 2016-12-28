package cn.com.t8sort.linkhashset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2016年12月12日 下午4:01:58
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class Link {

	public static List<String> LinkHashSet(List<String> ss) {

		LinkedHashSet<String> ls = new LinkedHashSet<String>(ss);

		ss = new ArrayList<String>(ls);

		return ss;

	}

	public static List<String> sHashSet(List<String> ss) {

		HashSet<String> ls = new HashSet<String>(ss);

		ss = new ArrayList<String>(ls);

		return ss;

	}

	public static void main(String[] args) {
		
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "C++");
        
		System.out.println("linkedhashset:"+LinkHashSet(languages));
		
		System.out.println("hashset:"+sHashSet(languages));
		
	}
}
