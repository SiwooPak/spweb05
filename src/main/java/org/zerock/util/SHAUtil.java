package org.zerock.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHAUtil {
	 
    public static String getEncrypt(String source, String salt) {
        return getEncrypt(source, salt.getBytes());
    }
    
    public static String getEncrypt(String source, byte[] salt) {
        
        String result = "";
        
        byte[] a = source.getBytes();
        byte[] bytes = new byte[a.length + salt.length];
        
        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(salt, 0, bytes, a.length, salt.length);
        
        try {
        	
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            
            byte[] byteData = md.digest();
            
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }
            
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static String generateSalt() {
    	
        Random random = new Random();
        
        byte[] salt = new byte[20];
        
        random.nextBytes(salt);
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < salt.length; i++) {
            sb.append(String.format("%02x",salt[i]));
        }
        
        return sb.toString();
    }
    
    
    public static String encryptSHA256(String str) {
	    String SHA = null;
	    try {
	        MessageDigest sh = MessageDigest.getInstance("SHA-256"); // 이 부분을 SHA-1으로 바꿔도 된다!
	        sh.update(str.getBytes()); 
	        byte byteData[] = sh.digest();
	        StringBuffer sb = new StringBuffer(); 
	        for(int i = 0 ; i < byteData.length ; i++){
	            sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	        }
	        SHA = sb.toString();
	    } catch(NoSuchAlgorithmException e){
	        e.printStackTrace(); 
	    }
	    return SHA;
	}
    
}

