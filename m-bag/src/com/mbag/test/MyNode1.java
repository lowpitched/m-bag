package com.mbag.test;

import com.mbag.context.FlowContext;
import com.mbag.data.PassBag;
import com.mbag.publish.INode;

public class MyNode1 implements INode{

	@Override
	public void dowork(PassBag bag, FlowContext context) {
		System.out.println("----------node1 begin------------");
		bag.set("test", 10);
		context.set("test2", 100);
		System.out.println(context.getConstant("name"));
		System.out.println("----------node1 end------------");
		context.set("num", 0);
	}

}
