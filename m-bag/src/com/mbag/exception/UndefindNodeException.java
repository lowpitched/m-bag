package com.mbag.exception;

public class UndefindNodeException extends Exception{

	private static final long serialVersionUID = 1L;

	public UndefindNodeException(Throwable throwable){
		super(throwable);
	}
	
	public UndefindNodeException(String info){
		super(info);
	}
	
	public UndefindNodeException(Throwable throwable,String info){
		super(info,throwable);
	}
		
}
