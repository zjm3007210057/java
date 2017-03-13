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
        MessageDigest md = null;
        byte[] bytes = null;
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
        String tmp = null;
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

    public static void main(String[] args) {
        String sha = "201703091100001001MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApwbuhLHGlRmJwFwnH07ubOMZ3BFuEnAiq+lRYGMZ5KznAO9p+ReEYU+smh/2FH94RRIDD0qi7Y29DfW0eUbhGiW7YV1dPtQ6obEeqBno2ZKNSKiwNfyq+1PQuZBtmv0PuJlPTdlWEBSVkC3F3Twhaqgvxpv9Sy8FotZr4Sd4KYTNaW+YKOF0R5phsOEU2lhnc1gSr2jBdXg91eYp70/nEhS3lFEPZNO7d3XObLfMVirJnrTUQKE3RBUB4OqYVJscTMazGy8wRz59ag9qPCHMhsF4iy+RmnfuLL7Ib94W1eQciQYMLLZHclnlGoiF8qDH0V8I8C/JZag9SkeyIav45wIDAQAB";
        //a92492420ea43ab6e4a0b918362a8ba5d4862c2508b17a9caf50d719a8ee6e41
        List ss = Arrays.asList(new String[]{"a", "d"});
        String sendRecord = "1489067148009|1489067225047|1489071173669|";
        List lostTimes = Arrays.asList(sendRecord.split("\\|"));
            lostTimes.forEach( timeStr -> {
                long lostTime = Long.parseLong(timeStr.toString());
                System.out.println(lostTime);
            });
        //36b64f0c05b2626abb4d2c2a1408cda650dd2761827c614fc6c81debbc9be1d2
        System.out.println(ss.contains(""));
        System.out.println(encrypt(sha, ""));
    }
}
