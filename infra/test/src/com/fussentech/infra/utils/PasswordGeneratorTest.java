package com.fussentech.infra.utils;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class PasswordGeneratorTest {

	@Test
	public void testDigitWithPositiveLen() {
		int len = 15;
		PasswordGenerator pswdgen = new PasswordGenerator.Builder().useDigit()
														.build();
		String pswd = pswdgen.generatePassword(len);
		System.out.println(pswd);
		assertEquals(len, pswd.length());
		assertTrue(pswd.matches("[0-9]+"));
	}
	
	@Test
	public void testLowercase() {
		int len = 8;
		PasswordGenerator pswdgen = new PasswordGenerator.Builder().useLower()
														.build();
		String pswd = pswdgen.generatePassword(len);
		System.out.println(pswd);
		assertEquals(len, pswd.length());
		assertTrue(pswd.matches("[a-z]+"));
	}
	
	@Test
	public void testAllCombinations() {
		int len = 10;
		PasswordGenerator pswdgen = new PassworddGenerator.Builder().useUpper()
														.useLower()
														.useDigit()
														.useSymbol()
														.build();
		String pswd = pswdgen.generatePassword(len);
		System.out.println(pswd);
		assertEquals(len, pswd.length());
	}
	
	@Test
	public void testUppercase() {
		int len = 16;
		PasswordGenerator pswdgen = new PasswordGenerator.Builder().useUpper()
														.build();
		String pswd = pswdgen.generatePassword(len);
		System.out.println(pswd);
		assertEquals(len, pswd.length());
		assertTrue(pswd.matches("[A-Z]+"));
	}
	
	@Test
	public void testWithLen0() {
		int len = 0;
		PasswordGenerator pswdgen = new PasswordGenerator.Builder().useUpper()
														.useLower()
														.useDigit()
														.useSymbol()
														.build();
		String pswd = pswdgen.generatePassword(len);
		System.out.println(pswd);
		assertEquals("", pswd);
	}

	@Test
	public void testWithNegativeLen() {
		int len = -4;
		PasswordGenerator pswdgen = new PasswordGenerator.Builder().useUpper()
														.useLower()
														.useDigit()
														.useSymbol()
														.build();
		String pswd = pswdgen.generatePassword(len);
		System.out.println(pswd);
		assertEquals("", pswd);
	}

	//@Test
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
		PasswordGenerator.Builder builder = new PasswordGenerator.Builder();
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
		int pswdLen = 0;
		while (true) {
			System.out.println("Please provide password length (minimum is 6):");
			pswdLen = in.nextInt();
			if (pswdLen < 6) {
				System.out.println("Too short.");
			}
			else {
				break;
			}
		}
		PwdGenerator pswdgen = builder.build();
		String pswd = pswdgen.generatePassword(pswdLen);
		System.out.println("Password is " + pswd);
	}
}
