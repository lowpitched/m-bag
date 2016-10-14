package com.mbag.publish;

import com.mbag.context.FlowContext;
import com.mbag.data.PassBag;

public interface If {

	boolean branch(PassBag bag,FlowContext context);
	
}
