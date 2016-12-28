package cn.com.t8sort.ssh;
/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2016��12��23�� ����12:55:00 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 * ͨ��ѭ����ҪΪ�˷�ֹ����Ͽ�ʱ����ͣ�ķ����������10�ξ͹���
 */
public class ExchangeUtil {

	public static boolean canRunForExchange(String resultType) {
	    int i = 1;
	    boolean result = false;

	    while (true) {
	        try {
	            // webservice������
	            ExchangeServiceProxy proxy = new ExchangeServiceProxy();
	            BASE64Encoder encoder = new BASE64Encoder();

	            // step1.��ȡservice�����Ĺ�Կ
	            KeyResult keyResult = JsonUtil.byteToObject(proxy.request(null, PUBLIC_KEY_RESULT_TYPE),
	                    KeyResult.class);

	            // step2.��������ַ��������͵�webserivce
	            String echoStr = StrUtil.getEchoStrByLength(10);
	            byte[] echoByteParam = RSACoder.encryptByPublicKey(echoStr.getBytes(), keyResult.getKey());
	            proxy.request(encoder.encode(echoByteParam), ECHOSTR_RESULT_TYPE);

	            // step3.���ܿͻ���������Ϣ��Ȼ���͵�webservice
	            // �ȼ���Ϊbyte���飬Ȼ��ת�����ַ���
	            byte[] results = proxy.request(
	                    encoder.encode(CryptUtil.encrypt(Constants.client_type.getBytes(), echoStr.getBytes())),
	                    resultType);
	            keyResult = JsonUtil.byteToObject(results, KeyResult.class);

	            // step4.ͨ��������ܷ���˷�����Ϣ
	            String response = new String(CryptUtil.decrypt(keyResult.getResult(), echoStr.getBytes()));
	            if (response.equals("1")) {
	                result = true;
	            }
	            break;
	        } catch (Exception e) {
	            logger.debug("��" + i + "�μ���webserviceʧ��");
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
