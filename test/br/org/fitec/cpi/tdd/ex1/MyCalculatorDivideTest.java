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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * testDivTwoNumbersSuccess
 * testDivOneNumberSuccess
 * testDivNoNumberSuccess
 * testAnyNumberSuccess
 * testDivideByZeroNumberFailException
 * testDivideFirstZeroNumberSuccess
 * testNegativeNumberShouldFailException
 * testNegativeNumber2ShouldFailException
 * testNegativeNumber3ShouldFailException
 * testNegativeNumber4ShouldFailException
 * testNumberBiggerThan1000ShouldSuccess
 * testNegativeNumberBiggerThan1000ShouldFailException
 */

public class MyCalculatorDivideTest {

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
	public void testDivTwoNumbersSuccess() throws NegativeNumberException {
		String s = "2,1";
		double result = myCalculator.divide(s);
		Assert.assertThat(result, is(equalTo((double) 2)));
	}

	@Test
	public void testDivOneNumberSuccess() throws NegativeNumberException {
		String s = "1";
		double result = myCalculator.divide(s);
		Assert.assertThat(result, is(equalTo((double) 1)));

	}

	@Test
	public void testDivNoNumberSuccess() throws NegativeNumberException {
		String s = "";
		double result = myCalculator.divide(s);
		Assert.assertThat(result, is(equalTo((double) 0)));

		result = myCalculator.divide(null);
		Assert.assertThat(result, is(equalTo((double) 0)));
	}

	@Test
	public void testAnyNumberSuccess() throws NegativeNumberException {
		String s = "3,2,1";
		double result = myCalculator.divide(s);
		Assert.assertThat(result, is(equalTo(1.5)));
	}

	@Test
	public void testDivideByZeroNumberFailException() throws NegativeNumberException, ArithmeticException {
		String s = "1,2,3,4,5,6,7,8,9,0";
		try {
			myCalculator.divide(s);
		} catch (ArithmeticException e) {
			String msg = e.getMessage();
			Assert.assertEquals("division by zero", msg);
		}
	}

	@Test
	public void testDivideFirstZeroNumberSuccess() throws NegativeNumberException, ArithmeticException {
		String s = "0,3,5,6";

		double result = myCalculator.divide(s);
		Assert.assertThat(result, is(equalTo(0.0)));
	}

	@Test
	public void testNegativeNumberShouldFailException() throws NegativeNumberException {
		String s = "-1";
		try {
			myCalculator.divide(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -1 ", msg);
		}
	}

	@Test
	public void testNegativeNumber2ShouldFailException() throws NegativeNumberException {
		String s = "3,-1";
		try {
			myCalculator.divide(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -1 ", msg);
		}
	}

	@Test
	public void testNegativeNumber3ShouldFailException() throws NegativeNumberException {
		String s = " -3,-1";
		try {
			myCalculator.divide(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -3 -1 ", msg);
		}
	}

	@Test
	public void testNegativeNumber4ShouldFailException() throws NegativeNumberException {
		String s = "-2, -1";
		try {
			myCalculator.divide(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -2 -1 ", msg);
		}
	}

	@Test
	public void testNumberBiggerThan1000ShouldSuccess() throws NegativeNumberException {
		String s = "2, 3, 1001";
		double result = myCalculator.divide(s);
		Assert.assertThat(result, is(equalTo(0.7)));
	}

	@Test
	public void testNegativeNumberBiggerThan1000ShouldFailException() throws NegativeNumberException {
		String s = "-2, 1001";
		try {
			myCalculator.divide(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -2 ", msg);
		}
	}
}
