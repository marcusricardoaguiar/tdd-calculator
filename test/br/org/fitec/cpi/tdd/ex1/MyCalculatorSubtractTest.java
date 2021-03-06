//**********************************************************************
// Copyright (c) 2017 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************
package br.org.fitec.cpi.tdd.ex1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyCalculatorSubtractTest {

	Calculator myCalculator;

	@Before
	public void setUp() throws Exception {
		myCalculator = new MyCalculator();
	}

	@After
	public void tearDown() throws Exception {
		myCalculator = null;
	}

	@Test
	public void testSubTwoNumbersSuccess() throws NegativeNumberException {
		String s = "2,1";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(1, result);
	}

	@Test
	public void testSubOneNumberSuccess() throws NegativeNumberException {
		String s = "1";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(1, result);

	}

	@Test
	public void testSubNoNumberSuccess() throws NegativeNumberException {
		String s = "";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(0, result);

		result = myCalculator.subtract(null);
		Assert.assertEquals(0, result);
	}

	@Test
	public void testAnyNumberSuccess() throws NegativeNumberException {
		String s = "1,2,3,4,5,6,7,8,9,0";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(-43, result);
	}

	@Test
	public void testNegativeNumberShouldFailException() throws NegativeNumberException {
		String s = "-1";
		try {
			myCalculator.subtract(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -1 ", msg);
		}
	}

	@Test
	public void testNegativeNumber2ShouldFailException() throws NegativeNumberException {
		String s = "3,-1";
		try {
			myCalculator.subtract(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -1 ", msg);
		}
	}

	@Test
	public void testNegativeNumber3ShouldFailException() throws NegativeNumberException {
		String s = " -3,-1";
		try {
			myCalculator.subtract(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -3 -1 ", msg);
		}
	}

	@Test
	public void testNegativeNumber4ShouldFailException() throws NegativeNumberException {
		String s = "-2, -1";
		try {
			myCalculator.subtract(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -2 -1 ", msg);
		}
	}

	@Test
	public void testNumberBiggerThan1000ShouldSuccess() throws NegativeNumberException {
		String s = "2, 3, 1001";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testNumberBiggerThan1000_2ShouldSuccess() throws NegativeNumberException {
		String s = "1003, 2001, 2, 3, 1001";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testNegativeNumberBiggerThan1000ShouldFailException() throws NegativeNumberException {
		String s = "-2, 1001";
		try {
			myCalculator.subtract(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -2 ", msg);
		}
	}
}
