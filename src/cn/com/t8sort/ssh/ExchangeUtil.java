package cn.com.t8sort.ssh;
/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2016年12月23日 下午12:55:00 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 * 通过循环主要为了防止网络断开时服务不停的发送请求，最多10次就够了
 */
public class ExchangeUtil {

	public static boolean canRunForExchange(String resultType) {
	    int i = 1;
	    boolean result = false;

	    while (true) {
	        try {
	            // webservice调用类
	            ExchangeServiceProxy proxy = new ExchangeServiceProxy();
	            BASE64Encoder encoder = new BASE64Encoder();

	            // step1.获取service产生的公钥
	            KeyResult keyResult = JsonUtil.byteToObject(proxy.request(null, PUBLIC_KEY_RESULT_TYPE),
	                    KeyResult.class);

	            // step2.产生随机字符串，发送到webserivce
	            String echoStr = StrUtil.getEchoStrByLength(10);
	            byte[] echoByteParam = RSACoder.encryptByPublicKey(echoStr.getBytes(), keyResult.getKey());
	            proxy.request(encoder.encode(echoByteParam), ECHOSTR_RESULT_TYPE);

	            // step3.加密客户端请求信息，然后发送到webservice
	            // 先加密为byte数组，然后转换成字符串
	            byte[] results = proxy.request(
	                    encoder.encode(CryptUtil.encrypt(Constants.client_type.getBytes(), echoStr.getBytes())),
	                    resultType);
	            keyResult = JsonUtil.byteToObject(results, KeyResult.class);

	            // step4.通过口令解密服务端返回消息
	            String response = new String(CryptUtil.decrypt(keyResult.getResult(), echoStr.getBytes()));
	            if (response.equals("1")) {
	                result = true;
	            }
	            break;
	        } catch (Exception e) {
	            logger.debug("第" + i + "次加载webservice失败");
	            i++;
	            logger.error(e.getMessage(), e);

	            if (i >= 10) {
	                break;
	            }
	        }
	    }

	    return result;
	}
}
