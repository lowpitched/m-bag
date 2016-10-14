package com.mbag.pool;

import java.util.HashMap;
import java.util.Map;

import com.mbag.model.Flow;

public class FlowPool {

	private  Map<String,Flow> flowPool = new HashMap<String,Flow>();
	
	private static FlowPool pool;
	
	private FlowPool(Map<String,Flow> flowPool){
		this.flowPool=flowPool;
	}
	
	public static FlowPool getInstance(Map<String,Flow> flowPool){
		synchronized (FlowPool.class) {
			if(null==pool){
				pool = new FlowPool(flowPool);
			}
			return pool;
		}
	}
	
	public Object addFlow(Flow flow){
		return flowPool.put(flow.getTradeCode(),flow);
	}
	
	public Object removeFlow(Flow flow){
		 return flowPool.remove(flow.getTradeCode());
	}
	
	public Object removeFlow(String tradeCode){
		return flowPool.remove(tradeCode);
	}
	
	public void clear(){
		flowPool.clear();
	}
	
	public FlowPool getFlowPool(){
		return pool;
	}
	
	public Flow getFlow(String tradeCode){
		return flowPool.get(tradeCode);
	}
}
