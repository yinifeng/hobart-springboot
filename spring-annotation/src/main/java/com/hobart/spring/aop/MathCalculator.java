package com.hobart.spring.aop;

/**
 * 数字计算器
 * 
 * @author hobart
 *
 */
public class MathCalculator {
	public int div(int x, int y) {
		System.out.println("--==MathCalculator...div...");
		return x / y;
	}
}
