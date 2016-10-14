package com.mbag.publish;

import com.mbag.context.FlowContext;
import com.mbag.data.Loop;
import com.mbag.data.PassBag;

public interface For {

	
	Loop loop(PassBag bag,FlowContext context);
	
}
