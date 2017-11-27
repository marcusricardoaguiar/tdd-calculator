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

public interface Calculator {

	public int generic(String s) throws NegativeNumberException;

	public int add(String s) throws NegativeNumberException;

	public int subtract(String s) throws NegativeNumberException;

	public double multiply(String s) throws NegativeNumberException;

	public double divide(String s) throws ArithmeticException, NegativeNumberException;
}
