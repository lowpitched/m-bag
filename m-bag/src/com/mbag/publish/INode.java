package com.mbag.publish;

import com.mbag.context.FlowContext;
import com.mbag.data.PassBag;

public interface INode {

	public void dowork(PassBag bag,FlowContext context);
	
}
