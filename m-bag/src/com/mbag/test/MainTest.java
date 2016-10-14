package com.mbag.test;

import com.mbag.data.InData;
import com.mbag.engine.FlowEngine;

public class MainTest {

	public static void main(String[] args) {
		FlowEngine engine = new FlowEngine();
		engine.startFlow("001",new InData());
	}
	
}
