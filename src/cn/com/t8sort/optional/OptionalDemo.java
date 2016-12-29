package cn.com.t8sort.optional;

import java.util.NoSuchElementException;
import java.util.Optional;

/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2016��12��29�� ����3:48:22 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class OptionalDemo {

	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		    //����Optionalʵ����Ҳ����ͨ����������ֵ�õ���
		    Optional<String> name = Optional.of("Sanaulla");
		 
		    //����û��ֵ��Optionalʵ��������ֵΪ'null'
		    Optional empty = Optional.ofNullable(null);
		 
		    //isPresent�����������Optionalʵ���Ƿ���ֵ��
		    if (name.isPresent()) {
		      //����get()����Optionalֵ��
		      System.out.println(name.get());
		    }
		 
		    try {
		      //��Optionalʵ���ϵ���get()�׳�NoSuchElementException��
		      System.out.println(empty.get());
		    } catch (NoSuchElementException ex) {
		      System.out.println(ex.getMessage());
		    }
		 
		    //ifPresent��������lambda���ʽ������
		    //���Optionalֵ��Ϊ�գ�lambda���ʽ�ᴦ��������ִ�в�����
		    name.ifPresent((value) -> {
		      System.out.println("The length of the value is: " + value.length());
		    });
		 
		    //�����ֵorElse�����᷵��Optionalʵ�������򷵻ش���Ĵ�����Ϣ��
		    System.out.println(empty.orElse("There is no value present!"));
		    System.out.println(name.orElse("There is some value!"));
		 
		    //orElseGet��orElse���ƣ��������ڴ����Ĭ��ֵ��
		    //orElseGet����lambda���ʽ����Ĭ��ֵ��
		    System.out.println(empty.orElseGet(() -> "Default Value"));
		    System.out.println(name.orElseGet(() -> "Default Value"));
		 
		    try {
		      //orElseThrow��orElse�������ƣ��������ڷ���ֵ��
		      //orElseThrow�׳��ɴ����lambda���ʽ/���������쳣��
		      empty.orElseThrow(ValueAbsentException::new);
		    } catch (Throwable ex) {
		      System.out.println(ex.getMessage());
		    }
		 
		    //map����ͨ�������lambda���ʽ�޸�Optonalʵ��Ĭ��ֵ�� 
		    //lambda���ʽ����ֵ���װΪOptionalʵ����
		    Optional<String> upperName = name.map((value) -> value.toUpperCase());
		    System.out.println(upperName.orElse("No value found"));
		 
		    //flatMap��map��Funtion���ǳ����ƣ���������lambda���ʽ�ķ���ֵ��
		    //map������lambda���ʽ����ֵ�������κ����ͣ����Ƿ���ֵ���װ��Optionalʵ����
		    //����flatMap������lambda����ֵ����Optional���͡�
		    upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
		    System.out.println(upperName.orElse("No value found"));
		 
		    //filter�������Optionaֵ�Ƿ��������������
		    //������㷵��Optionalʵ��ֵ�����򷵻ؿ�Optional��
		    Optional<String> longName = name.filter((value) -> value.length() > 6);
		    System.out.println(longName.orElse("The name is less than 6 characters"));
		 
		    //��һ��ʾ����Optionalֵ���������������
		    Optional<String> anotherName = Optional.of("Sana");
		    Optional<String> shortName = anotherName.filter((value) -> value.length() > 6);
		    System.out.println(shortName.orElse("The name is less than 6 characters"));
		 
		  }
}
