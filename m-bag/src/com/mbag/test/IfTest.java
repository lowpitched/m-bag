package com.mbag.test;

import com.mbag.context.FlowContext;
import com.mbag.data.PassBag;
import com.mbag.publish.If;

public class IfTest implements If{

	@Override
	public boolean branch(PassBag bag, FlowContext context) {
		return true;
	}

}
