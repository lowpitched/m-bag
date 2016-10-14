package com.mbag.data;

import java.util.HashMap;
import java.util.Map;

public class FlowConstant {

	private Map<String,Object> flowConstants = new HashMap<String,Object>();
	
	public FlowConstant(Map<String,Object> flowConstants){
		this.flowConstants = flowConstants;
	}
	
	public FlowConstant(){
	}

	public void put(String key,Object value){
		flowConstants.put(key, value);
	}
	
	public int getInt(String key){
		return (Integer) flowConstants.get(key);
	}
	
	public long getLong(String key){
		return (Long) flowConstants.get(key);
	}
	
	public float getFloat(String key){
		return (Float) flowConstants.get(key);
	}
	
	public double getDouble(String key){
		return (Double) flowConstants.get(key);
	}
	
	public short getShort(String key){
		return (Short) flowConstants.get(key);
	}
	
	public boolean getBoolean(String key){
		return (Boolean) flowConstants.get(key);
	}
	
	public String getString(String key){
		return (String) flowConstants.get(key);
	}
	
	public Object get(String key){
		return flowConstants.get(key);
	}
}
