package com.mbag.context;

import java.util.HashMap;
import java.util.Map;

import com.mbag.data.FlowConstant;
import com.mbag.data.InData;
import com.mbag.data.OutData;
import com.mbag.engine.FlowHandler;
import com.mbag.model.Flow;

public class FlowContext {
	
	private Flow flow;

	private Map<String,Object> container = new HashMap<String,Object>();
	
	private static FlowContext flowcontainer;
	
	private InData inData;
	
	private OutData outData;
	
	private boolean breakLoop;
	
	private boolean shutdown;
	
	private FlowHandler flowHandler;
	
	private FlowContext(Flow flow,FlowHandler flowHandler){
		this.flow=flow;
		this.flowHandler=flowHandler;
	}
	
	public static FlowContext instance(Flow flow,FlowHandler flowHandler){
		synchronized (FlowContext.class) {
			if(null==flowcontainer){
				flowcontainer = new FlowContext(flow,flowHandler);
			}
			return flowcontainer;
		}
	}

	public void setFlowHandler(FlowHandler flowHandler) {
		this.flowHandler = flowHandler;
	}

	public void setInData(InData inData){
		this.inData=inData;
	}
	
	public InData getInData(){
		return inData;
	}
	
	public void setOutData(OutData outData){
		this.outData = outData;
	}
	
	public OutData getOutData(){
		return outData;
	}
	
	public Object get(String key){
		return container.get(key);
	}
	
	public Object set(String key,Object value){
		return container.put(key, value);
	}
	
	public void clear(){
		container.clear();
	}
	
	public Object remove(String key){
		return container.remove(key);
	}
	
	public boolean isEmpty(){
		return container.isEmpty();
	}
	
	public String getTradeCode(){
		return flow.getTradeCode();
	}
	
	public void breakLoop(boolean condition){
		this.breakLoop = condition;
	}
	
	public boolean isBreakLoop(){
		return breakLoop;
	}
	
	public Object getConstant(String key){
		return flow.getFlowConstant().get(key);
	}
	
	public FlowConstant getConstant(){
		return flow.getFlowConstant();
	}
	
	public void shutdown(){
		this.shutdown=true;
	}
	
	public boolean getShutdown(){
		return this.shutdown;
	}
	
	public OutData dispacher(String tradeCode,InData inData,FlowContext flowContext){
		return flowHandler.dispacher(tradeCode, inData, flowContext);
	}
	
	public OutData startNewFlow(String tradeCode,InData inData){
		return flowHandler.startFlow(tradeCode, inData);
	}
}
