package com.example;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author jy
 * AES加密算法模式有四种：ECB、CBC、CFB、OFB
 * 要想AES加密，至少需要一个16位的密钥，如果是非ECB模式的加密，至少还得需要密钥偏移量。
 * 当前采用：加密方式AES；算法模式 CBC ；补码方式:PKCS5Padding;加密结果编码方式：十六进制(字母为小写)
 */
public class AesCBCWithBase64 {
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final String ALGORITHM_AES = "AES";
    /*
    * 加密用的Key 可以用26个字母和数字组成
	* 此处使用AES-128-CBC加密模式,key需要为16位。
	*/
    public static final String AES_KEY = "DSzn0818#ibotn%^";
    public static final String AES_IV_PARAMETER = "^%ntobi#8180nzSD";
    private static volatile AesCBCWithBase64 instance = null;

    private AesCBCWithBase64() {
    }

    public static AesCBCWithBase64 getInstance() {
        if (instance == null){
            instance = new AesCBCWithBase64();
        }
        return instance;
    }

    /**
     * 加密方式AES，算法模式 CBC ；补码方式:PKCS5Padding;加密结果编码方式：十六进制(字母为小写)
     * @param sSrc 待加密内容
     * @param encodingFormat 编码格式
     * @param sKey 秘钥
     * @param ivParameter 偏移量
     * @return String
     * @throws Exception Exception
     */
    public String encrypt(String sSrc, String encodingFormat, String sKey,
                          String ivParameter) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM_AES);
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 密钥偏移量
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
//        return HexConverter.parseByte2HexStr(encrypted).toLowerCase();
        return (new BASE64Encoder()).encodeBuffer(encrypted);
    }

    /**
     * 解密方式AES，算法模式 CBC ；补码方式:PKCS5Padding;解密结果编码方式：十六进制(字母为小写)
     * @param sSrc 待加密内容
     * @param encodingFormat 编码格式
     * @param sKey 秘钥
     * @param ivParameter 偏移量
     * @return String
     * @throws Exception Exception
     */
    public String decrypt(String sSrc, String encodingFormat, String sKey,
                          String ivParameter) throws Exception {
        try {
            byte[] raw = sKey.getBytes(DEFAULT_CHARSET);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
//            byte[] encrypted1 = HexConverter.parseHexStr2Byte(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, encodingFormat);
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }
    public static void main(String[] args) throws Exception {
        //需要加密的字串
        String cSrc = "abc";
        System.out.println(cSrc);
        //加密
        long lStart = System.currentTimeMillis();
        String enString = AesCBCWithBase64.getInstance().encrypt(cSrc, DEFAULT_CHARSET, AES_KEY,
                AES_IV_PARAMETER);
        System.out.println("加密后的字串是：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        //解密
        lStart = System.currentTimeMillis();
        String DeString = AesCBCWithBase64.getInstance().decrypt("eD/LZtKTqst6YU0BqCb6hY2SnMEVaH/+ZQ4m1Xu1kO+EEGON2Q9du3wSsqEMABhB", DEFAULT_CHARSET, AES_KEY,
                AES_IV_PARAMETER);
        System.out.println("解密后的字串是：" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
    }

    /**
     * abc
     * 加密后的字串是：Rqpop0x8J64rHCwosP2tgg==
     *
     * 加密耗时：139毫秒
     * 解密后的字串是：abc
     * 解密耗时：2毫秒
     *
     * 使用在线aes加密工具：http://tool.chacuo.net/cryptaes
     * Rqpop0x8J64rHCwosP2tgg==
     */

}