package com.encryption;

import com.me.string.StringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 验签生成类
 * Created by zjm on 2017/3/28.
 */
public final class Encrypt {

    private static final String DEFAULT_METHOD = "SHA-256";

    public static String encrypt(String sourceStr, String encryptName){
        if(StringUtil.isBlank(encryptName)){
            encryptName = DEFAULT_METHOD;
        }
        MessageDigest md;
        String strDes;

        byte[] bytes = sourceStr.getBytes();
        try{
            md = MessageDigest.getInstance(encryptName);
            md.update(bytes);
            strDes = bytes2Hex(md.digest());
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        StringBuffer buffer = new StringBuffer();
        String tmp;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                buffer.append('0');
            }
            buffer.append(tmp);
        }
        return buffer.toString();
    }

}
