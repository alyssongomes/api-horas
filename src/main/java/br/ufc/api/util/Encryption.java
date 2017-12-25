package br.ufc.api.util;

import java.security.MessageDigest;

public class Encryption {
	public static String encrpyt(String pass){
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
	        byte messageDigest[] = algorithm.digest(pass.getBytes("UTF-8"));
	        
	        StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
              hexString.append(String.format("%02X", 0xFF & b));
            }
            String passhex = hexString.toString();
	        return passhex;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
}
