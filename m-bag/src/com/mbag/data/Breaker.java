package com.mbag.data;

public class Breaker {

	private boolean condition;
	
	public Breaker(boolean condition){
		this.condition = condition;
	}
	
	public boolean getCondition(){
		return condition;
	}
}
