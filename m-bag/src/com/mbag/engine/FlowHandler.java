package com.mbag.engine;

import com.mbag.context.FlowContext;
import com.mbag.data.InData;
import com.mbag.data.OutData;

public class FlowHandler {

	private FlowEngine flowEngine;
	
	public FlowHandler(FlowEngine flowEngine){
		this.flowEngine = flowEngine;
	}
	
	public void shutdownFlow(FlowContext flowContext){
		flowContext.shutdown();
	}
	
	public OutData dispacher(String tradeCode,InData inData,FlowContext flowContext){
		shutdownFlow(flowContext);
		return flowEngine.startFlow(tradeCode, inData);
	}
	
	public OutData startFlow(String tradeCode,InData inData){
		NewFlow newFlow = new NewFlow(tradeCode,inData);
		newFlow.start();
		return newFlow.getOutData();
	}
	
	class NewFlow extends Thread{
		private String tradeCode;
		private InData inData;
		private OutData outData;
		private NewFlow(String tradeCode,InData inData){
			this.tradeCode=tradeCode;
			this.inData=inData;
		}
		@Override
		public void run() {
			outData = flowEngine.startFlow(tradeCode, inData);
		}
		public OutData getOutData(){
			return outData;
		}
	}
	
}
