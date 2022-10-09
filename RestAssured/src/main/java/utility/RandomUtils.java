package utility;

import java.security.SecureRandom;

import org.testng.annotations.Test;

public class RandomUtils {
	
	@Test
	public void a() {
		System.out.println(generateRandomString(4));
		System.out.println(generateRandomNumericString(8));
		System.out.println(generateRandomHBnumber());
	}
	
	
	private RandomUtils() {
		
	}
	
	private static final SecureRandom random= new SecureRandom();
	
	public static String generateRandomString(int length) {

		String text ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(length);
		for( int i = 0; i < length; i++ ) 
			sb.append( text.charAt( random.nextInt(text.length()) ) );
		return sb.toString();

	}

	public static String generateRandomNumericString(int length) {
		String textnumber ="0123456789";
		StringBuilder sb = new StringBuilder(length);
		for( int i = 0; i < length; i++ ) 
			sb.append( textnumber.charAt( random.nextInt(textnumber.length()) ) );
		return sb.toString();

	}

	public static String generateRandomMasterBillNumber() {
		return "MAMU"+generateRandomString(1)+generateRandomNumericString(2);
	}

	public static String generateRandomMasterFileNumber() {
		return generateRandomString(6)+generateRandomNumericString(3);
	}

	

	public static String generateRandomHBnumber() {
		
		return generateRandomString(3)+generateRandomNumericString(3);
	}


	

}
