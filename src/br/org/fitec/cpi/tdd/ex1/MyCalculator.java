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
		int sum = 0;

		StringBuilder builder = new StringBuilder();
		Stream.of(values).map(value -> Integer.parseInt(value.trim())).filter(num -> num <= 1000).forEach(it -> {
			if (it < 0)
				builder.append(it + " ");
			else
				sum += it;
		});

		verifyNegatives(builder);
		return sum;
	}

	@Override
	public int subtract(String s) throws NegativeNumberException {
		if (s == null || s.length() == 0) {
			return 0;
		}
		String[] values = s.split(",");
		int sub = 0;
		int num = 0;
		// convert to java8
		StringBuilder builder = new StringBuilder();
		for (String value : values) {
			num = Integer.parseInt(value.trim());
			if (num < 0) {
				builder.append(num + " ");
			} else if (num <= 1000) {
				if (sub == 0)
					sub = num;
				else
					sub -= num;
			}
		}
		verifyNegatives(builder);
		return sub;
	}

	@Override
	public double multiply(String s) throws NegativeNumberException {
		if (s == null || s.length() == 0) {
			return 0;
		}
		String[] values = s.split(",");
		double mult = 0;
		double num = 0;
		// convert to java8
		StringBuilder builder = new StringBuilder();
		for (String value : values) {
			num = Float.parseFloat(value.trim());
			if (num < 0) {
				builder.append(num + " ");
			} else if (num <= 1000) {
				if (mult == 0) {
					mult = num;
				} else {
					mult *= num;
				}
			}
		}
		verifyNegatives(builder);
		BigDecimal finalValue = BigDecimal.valueOf(mult).setScale(1, RoundingMode.HALF_DOWN);

		return finalValue.doubleValue();
	}

	@Override
	public double divide(String s) throws NegativeNumberException, ArithmeticException {
		if (s == null || s.length() == 0) {
			return 0;
		}
		String[] values = s.split(",");
		double div = 0;
		float num = 0;
		// convert to java8
		StringBuilder builder = new StringBuilder();
		for (String value : values) {
			num = Float.parseFloat(value.trim());
			if (num < 0) {
				builder.append(num + " ");
			} else if (num <= 1000) {
				if (div == 0) {
					div = num;
				} else {
					if (num == 0) {
						throw new ArithmeticException("division by zero");
					}
					div /= num;
				}
			}
		}
		verifyNegatives(builder);
		BigDecimal finalValue = BigDecimal.valueOf(div).setScale(1, RoundingMode.HALF_DOWN);

		return finalValue.doubleValue();
	}

	/**
	 * @param StringBuilder
	 *            Description: Verify if input has negative numbers
	 */
	private void verifyNegatives(StringBuilder builder) throws NegativeNumberException {
		if (builder.length() > 0) {
			throw new NegativeNumberException("negatives not allowed: " + builder.toString());
		}
	}
}
