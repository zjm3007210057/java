package com.me.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zjm on 2017/3/4.
 */
public class EncryptUtil {

    public final static String ALGORITHM_SHA_256 = "SHA-256";
    public final static String ALGORITHM_SHA_512 = "SHA-512";
    public final static String ALGORITHM_MD5 = "MD5";
    public final static String ALGORITHM_DES = "DESede/ECB/PKCS5Padding";


    /**
     * 256加密
     *
     * @param sourceString
     * @return
     */
    public static String SHA256ToHexString(final String sourceString) {
        return encryptToHexString(sourceString, ALGORITHM_SHA_256);
    }

    /**
     * 256加密
     *
     * @param sourceString
     * @return
     */
    public static String SHA256Bytes2Hex(final String sourceString) {
        return encryptBytes2Hex(sourceString, ALGORITHM_SHA_256);
    }

    /**
     * 加密
     *
     * @param sourceString 原字符串
     * @param encryptType  加密算法类型
     * @return 加密后的字符串
     */
    public static String encryptToHexString(final String sourceString, final String encryptType) {
        String res = null;
        MessageDigest md;
        byte[] bytes;
        try {
            md = MessageDigest.getInstance(encryptType);
            md.update(sourceString.getBytes());
            bytes = md.digest();
            res = toHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 加密
     * @param sourceString 原字符串
     * @param encryptType 加密类型
     * @return 加密串
     */
    public static String encryptBytes2Hex(final String sourceString, final String encryptType) {
        String res = null;
        MessageDigest md;
        byte[] bytes;
        try {
            md = MessageDigest.getInstance(encryptType);
            md.update(sourceString.getBytes());
            bytes = md.digest();
            res = bytes2Hex(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * byte数组转字符串
     * @param bts
     * @return
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * bytes转成字符串
     *
     * @param data
     * @return
     */
    public static String toHexString(byte[] data) {
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            strBuffer.append(Integer.toHexString(0xff & data[i]));
        }
        return strBuffer.toString();
    }


}
