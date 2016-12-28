package cn.com.t8sort.ssh;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2016年12月23日 下午12:55:48 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class CryptUtil {
    /** 
    * @Title: encrypt 
    * @Description: 加密
    * @param data
    * @param key
    * @return
    * @throws Exception
    */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        key = get8(key);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return cipher.doFinal(data);
    }

    /** 
    * @Title: decrypt 
    * @Description: 解密
    * @param data
    * @param key
    * @return
    * @throws Exception
    */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        key = get8(key);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        return cipher.doFinal(data);
    }

    private static byte[] get8(byte[] key) {
        byte[] key1 = new byte[8];
        for (int i = 0; i < 8; i++) {
            key1[i] = key[i];
        }
        return key1;
    }

    public static String toHexString(byte[] data) {
        String s = "";
        for (int i = 0; i < data.length; i++) {
            s += Integer.toHexString(data[i] & 0xFF)+"-";
        }
        return s;
    }

}
