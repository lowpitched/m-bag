package com.mbag.data;

import java.util.HashMap;
import java.util.Map;

public class PassBag {

	private Map<String,Object> bag=new HashMap<String,Object>();
	
	public Object get(String key){
		return bag.get(key);
	}
	
	public Object set(String key,Object value){
		return bag.put(key, value);
	}
	
	public Object remove(String key){
		return bag.remove(key);
	}
	
	public void clear(){
		bag.clear();
	}
	
	public boolean isEmpty(){
		return bag.isEmpty();
	}
	
	public int size(){
		return bag.size();
	}
}
