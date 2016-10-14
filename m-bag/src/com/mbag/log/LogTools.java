package com.mbag.log;

import org.apache.log4j.Logger;

@SuppressWarnings("unchecked")
public class LogTools {

	public static void i(Class target,String message,Throwable t){
		Logger log = Logger.getLogger(target);
		if(log.isInfoEnabled()){
			log.info(message, t);
		}
	}
	
	public static void i(Class target,String message){
		i(target,message,null);
	}
	
	public static void i(Object obj,String message,Throwable t){
		i(obj.getClass(),message,t);
	}
	
	public static void i(Object obj,String message){
		i(obj.getClass(),message);
	}
	
	public static void d(Class target,String message, Throwable t){
		Logger log = Logger.getLogger(target);
		if(log.isDebugEnabled()){
			log.debug(message, t);
		}
	}
	
	public static void d(Class target,String message){
		d(target,message,null);
	}
	
	public static void d(Object obj,String message,Throwable t){
		d(obj.getClass(),message,t);
	}
	
	public static void d(Object obj,String message){
		d(obj.getClass(),message);
	}
	
	public static void e(Class target,String message,Throwable t){
		Logger log = Logger.getLogger(target);
		log.error(message, t);
	}
	
	public static void e(Class target,String message){
		e(target,message,null);
	}
	
	public static void e(Object obj,String message,Throwable t){
		e(obj.getClass(),message,t);
	}
	
	public static void e(Object obj,String message){
		e(obj.getClass(),message);
	}
	
	public static void w(Class target,String message,Throwable t){
		Logger log = Logger.getLogger(target);
		log.warn(message, t);
	}
	
	public static void w(Class target,String message){
		w(target,message,null);
	}
	
	public static void w(Object obj,String message,Throwable t){
		w(obj.getClass(),message,t);
	}
	
	public static void w(Object obj,String message){
		w(obj.getClass(),message);
	}
	
	public static void f(Class target,String message,Throwable t){
		Logger log = Logger.getLogger(target);
		log.fatal(message, t);
	}
	
	public static void f(Class target,String message){
		f(target,message,null);
	}
	
	public static void f(Object obj,String message,Throwable t){
		f(obj.getClass(),message,t);
	}
	
	public static void f(Object obj,String message){
		f(obj.getClass(),message);
	}
}
