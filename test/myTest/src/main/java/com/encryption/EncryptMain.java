package com.encryption;

/**
 * Created by zjm on 2017/3/28.
 */
public class EncryptMain {

    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("20170328170500");//时间戳
        buffer.append("1001");//业务线编码，1001为uop的业务线编码
        buffer.append("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApwbuhLHGlRmJwFwnH07ubOMZ3BFuEnAi\n" +
                "q+lRYGMZ5KznAO9p+ReEYU+smh/2FH94RRIDD0qi7Y29DfW0eUbhGiW7YV1dPtQ6obEeqBno2ZKN\n" +
                "SKiwNfyq+1PQuZBtmv0PuJlPTdlWEBSVkC3F3Twhaqgvxpv9Sy8FotZr4Sd4KYTNaW+YKOF0R5ph\n" +
                "sOEU2lhnc1gSr2jBdXg91eYp70/nEhS3lFEPZNO7d3XObLfMVirJnrTUQKE3RBUB4OqYVJscTMaz\n" +
                "Gy8wRz59ag9qPCHMhsF4iy+RmnfuLL7Ib94W1eQciQYMLLZHclnlGoiF8qDH0V8I8C/JZag9Skey\n" +
                "Iav45wIDAQAB");//密钥，固定值
        String sign = Encrypt.encrypt(buffer.toString(), "");
        System.out.println(sign);
    }
}
