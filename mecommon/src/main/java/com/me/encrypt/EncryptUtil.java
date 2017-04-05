package com.me.encrypt;

import com.me.string.StringUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zjm on 2017/3/4.
 */
public class EncryptUtil {

    private final static String DEFAULT_ENCRYPT_TYPE = "SHA-256";

    /**
     * 加密
     * @param sourceString 原字符串
     * @param encryptType 加密算法类型
     * @return 加密后的字符串
     */
    public static String encrypt(String sourceString, String encryptType) {
        if (StringUtil.isBlank(encryptType)) {
            encryptType = DEFAULT_ENCRYPT_TYPE;
        }
        String res = null;
        MessageDigest md;
        byte[] bytes;
        try {
            md = MessageDigest.getInstance(encryptType);
            md.update(sourceString.getBytes(/*"UTF-8"*/));
            bytes = md.digest();
            res = toHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } /*catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return res;
    }

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
