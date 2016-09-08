import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MTH816A5{
	public static void main(String args[]){
		
		String input = "magtohash";
		String encryptionPW = "kimchulisbestprofever";
		
		System.out.println("SHA-1");
		long inputTime1 = System.nanoTime();
		String output = hmacSHA1(input);
		System.out.println("HMAC (SHA-1) Verified: " + verifyHMACSHA1 (output));
		long inputTime2 = System.nanoTime();
		long actualTime = inputTime2 - inputTime1;
		System.out.println("Time to calculate "+actualTime);
		
		System.out.println("MD5");
		long inputTime3 = System.nanoTime();
		String outputMD5 = hmacMD5(input);
		System.out.println("HMAC (MD5) Verified: " + verifyHMACMD5 (outputMD5));
		long inputTime4 = System.nanoTime();
		long actualTime2 = inputTime4 - inputTime3;
		System.out.println("Time to calculate "+actualTime2);
		
		System.out.println("AES");
		long inputTime5 = System.nanoTime();
		String outputAES = hmacAES(input, encryptionPW);
		System.out.println("HMAC (AES) Verified: " + verifyHMACAES (outputAES, encryptionPW));
		long inputTime6 = System.nanoTime();
		long actualTime3 = inputTime6 - inputTime5;
		System.out.println("Time to calculate "+actualTime3);	
		
	}

	public static String hmacSHA1(String input) {
		// TODO Auto-generated method stub
		String result = "";
		//sha stuff
		String base64 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(input.getBytes());
			base64 = Base64.getEncoder().encodeToString(digest);
		}
		catch (Exception e){
			
		}
		
		result = input + "yothisiswhereitstops"+base64;
		return result;
	}
	
	public static boolean verifyHMACSHA1 (String input){
		boolean result = false;
		try{
			String actualMsg = input.substring(0, input.indexOf("yothisiswhereitstops"));
			String originalHashedValue = input.substring(input.indexOf("yothisiswhereitstops")+20, input.length());
			String input2 = hmacSHA1(input);
			String returnedValue = input2.substring(input.indexOf("yothisiswhereitstops")+20, input.length());
			if (originalHashedValue.equals(returnedValue)){
				result = true;
				System.out.println("SHA1 HASH: "+returnedValue);
			}
		}
		catch (Exception e){
			
		}
		
		return result;
	}
	
	public static String hmacMD5(String input) {
		// TODO Auto-generated method stub
		String result = "";
		//sha stuff
		String base64 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(input.getBytes());
			base64 = Base64.getEncoder().encodeToString(digest);
		}
		catch (Exception e){
			
		}
		
		result = input + "yothisiswhereitstops"+base64;
		return result;
	}
	
	public static boolean verifyHMACMD5 (String input){
		boolean result = false;
		try{
			String actualMsg = input.substring(0, input.indexOf("yothisiswhereitstops"));
			String originalHashedValue = input.substring(input.indexOf("yothisiswhereitstops")+20, input.length());
			String input2 = hmacMD5(input);
			String returnedValue = input2.substring(input.indexOf("yothisiswhereitstops")+20, input.length());
			if (originalHashedValue.equals(returnedValue)){
				result = true;
				System.out.println("MD5 HASH: "+returnedValue);
			}
		}
		catch (Exception e){
			
		}
		
		return result;
	}
	public static String hmacAES (String input, String encryptionPW){
		String result = "";
		String encoded = "";
		try{
			byte[] key = (encryptionPW).getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); 
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	  	    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] encrypted=cipher.doFinal(input.getBytes());
			//encoded = new String (encrypted);
			encoded = Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception es) {
			es.printStackTrace();
			
		}
		result = input + "yothisiswhereitstops"+encoded;
		return result;
	}
	public static boolean verifyHMACAES (String input, String encryptionPW){
		boolean result = false;
		try{
			String actualMsg = input.substring(0, input.indexOf("yothisiswhereitstops"));
			String originalHashedValue = input.substring(input.indexOf("yothisiswhereitstops")+20, input.length());
			String input2 = hmacAES(input, encryptionPW);
			String returnedValue = input2.substring(input.indexOf("yothisiswhereitstops")+20, input.length());
			if (originalHashedValue.equals(returnedValue)){
				result = true;
				System.out.println("AES HASH: "+originalHashedValue);
			}
		}
		catch (Exception e){
			
		}
		
		return result;
	}
	
}