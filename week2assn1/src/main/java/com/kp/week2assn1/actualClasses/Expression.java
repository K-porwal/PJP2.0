package com.kp.week2assn1.actualClasses;

public class Expression {
	private String expression;
	private String result;
	
	public Expression(String expression) {
		super();
		this.expression = expression;
	}
	
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return expression + " = " + result;
	}
	
}