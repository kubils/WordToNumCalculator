package com.ks.calculatoruser;


public class CalculatorServiceImp implements CalculatorService {

	@Override
	public int add(int a, int b) {
		// TODO Auto-generated method stub
		return a + b;
	}

	@Override
	public int minus(int a, int b) {
		// TODO Auto-generated method stub
		return a - b;
	}

	@Override
	public int multiply(int a, int b) {
		// TODO Auto-generated method stub
		return a * b;
	}

	@Override
	public int division(int a, int b) {
		// TODO Auto-generated method stub
		try {
			return a / b;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return 1;
	}

}
