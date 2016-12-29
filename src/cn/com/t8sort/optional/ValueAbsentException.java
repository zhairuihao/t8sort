package cn.com.t8sort.optional;
/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2016��12��29�� ����3:50:18 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class ValueAbsentException extends Throwable {
	 
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValueAbsentException() {
	    super();
	  }
	 
	  public ValueAbsentException(String msg) {
	    super(msg);
	  }
	 
	  @Override
	  public String getMessage() {
	    return "No value present in the Optional instance";
	  }
}
