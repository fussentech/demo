/*
 * Fussen Technologies
 */

package com.fussentech.infra.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public final class PasswordGenerator {
	private static final String strA2Z = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String stra2z = "abcdefghijklmnopqrstuvwxyz";
	private static final String strDigits = "0123456789";
	private static final String strSymbols = "~!@#$%^&*()_=+-/,.?<>[])";
	private boolean useUpper = false;
	private boolean useLower = false;
	private boolean useDigit = false;
	private boolean useSymbol = false;

	public static class Builder {
		private boolean useUpper = false;
		private boolean useLower = false;
		private boolean useDigit = false;
		private boolean useSymbol = false;
		
		public Builder useUpper() {
			useUpper = true;
			return this;
		}
	
		public Builder useLower() {
			useLower = true;
			return this;
		}
		
		public Builder useDigit() {
			useDigit = true;
			return this;
		}
		
		public Builder useSymbol() {
			useSymbol = true;
			return this;
		}
		public PwdGenerator build() {
			return new PasswordGenerator(this);
		}
	}
	
	private PasswordGenerator() {
		throw new UnsupportedOperationException("Empty constructor not supported");
	}
	
	private PasswordGenerator(Builder builder) {
		useUpper = builder.useUpper;
		useLower = builder.useLower;
		useDigit = builder.useDigit;
		useSymbol = builder.useSymbol;
	}
	
	String generatePassword(int len) {
		if (len <= 0) {
			return "";
		}
		SecureRandom random = new SecureRandom();
		char[] pwd = new char[len];
		int index = 0;
		List<String> charsets = new ArrayList<String>();
		if (useUpper && index < len) {
			pwd[index++] = strA2Z.charAt(random.nextInt(strA2Z.length()));
			charsets.add(strA2Z);
		}
		if (useLower && index < len) {
			pwd[index++] = stra2z.charAt(random.nextInt(stra2z.length()));
			charsets.add(stra2z);
		}
		if (useDigit && index < len) {
			pwd[index++] = strDigits.charAt(random.nextInt(strDigits.length()));
			charsets.add(strDigits);
		}
		if (useSymbol && index < len) {
			pwd[index++] = strSymbols.charAt(random.nextInt(strSymbols.length()));
			charsets.add(strSymbols);
		}
		for (int i = index; i < len; i++) {
			String charset = charsets.get(random.nextInt(charsets.size()));
			pwd[i]=(charset.charAt(random.nextInt(charset.length())));
		}
		System.out.println(new String(pwd));
		// shuffle
		for (int i = 0; i < len; i++) {
			int tempIndex = random.nextInt(len-i);
			System.out.println(tempIndex);
			char tempChar = pwd[i];
			pwd[i] = pwd[tempIndex];
			pwd[tempIndex] = tempChar;
			
		}
		System.out.println(new String(pwd));
		return new String(pwd);
	}
}

