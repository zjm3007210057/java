package com.me.encrypt;

import com.me.string.StringUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by zjm on 25/04/2017.
 */
public class RSAUtil {

    private final static String KEY_ALGORITHM_RSA = "RSA";

    /**
     * 生成RSA密钥对
     * @return
     */
    public static KeyPair generateRSAKeyPair(){
        try{
            Security.addProvider(new BouncyCastleProvider());
            SecureRandom random = new SecureRandom();
            KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM_RSA);
            generator.initialize(2048, random);

            return generator.generateKeyPair();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取私钥
     * @param keyPair 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(KeyPair keyPair)throws Exception{
        return base64Encrypt(keyPair.getPrivate().getEncoded());
    }

    /**
     * 获取公钥
     * @param keyPair 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(KeyPair keyPair)throws Exception{
        return base64Encrypt(keyPair.getPublic().getEncoded());
    }

    /**
     * 私钥解密
     * @param encryptedData 加密串
     * @param privateKeyEncode 私钥（BASE64编码）
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, byte[] privateKeyEncode)throws Exception{
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyEncode);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        return decryptedData;
    }

    /**
     * 私钥解密 base64字符串
     * @param base64EncryptedData
     * @param privateKeyEncode
     * @return
     * @throws Exception
     */
    public static byte[] decryptBase64ByPrivateKey(String base64EncryptedData, byte[] privateKeyEncode)throws Exception{
        return decryptByPrivateKey(base64Decrypt(base64EncryptedData), privateKeyEncode);
    }

    /**
     * 公钥解密
     * @param encryptedDate 已经加密数据
     * @param publickKeyEncode 公钥（BASE64编码）
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedDate, byte[] publickKeyEncode)throws Exception{
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publickKeyEncode);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        Key publicKey = keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedData = cipher.doFinal(encryptedDate);
        return decryptedData;
    }

    /**
     * 公钥解密
     * @param base64EncryptedData
     * @param publicKeyEncode
     * @return
     * @throws Exception
     */
    public static byte[] decryptBase64ByPublicKey(String base64EncryptedData, byte[] publicKeyEncode)throws Exception{
        return decryptByPublicKey(base64Decrypt(base64EncryptedData), publicKeyEncode);
    }

    /**
     * 公钥加密
     * @param data 数据源
     * @param publicKeyEncode 公钥（BASE64编码）
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, byte[] publicKeyEncode)throws Exception{
        data = StringUtil.trim(data);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyEncode);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        Key publicKey = keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptData = cipher.doFinal(data.getBytes("utf-8"));
        return encryptData;
    }

    /**
     * 私钥加密
     * @param data 数据源
     * @param privateKeyEncode 私钥（BASE64编码）
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(String data, byte[] privateKeyEncode)throws Exception{
        data = StringUtil.trim(data);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyEncode);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptData = cipher.doFinal(data.getBytes("utf-8"));
        return encryptData;
    }

    /**
     * BASE64编码
     * @param bts
     * @return
     */
    public static String base64Encrypt(byte[] bts){
		return new BASE64Encoder().encode(bts);
	}

    /**
     * BASE64解码
     * @param base64Str
     * @return
     * @throws IOException
     */
	public static byte[] base64Decrypt(String base64Str) throws IOException {
		return new BASE64Decoder().decodeBuffer(base64Str);
	}

}
