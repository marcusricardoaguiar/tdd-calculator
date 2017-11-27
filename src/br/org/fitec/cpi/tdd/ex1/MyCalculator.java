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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MyCalculator implements Calculator {

	private enum OPERATIONS {
		ADD, SUB, MUL, DIV
	}

	/**
	 * Description: Generic operation
	 */
	private Number operation(String s, OPERATIONS operation) throws NegativeNumberException {
		if (s == null || s.length() == 0) {
			return 0;
		}
		String[] values = s.split(",");

		Number result = Stream.of(values).map(parseIntMap()).filter(filterAllowedNumbers).map(num -> (Number) num).reduce((a, b) -> {
			switch (operation) {
			case ADD:
				return addOperation(a, b);
			case SUB:
				return subOperation(a, b);
			case MUL:
				return multOperation(a, b);
			case DIV:
				return divOperation(a, b);
			default:
				return (Number) 0.0;
			}
		}).orElse(0.0);

		verifyNegatives(values);
		return result;
	}

	@Override
	public int add(String s) throws NegativeNumberException {

		Number result = this.operation(s, OPERATIONS.ADD);
		return result.intValue();
	}

	@Override
	public int subtract(String s) throws NegativeNumberException {

		Number result = this.operation(s, OPERATIONS.SUB);
		return result.intValue();
	}

	@Override
	public double multiply(String s) throws NegativeNumberException {

		Number result = this.operation(s, OPERATIONS.MUL);
		BigDecimal finalValue = BigDecimal.valueOf(result.doubleValue()).setScale(1, RoundingMode.HALF_DOWN);
		return finalValue.doubleValue();
	}

	@Override
	public double divide(String s) throws NegativeNumberException, ArithmeticException {
		Number result = this.operation(s, OPERATIONS.DIV);

		if (Double.isInfinite(result.doubleValue()))
			throw new ArithmeticException("division by zero");

		BigDecimal finalValue = BigDecimal.valueOf(result.doubleValue()).setScale(1, RoundingMode.HALF_DOWN);
		return finalValue.doubleValue();
	}

	/**
	 * Description: Filter used to define the allowedNumbers
	 */
	private static final Predicate<Integer> filterAllowedNumbers = num -> num <= 1000 && num >= 0;

	/**
	 * Description: Generic function to trim and parseInt values
	 */
	private static Function<String, Integer> parseIntMap() {
		return value -> Integer.parseInt(value.trim());
	}

	/**
	 * Description: Operations to reduce
	 */

	public static Number addOperation(Number a, Number b) {
		return a.intValue() + b.intValue();
	}

	public static Number subOperation(Number a, Number b) {
		return a.intValue() - b.intValue();
	}

	public static Number multOperation(Number a, Number b) {
		return a.doubleValue() * b.doubleValue();
	}

	public static Number divOperation(Number a, Number b) {
		return a.doubleValue() / b.doubleValue();
	}

	/**
	 * Description: Verify if input has negative numbers
	 */
	private void verifyNegatives(String[] values) throws NegativeNumberException {
		StringBuilder builder = new StringBuilder();
		Stream.of(values).map(value -> Integer.parseInt(value.trim())).filter(num -> num < 0).forEach(it -> builder.append(it + " "));

		if (builder.length() > 0) {
			throw new NegativeNumberException("negatives not allowed: " + builder.toString());
		}
	}
}
