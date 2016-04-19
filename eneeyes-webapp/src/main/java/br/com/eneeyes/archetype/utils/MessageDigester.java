package br.com.eneeyes.archetype.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public final class MessageDigester {
	public static final String MD2 = "MD2";
	public static final String MD5 = "MD5";
	public static final String SHA1 = "SHA-1";
	public static final String SHA256 = "SHA-256";
	public static final String SHA384 = "SHA-384";
	public static final String SHA512 = "SHA-512";
	
	public static String digestMD5(String message) throws Throwable {
		return digest(MD5,message);
	}
	
	public static String digestSha1(String message) throws Throwable {
		return digest(SHA1,message);
	}
	
	private static String digest(String algorithm, String message) throws Throwable {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			return (new BigInteger(1, digest.digest(message.getBytes()))).toString(16);
		} catch(Throwable t) {
			throw new Throwable(t);
		}
	}

    public static String encode64(String s) {
        return Base64.encodeBase64String(s.getBytes());
    }

    public static String decode64(String s) {
        return new String(Base64.decodeBase64(s.getBytes()));
    }
}