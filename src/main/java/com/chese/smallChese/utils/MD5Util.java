package com.chese.smallChese.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	public static String getMD5(String param){
		MessageDigest instance;
		String result = "";
		try {
			instance = MessageDigest.getInstance("MD5");
			byte[] digest = instance.digest(param.getBytes());
			result = digest.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		MessageDigest instance;
		String result = "";
		try {
			instance = MessageDigest.getInstance("MD5");
			byte[] digest = instance.digest("QgUG2+6NCXIqEXh6RtRS/g==".getBytes());
			result = digest.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("result" + result);
	}

}
