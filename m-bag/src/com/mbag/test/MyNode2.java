package com.mbag.test;

import com.mbag.context.FlowContext;
import com.mbag.data.PassBag;
import com.mbag.publish.INode;

public class MyNode2 implements INode{

	@Override
	public void dowork(PassBag bag, FlowContext context) {
		System.out.println("----------node2 begin------------");
		System.out.println(bag.get("test"));
		System.out.println(context.get("test2"));
		System.out.println(context.getTradeCode());
		System.out.println(bag.get("helloworld"));
		System.out.println(context.get("context"));
		System.out.println("----------node2 end------------");
		context.shutdown();
	}

}
