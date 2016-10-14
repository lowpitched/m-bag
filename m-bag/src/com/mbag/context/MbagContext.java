package com.mbag.context;

import java.util.Map;

import com.mbag.data.MbagConstant;
import com.mbag.pool.FlowPool;

/**
 * mbag上下文可获取bog所有流程及其信息
 * @author acer
 *
 */
public class MbagContext {
	/**所有流程缓存*/
	private FlowPool flowPool;
	
	private Map<String,Object> context;
	
	private static MbagContext mbagContext;
	
	private MbagConstant mbagConstant =  new MbagConstant();
	
	private MbagContext(FlowPool flowPool){
		this.flowPool = flowPool;
	}
	
	public static MbagContext getInstance(FlowPool flowPool){
		synchronized (MbagContext.class) {
			if(null==mbagContext){
				mbagContext=new MbagContext(flowPool);
			}
		}
		return mbagContext;
	}
	
	public void setMbagConstant(MbagConstant mbagConstant){
		this.mbagConstant = mbagConstant;
	}
	
	public MbagConstant getMbagConstant(){
		return mbagConstant;
	}
	
	public Object getConstant(String key){
		return mbagConstant.get(key);
	}
	
	public Object get(String key){
		return context.get(key);
	}
	
	public FlowPool getFlowPool() {
		return flowPool;
	}

	public Object set(String key,Object value){
		return context.put(key, value);
	}
	
	public void clear(){
		context.clear();
	}
	
	public Object remove(String key){
		return context.remove(key);
	}
	
	public boolean isEmpty(){
		return context.isEmpty();
	}
	
	
}
