package com.mbag.test;

import com.mbag.context.FlowContext;
import com.mbag.data.PassBag;
import com.mbag.publish.INode;

public class IfClassNode2 implements INode{

	@Override
	public void dowork(PassBag bag, FlowContext context) {
		System.out.println(bag.get("helloworld"));
		System.out.println(context.get("context"));
		System.out.println("this is the ifclassnode2");
	}

	
	
}
