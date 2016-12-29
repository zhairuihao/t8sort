package cn.com.t8sort.optional;
/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2016年12月29日 下午3:50:18 
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
