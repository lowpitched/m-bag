package com.mbag.context;

import com.mbag.pool.FlowPool;

public abstract class Configration {

	private FlowPool pool;
	
	protected Configration(FlowPool pool){
		this.pool=pool;
	}
	
	public FlowPool getFlowPool(){
		return pool;
	}
	
}
