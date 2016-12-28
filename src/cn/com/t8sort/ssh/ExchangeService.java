package cn.com.t8sort.ssh;

import java.util.Date;
import java.util.Map;

/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2016��12��23�� ����12:53:45 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 * ��һ���ж�����е����ݾ������ɹ�Կ��˽Կ�����ҷ��ع�Կ��
 * �ڶ����ж�����е����ݾ��Ǳ���client���͵�����ַ�������һ���ǳ��ؼ�������ַ�������ͨ����Կ�����˼��ܣ������ǿ�˼��ܵ���ȡ�
 * �������ж�����е����ݾ��ǽ�client��Ȩ��ͨ������ַ������м��ܡ�
 */
public class ExchangeService {

	public byte[] request(String param, String resultType) {
	    logger.info("���������" + param);

	    // ���ض���
	    KeyResult keyResult = new KeyResult();

	    try {
	        // �Ȼ�ȡ��Կ
	        if (resultType.equals(PUBLIC_KEY_RESULT_TYPE)) {

	            Map<String, Object> keyMap = RSACoder.initKey();
	            // ������Կ��˽Կ
	            privateKey = RSACoder.getPrivateKey(keyMap);

	            keyResult.setKey(RSACoder.getPublicKey(keyMap));

	            logger.info("��Կ�ַ�����" + keyResult.getKey());
	            logger.info("˽Կ�ַ�����" + privateKey);

	        } else if (resultType.equals(ECHOSTR_RESULT_TYPE)) {

	            // ���ÿͻ��˵Ŀ�����Ϣ
	            byte[] paramByte = new BASE64Decoder().decodeBuffer(param);
	            echoStr = new String(RSACoder.decryptByPrivateKey(paramByte, privateKey));

	        } else {
	            // ͨ�����ݿ��ȡ��������Ӧ��Ȩ����Ϣ.
	            // �Ƚ�����ת��Ϊbyte���飬Ȼ���ٽ��н��ܣ����ת��Ϊ�ַ���
	            ExchangeInfo info = ExchangeInfo.dao.getInfoByName(new String(CryptUtil.decrypt(
	                    new BASE64Decoder().decodeBuffer(param), echoStr.getBytes())));

	            String result = "";

	            // ��ȡϵͳ����Ȩ��
	            if (resultType.equals(PRIVILEGE_RESULT_TYPE)) {
	                // ���ж�ʹ��Ȩ��

	                // ���ж�ʹ������
	                // ��ǰ��¼�õ�¼ʱ��ȡ��¼�ĵ�ǰ���ںͿ�ʼ���ڽ��бȽϣ�Ȼ����㻹����ʹ�õ�����
	                long time = (new Date().getTime() / 1000) - string2DateInt(openday);
	                // ���������
	                int day = (int) (time / (60 * 60 * 24));
	                // ������ʹ�õ�����
	                if (usedays - day > 0) {
	                    // ����ʹ��
	                    result = "1";
	                } else {
	                    // �޷�ʹ��
	                    result = "0";
	                }

	            }

	            keyResult.setResult(CryptUtil.encrypt(result.getBytes(), echoStr.getBytes()));
	        }

	        return JsonUtil.objectToByte(keyResult);
	    } catch (Exception e) {
	        logger.error("webservice�����ˣ�������");
	        logger.error(e.getMessage(), e);
	    }

	    return null;
	}
}
