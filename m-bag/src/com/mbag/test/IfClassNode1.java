package com.mbag.test;

import com.mbag.context.FlowContext;
import com.mbag.data.PassBag;
import com.mbag.publish.INode;

public class IfClassNode1 implements INode{

	@Override
	public void dowork(PassBag bag, FlowContext context) {
		System.out.println("this is ifclassNode1");
		bag.set("helloworld", "helloworld");
		context.set("context", "context");
		System.out.println(bag.get("test"));
	}

	
	
}
