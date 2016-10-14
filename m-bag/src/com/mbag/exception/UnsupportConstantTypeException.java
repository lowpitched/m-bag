package com.mbag.exception;

public class UnsupportConstantTypeException extends Exception{

	private static final long serialVersionUID = 1L;

	
	public UnsupportConstantTypeException(Throwable throwable){
		super(throwable);
	}
	
	public UnsupportConstantTypeException(String info){
		super(info);
	}
	
	public UnsupportConstantTypeException(Throwable throwable,String info){
		super(info,throwable);
	}
}
