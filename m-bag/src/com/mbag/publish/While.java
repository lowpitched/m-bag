package com.mbag.publish;

import com.mbag.context.FlowContext;
import com.mbag.data.Breaker;
import com.mbag.data.PassBag;

public interface While {

	Breaker dowhile(PassBag bag,FlowContext context);
	
}
