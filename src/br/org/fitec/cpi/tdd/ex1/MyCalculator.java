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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

public class MyCalculator implements Calculator {

	@Override
	public int add(String s) throws NegativeNumberException {
		if (s == null || s.length() == 0) {
			return 0;
		}
		String[] values = s.split(",");

		int sum = Stream.of(values).map(value -> Integer.parseInt(value.trim())).filter(num -> num <= 1000 && num >= 0).mapToInt(Integer::intValue).sum();

		verifyNegatives(values);
		return sum;
	}

	@Override
	public int subtract(String s) throws NegativeNumberException {
		if (s == null || s.length() == 0) {
			return 0;
		}
		String[] values = s.split(",");

		int sub = Stream.of(values).map(value -> Integer.parseInt(value.trim())).filter(num -> num <= 1000 && num >= 0).mapToInt(Integer::intValue).reduce((a, b) -> a - b).orElse(0);

		verifyNegatives(values);
		return sub;
	}

	@Override
	public double multiply(String s) throws NegativeNumberException {
		if (s == null || s.length() == 0) {
			return 0;
		}
		String[] values = s.split(",");

		double mult = Stream.of(values).map(value -> Double.parseDouble(value.trim())).filter(num -> num <= 1000 && num >= 0).mapToDouble(Double::doubleValue).reduce(1, (a, b) -> a * b);

		verifyNegatives(values);

		BigDecimal finalValue = BigDecimal.valueOf(mult).setScale(1, RoundingMode.HALF_DOWN);
		return finalValue.doubleValue();
	}

	@Override
	public double divide(String s) throws NegativeNumberException, ArithmeticException {
		if (s == null || s.length() == 0) {
			return 0;
		}
		String[] values = s.split(",");

		double div = Stream.of(values).map(value -> Double.parseDouble(value.trim())).filter(num -> num <= 1000 && num >= 0).mapToDouble(Double::doubleValue).reduce((a, b) -> a / b).orElse(0.0);

		if (Double.isInfinite(div))
			throw new ArithmeticException("division by zero");

		verifyNegatives(values);

		BigDecimal finalValue = BigDecimal.valueOf(div).setScale(1, RoundingMode.HALF_DOWN);
		return finalValue.doubleValue();
	}

	/**
	 * @param StringBuilder
	 *            Description: Verify if input has negative numbers
	 */
	private void verifyNegatives(String[] values) throws NegativeNumberException {
		StringBuilder builder = new StringBuilder();
		Stream.of(values)
			.map(value -> Integer.parseInt(value.trim()))
			.filter(num -> num < 0)
			.forEach(it -> builder.append(it + " "));
		
		if (builder.length() > 0) {
			throw new NegativeNumberException("negatives not allowed: " + builder.toString());
		}
	}
}
