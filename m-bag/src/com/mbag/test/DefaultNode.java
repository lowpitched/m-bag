package com.mbag.test;

import com.mbag.context.FlowContext;
import com.mbag.data.PassBag;
import com.mbag.publish.INode;

public class DefaultNode implements INode{

	@Override
	public void dowork(PassBag bag, FlowContext context) {
		System.out.println("--------this is the default node------");
		int i = (Integer) context.get("num");
		i++;
		context.set("num", i);
		System.out.println(i+"=========");
		context.breakLoop(i==10);
	}

}
