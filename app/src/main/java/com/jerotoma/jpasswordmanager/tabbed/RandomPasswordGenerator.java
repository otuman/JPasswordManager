package com.jerotoma.jpasswordmanager.tabbed;

import java.util.Random;

public class RandomPasswordGenerator {
	private static final String ALPHA_CAPS 	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA 	= "abcdefghijklmnopqrstuvwxyz";
	private static final String NUM 	= "0123456789";
	private static final String SPL_CHARS	= "!@#$%^&*_=+-/";
	private static Random rnd = new Random();

	public static char[] generatePswd(int noOfCAPSAlpha, 
			int noOfDigits, int noOfSplChars, int len ) {

		Random rnd = new Random();
		
		char[] pswd = new char[len];
		int index = 0;
		for (int i = 0; i < noOfCAPSAlpha; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
		}
		for (int i = 0; i < noOfDigits; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
		}
		for (int i = 0; i < noOfSplChars; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
		}
		for(int i = 0; i < len; i++) {
			if(pswd[i] == 0) {
				pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
			}
		}
		return pswd;
	}
	
	private static int getNextIndex(Random rnd, int len, char[] pswd) {
		int index = rnd.nextInt(len);
		while(pswd[index = rnd.nextInt(len)] != 0);
		return index;
	}
	
	public static String getRandomNumber(int digCount) {
	    StringBuilder sb = new StringBuilder(digCount);
	    for(int i=0; i < digCount; i++)
	        sb.append((char)('0' + rnd.nextInt(10)));
	    return sb.toString();
	}
}