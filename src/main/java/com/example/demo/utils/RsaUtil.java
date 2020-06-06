package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sebastian
 * @date 2020/5/23 10:56
 */
@Slf4j
public class RsaUtil {
    public static final int KEY_SIZE = 2048;

    /**
     * 获取公私钥
     *
     * @return map; public key & private key
     * @throws NoSuchAlgorithmException error algorithm
     */
    public static Map<String, String> generateKeyPair() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(KEY_SIZE, secureRandom);
        KeyPair keyPair = rsa.generateKeyPair();
        PublicKey aPublic = keyPair.getPublic();
        byte[] encoded = aPublic.getEncoded();
        String s = Base64.getEncoder().encodeToString(encoded);
        PrivateKey aPrivate = keyPair.getPrivate();
        byte[] encoded1 = aPrivate.getEncoded();
        String s1 = Base64.getEncoder().encodeToString(encoded1);

        Map<String, String> map = new HashMap<>();
        map.put("privateKey", s1);
        map.put("publicKey", s);
        return map;
    }

    /**
     * 加密
     *
     * @param source    需要加密的数据
     * @param publicKey rsa公钥
     * @return 加密后的字符串
     * //     * @throws NoSuchPaddingException
     * //     * @throws NoSuchAlgorithmException
     * //     * @throws InvalidKeyException
     * //     * @throws BadPaddingException
     * //     * @throws IllegalBlockSizeException
     */
    public static String encrypt(String source, String publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        PublicKey publicKey1 = getPublicKey(publicKey);
        Cipher rsa = Cipher.getInstance("RSA");
        rsa.init(Cipher.ENCRYPT_MODE, publicKey1);
        byte[] bytes = source.getBytes();
        byte[] bytes1 = rsa.doFinal(bytes);
        return Base64.getEncoder().encodeToString(bytes1);
    }

    /**
     * 解密
     *
     * @param cryptoGraph 密文
     * @param privateKey  私钥
     * @return 解密后的数据
     * //     * @throws NoSuchPaddingException
     * //     * @throws NoSuchAlgorithmException
     * //     * @throws InvalidKeyException
     * //     * @throws BadPaddingException
     * //     * @throws IllegalBlockSizeException
     */
    public static String decrypt(String cryptoGraph, String privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        PrivateKey privateKey1 = getPrivateKey(privateKey);
        Cipher rsa = Cipher.getInstance("RSA");
        rsa.init(Cipher.DECRYPT_MODE, privateKey1);
        byte[] decode = Base64.getDecoder().decode(cryptoGraph.getBytes());
        byte[] bytes = rsa.doFinal(decode);
        return new String(bytes);
    }

    public static PublicKey getPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
        KeyFactory rsa = KeyFactory.getInstance("RSA");
        return rsa.generatePublic(x509EncodedKeySpec);
    }

    public static PrivateKey getPrivateKey(String key) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
        KeyFactory rsa = KeyFactory.getInstance("RSA");
        return rsa.generatePrivate(pkcs8EncodedKeySpec);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Map<String, String> stringStringMap = generateKeyPair();
        System.out.println(stringStringMap.get("publicKey"));
        System.out.println(stringStringMap.get("privateKey"));
    }
}
