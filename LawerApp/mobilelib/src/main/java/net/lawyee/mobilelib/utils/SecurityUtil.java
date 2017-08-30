package net.lawyee.mobilelib.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: 信息加密工具类
 * @Package net.lawyee.mobilelib.utils
 * @Description:
 * @author:wuzhu
 * @date: 2017/5/23
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class SecurityUtil {
    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private String sKey;
    private String ivParameter;
    /**
     *
     * @param key 加密所需的key,key需要为16位,且只能用26个字母和数字组成。
     * @param ivs 加密所需的向量iv，向量iv需要为16位,且只能用26个字母和数字组成。
     */
    public SecurityUtil(String key,String ivs) {
        if(key == null||key.length()==0||key.length() % 16!=0) {
            throw new IllegalArgumentException("key需要为16位,且只能用26个字母和数字组成。");
        }
        sKey = key;
        if(ivs == null||ivs.length()==0||ivs.length() % 16!=0) {
            throw new IllegalArgumentException("向量iv需要为16位,且只能用26个字母和数字组成。");
        }
        ivParameter = ivs;
    }

    /**
     * 生成合法的key，需要为16位,且只能用26个字母和数字组成
     * @param str
     * @return 如果输入为空、空字符串或并非用用26个字母和数字组成的，将非法字符自动删除,长度不足自动后补0
     */
    public static String getLegalKey(String str)
    {
        String zeros = "0000000000000000";
        if(StringUtil.isEmpty(str))
            return zeros;
        //移除非法字符
        String result = str.replaceAll("[^0-9a-zA-Z]","");
        if(result.length()==0)
            return zeros;
        if(result.length()>16)
            return result.substring(0, 16);
        if(result.length()%16!=0)
        {
            result = result+ zeros.substring(0,16-result.length()%16);
        }
        return result;
    }

    /**
     *  加密
     * @param sSrc
     * @return
     */
    public String encrypt(String sSrc) {
        return SecurityUtil.Encrypt(sSrc,sKey,ivParameter);
    }

    /**
     *  解密
     * @param sSrc
     * @return
     */
    public String decrypt(String sSrc){
        return SecurityUtil.Decrypt(sSrc,sKey,ivParameter);
    }

    /**
     * AES加密，CBC模式
     * @param encData 需要加密的数据
     * @param key 加密所需的key,key需要为16位,且只能用26个字母和数字组成。
     * @param ivs 加密所需的向量iv，向量iv需要为16位,且只能用26个字母和数字组成。
     * @return
     */
    public static String Encrypt(String encData ,String key,String ivs)  {
        if(StringUtil.isEmpty(encData))
            return "";
        if(key == null||key.length()==0||key.length() % 16!=0) {
            throw new IllegalArgumentException("key需要16的整数倍,且只能用26个字母和数字组成。");
        }
        if(ivs == null||ivs.length()==0||ivs.length() % 16!=0) {
            throw new IllegalArgumentException("向量iv需要16的整数倍,且只能用26个字母和数字组成。");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
            return Base64.encodeToString(encrypted,Base64.NO_WRAP);
            //return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * AES解密，CBC模式
     * @param sSrc 需要解密的数据
     * @param key 加密所需的key,key需要为16位,且只能用26个字母和数字组成。
     * @param ivs 加密所需的向量iv，向量iv需要为16位,且只能用26个字母和数字组成。
     * @return
     */
    public static String Decrypt(String sSrc,String key,String ivs) {

        if(StringUtil.isEmpty(sSrc))
            return "";
        if(key == null||key.length()==0||key.length() % 16!=0) {
            throw new IllegalArgumentException("key需要16的整数倍,且只能用26个字母和数字组成。");
        }
        if(ivs == null||ivs.length()==0||ivs.length() % 16!=0) {
            throw new IllegalArgumentException("向量iv需要16的整数倍,且只能用26个字母和数字组成。");
        }
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(sSrc,Base64.NO_WRAP);
            //byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encodeBytes(byte[] bytes) {
        if(bytes==null||bytes.length<1)
            return "";
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }

        return strBuf.toString();
    }
}
