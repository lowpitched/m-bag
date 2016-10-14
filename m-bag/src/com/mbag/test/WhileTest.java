package com.mbag.test;

import com.mbag.context.FlowContext;
import com.mbag.data.Breaker;
import com.mbag.data.PassBag;
import com.mbag.publish.While;

public class WhileTest implements While{

	@Override
	public Breaker dowhile(PassBag bag, FlowContext context) {
		
		return new Breaker(true);
	}

	
	
}
