package com.mbag.test;

import com.mbag.context.FlowContext;
import com.mbag.data.Loop;
import com.mbag.data.PassBag;
import com.mbag.publish.For;

public class ForTest implements For{

	@Override
	public Loop loop(PassBag bag, FlowContext context) {
		return new Loop(0,10);
	}

}
