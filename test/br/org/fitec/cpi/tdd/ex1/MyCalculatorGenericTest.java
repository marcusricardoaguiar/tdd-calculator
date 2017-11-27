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

public class MyCalculatorGenericTest {

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
	public void testGenericRequest() throws NegativeNumberException {
		String s = "1,2";
		int result = myCalculator.generic(s);
		Assert.assertEquals(0, result);
	}
}
