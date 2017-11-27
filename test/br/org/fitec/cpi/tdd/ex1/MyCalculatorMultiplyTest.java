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

public class MyCalculatorMultiplyTest {

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
	public void testMultTwoNumbersSuccess() throws NegativeNumberException {
		String s = "2,1";
		double result = myCalculator.multiply(s);
		Assert.assertThat(result, is(equalTo((double) 2)));
	}

	@Test
	public void testMultOneNumberSuccess() throws NegativeNumberException {
		String s = "1";
		double result = myCalculator.multiply(s);
		Assert.assertThat(result, is(equalTo((double) 1)));

	}

	@Test
	public void testMultNoNumberSuccess() throws NegativeNumberException {
		String s = "";
		double result = myCalculator.multiply(s);
		Assert.assertThat(result, is(equalTo((double) 0)));

		result = myCalculator.multiply(null);
		Assert.assertThat(result, is(equalTo((double) 0)));
	}

	@Test
	public void testAnyNumberSuccess() throws NegativeNumberException {
		String s = "1,2,3,4,5,6,7,8,9";
		double result = myCalculator.multiply(s);
		Assert.assertThat(result, is(equalTo((double) 362880)));
	}

	@Test
	public void testAnyNumber2Success() throws NegativeNumberException {
		String s = "1,2,3,4,5,6,7,8,9,0";
		double result = myCalculator.multiply(s);
		Assert.assertThat(result, is(equalTo((double) 0)));
	}

	@Test
	public void testNegativeNumberShouldFailException() throws NegativeNumberException {
		String s = "-1";
		try {
			myCalculator.multiply(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -1 ", msg);
		}
	}

	@Test
	public void testNegativeNumber2ShouldFailException() throws NegativeNumberException {
		String s = "3,-1";
		try {
			myCalculator.multiply(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -1 ", msg);
		}
	}

	@Test
	public void testNegativeNumber3ShouldFailException() throws NegativeNumberException {
		String s = " -3,-1";
		try {
			myCalculator.multiply(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -3 -1 ", msg);
		}
	}

	@Test
	public void testNegativeNumber4ShouldFailException() throws NegativeNumberException {
		String s = "-2, -1";
		try {
			myCalculator.multiply(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -2 -1 ", msg);
		}
	}

	@Test
	public void testNumberBiggerThan1000ShouldSuccess() throws NegativeNumberException {
		String s = "2, 3, 1001";
		double result = myCalculator.multiply(s);
		Assert.assertThat(result, is(equalTo((double) 6)));
	}

	@Test
	public void testNegativeNumberBiggerThan1000ShouldFailException() throws NegativeNumberException {
		String s = "-2, 1001";
		try {
			myCalculator.multiply(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("negatives not allowed: -2 ", msg);
		}
	}
}
