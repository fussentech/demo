package com.amechine.infra.utils;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class PwdGeneratorTest {

	//@Test
	public void testDigitWithPositiveLen() {
		int len = 15;
		PwdGenerator pwdgen = new PwdGenerator.Builder().useDigit()
														.build();
		String pwd = pwdgen.generatePassword(len);
		System.out.println(pwd);
		assertEquals(len, pwd.length());
		assertTrue(pwd.matches("[0-9]+"));
	}
	
	//@Test
	public void testLowercase() {
		int len = 8;
		PwdGenerator pwdgen = new PwdGenerator.Builder().useLower()
														.build();
		String pwd = pwdgen.generatePassword(len);
		System.out.println(pwd);
		assertEquals(len, pwd.length());
		assertTrue(pwd.matches("[a-z]+"));
	}
	
	//@Test
	public void testAllCombinations() {
		int len = 10;
		PwdGenerator pwdgen = new PwdGenerator.Builder().useUpper()
														.useLower()
														.useDigit()
														.useSymbol()
														.build();
		String pwd = pwdgen.generatePassword(len);
		System.out.println(pwd);
		assertEquals(len, pwd.length());
	}
	
	//@Test
	public void testUppercase() {
		int len = 16;
		PwdGenerator pwdgen = new PwdGenerator.Builder().useUpper()
														.build();
		String pwd = pwdgen.generatePassword(len);
		System.out.println(pwd);
		assertEquals(len, pwd.length());
		assertTrue(pwd.matches("[A-Z]+"));
	}
	
	//@Test
	public void testWithLen0() {
		int len = 0;
		PwdGenerator pwdgen = new PwdGenerator.Builder().useUpper()
														.useLower()
														.useDigit()
														.useSymbol()
														.build();
		String pwd = pwdgen.generatePassword(len);
		System.out.println(pwd);
		assertEquals("", pwd);
	}

	//@Test
	public void testWithNegativeLen() {
		int len = -4;
		PwdGenerator pwdgen = new PwdGenerator.Builder().useUpper()
														.useLower()
														.useDigit()
														.useSymbol()
														.build();
		String pwd = pwdgen.generatePassword(len);
		System.out.println(pwd);
		assertEquals("", pwd);
	}

	@Test
	public void testFlowManually() {
		
		String msg = "Generate password use:\n"
				+ "[1]: Lowercase\n"
				+ "[2]: Lowercase & Numbers\n"
				+ "[3]: Lowercase, Uppercase & Numbers\n"
				+ "[4]: Lowercase, Uppercase, NUmbers & Symbols\n"
				+ "[5]: stop and exit";
		System.out.println(msg);
		Scanner in = new Scanner(System.in);
		int selection = 0;
		while(true) {
			System.out.println("Please select [1-5]:");
			selection = in.nextInt();
			if (selection > 0 && selection < 5) {
				break;
			}
			else if (selection == 5) {
				System.out.println("Thanks! Exit program now");
				return;
			}
			else {
				System.out.println("Invalid option.");
			}
		}
		PwdGenerator.Builder builder = new PwdGenerator.Builder();
		switch(selection) {
		case 4:
			builder.useSymbol();
		case 3:
			builder.useUpper();
		case 2:
			builder.useDigit();
		case 1:
			builder.useLower();
			break;
		default:
			break;
		}
		int pwdLen = 0;
		while (true) {
			System.out.println("Please provide password length (minimum is 6):");
			pwdLen = in.nextInt();
			if (pwdLen < 6) {
				System.out.println("Too short.");
			}
			else {
				break;
			}
		}
		PwdGenerator pwdgen = builder.build();
		String pwd = pwdgen.generatePassword(pwdLen);
		System.out.println("Password is " + pwd);
	}
}
