package com.mbag.data;

import java.util.HashMap;
import java.util.Map;

public class MbagConstant {



	private Map<String,Object> mbagConstant = new HashMap<String,Object>();
	
	public MbagConstant(Map<String,Object> mbagConstant){
		this.mbagConstant = mbagConstant;
	}
	
	public MbagConstant(){
	}

	public void put(String key,Object value){
		mbagConstant.put(key, value);
	}
	
	public int getInt(String key){
		return (Integer) mbagConstant.get(key);
	}
	
	public long getLong(String key){
		return (Long) mbagConstant.get(key);
	}
	
	public float getFloat(String key){
		return (Float) mbagConstant.get(key);
	}
	
	public double getDouble(String key){
		return (Double) mbagConstant.get(key);
	}
	
	public short getShort(String key){
		return (Short) mbagConstant.get(key);
	}
	
	public boolean getBoolean(String key){
		return (Boolean) mbagConstant.get(key);
	}
	
	public String getString(String key){
		return (String) mbagConstant.get(key);
	}
	
	public Object get(String key){
		return mbagConstant.get(key);
	}

	
}
