package cn.com.t8sort.ssh;

import java.util.Date;
import java.util.Map;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2016年12月23日 下午12:53:45 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 * 第一个判断语句中的内容就是生成公钥和私钥，并且返回公钥。
 * 第二个判断语句中的内容就是保存client发送的随机字符串，这一步非常关键，随机字符串首先通过公钥进行了加密，这大大加强了加密的深度。
 * 第三个判断语句中的内容就是将client的权限通过随机字符串进行加密。
 */
public class ExchangeService {

	public byte[] request(String param, String resultType) {
	    logger.info("请求参数：" + param);

	    // 返回对象
	    KeyResult keyResult = new KeyResult();

	    try {
	        // 先获取公钥
	        if (resultType.equals(PUBLIC_KEY_RESULT_TYPE)) {

	            Map<String, Object> keyMap = RSACoder.initKey();
	            // 产生公钥和私钥
	            privateKey = RSACoder.getPrivateKey(keyMap);

	            keyResult.setKey(RSACoder.getPublicKey(keyMap));

	            logger.info("公钥字符串：" + keyResult.getKey());
	            logger.info("私钥字符串：" + privateKey);

	        } else if (resultType.equals(ECHOSTR_RESULT_TYPE)) {

	            // 设置客户端的口令信息
	            byte[] paramByte = new BASE64Decoder().decodeBuffer(param);
	            echoStr = new String(RSACoder.decryptByPrivateKey(paramByte, privateKey));

	        } else {
	            // 通过数据库获取交易所对应的权限信息.
	            // 先将请求转换为byte数组，然后再进行解密，最后转换为字符串
	            ExchangeInfo info = ExchangeInfo.dao.getInfoByName(new String(CryptUtil.decrypt(
	                    new BASE64Decoder().decodeBuffer(param), echoStr.getBytes())));

	            String result = "";

	            // 获取系统启用权限
	            if (resultType.equals(PRIVILEGE_RESULT_TYPE)) {
	                // 先判断使用权限

	                // 在判断使用日期
	                // 当前登录用登录时获取登录的当前日期和开始日期进行比较，然后计算还可以使用的日期
	                long time = (new Date().getTime() / 1000) - string2DateInt(openday);
	                // 换算成天数
	                int day = (int) (time / (60 * 60 * 24));
	                // 还可以使用的天数
	                if (usedays - day > 0) {
	                    // 可以使用
	                    result = "1";
	                } else {
	                    // 无法使用
	                    result = "0";
	                }

	            }

	            keyResult.setResult(CryptUtil.encrypt(result.getBytes(), echoStr.getBytes()));
	        }

	        return JsonUtil.objectToByte(keyResult);
	    } catch (Exception e) {
	        logger.error("webservice出错了！！！！");
	        logger.error(e.getMessage(), e);
	    }

	    return null;
	}
}
