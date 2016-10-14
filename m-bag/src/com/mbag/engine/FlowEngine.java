package com.mbag.engine;

import java.util.List;

import com.mbag.context.DefaultConfigration;
import com.mbag.context.FlowContext;
import com.mbag.context.MbagContext;
import com.mbag.data.Breaker;
import com.mbag.data.InData;
import com.mbag.data.Loop;
import com.mbag.data.OutData;
import com.mbag.data.PassBag;
import com.mbag.log.LogTools;
import com.mbag.model.Flow;
import com.mbag.model.Node;
import com.mbag.publish.For;
import com.mbag.publish.INode;
import com.mbag.publish.If;
import com.mbag.publish.While;

public class FlowEngine{
	
		private MbagContext mbagContext;
		
		private FlowContext flowContext;
		
		private PassBag bag = new PassBag();
	
		{
			if(null==mbagContext){
				mbagContext = DefaultConfigration.config();
			}
		}
		
		public OutData startFlow(String tradeCode,InData inData){
		Flow flow = mbagContext.getFlowPool().getFlow(tradeCode);
		flowContext = FlowContext.instance(flow,new FlowHandler(this));
		if(!flow.isUse()) return null;
		List<Node> nodes = flow.getNodes();
		for(Node node:nodes){
			try {
				goNode(node);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return flowContext.getOutData();
	}
	
	private void goNode(Node node) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		int nodeType = node.getNodeType();
		if(nodeType==Node.DEFAULT_NODE){
			node.setCurrent(true);
			goDefaultNode(node);
			node.setCurrent(false);
			node.setGo(true);
		}else if(nodeType==Node.IF_NODE){
			node.setCurrent(true);
			goIfNode(node);
			node.setCurrent(false);
			node.setGo(true);
		}else if(nodeType==Node.FOR_NODE){
			node.setCurrent(true);
			goForNode(node);
			node.setCurrent(false);
			node.setGo(true);
		}else if(nodeType==Node.WHILE_NODE){
			node.setCurrent(true);
			goWhileNode(node);
			node.setCurrent(false);
			node.setGo(true);
		}else if(nodeType==Node.THREAD_NODE){
			node.setCurrent(true);
			goThreadNode(node);
			node.setCurrent(false);
			node.setGo(true);
		}
	}

	private void goDefaultNode(Node node) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		if(isShutdown()) return;
		String clazzName = node.getClazzName();
		LogTools.i(this, "["+clazzName+"]--启动");
		Class<?> clazz = Class.forName(clazzName);
		INode inode = (INode)clazz.newInstance();
		inode.dowork(bag, flowContext);
		LogTools.i(this, "["+clazzName+"]--结束");
	}
	
	private void goIfNode(Node node) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(isShutdown()) return;
		String clazzName = node.getClazzName();
		Class<?> clazz = Class.forName(clazzName);
		If _if = (If) clazz.newInstance();
		boolean result = _if.branch(bag, flowContext);
		LogTools.i(this, "["+clazzName+"]--("+result+")启动");
		if(result){
			List<Node> nodes = node.getNodes();
			for(Node n:nodes){
				goNode(n);
			}
		}
		LogTools.i(this, "["+clazzName+"]--结束");
	}
	
	private void goForNode(Node node) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(isShutdown()) return;
		String clazzName = node.getClazzName();
		Class<?> clazz = Class.forName(clazzName);
		LogTools.i(this, "["+clazzName+"]--启动");
		For _for = (For) clazz.newInstance();
		Loop loop = _for.loop(bag, flowContext);
		int start = loop.getStart();
		int end = loop.getEnd();
		boolean breakFlag = false;
		for(int i=start;i<=end;i++){
			List<Node> nodes = node.getNodes();
			for(Node n:nodes){
				goNode(n);
				breakFlag = flowContext.isBreakLoop();
				if(breakFlag){
					flowContext.breakLoop(false);
					break;
				}
			}
			if(breakFlag) 
				break;
			
		}
		LogTools.i(this, "["+clazzName+"]--结束");
	}
	
	private void goWhileNode(Node node) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		if(isShutdown()) return;
		String clazzName = node.getClazzName();
		Class<?> clazz = Class.forName(clazzName);
		LogTools.i(this, "["+clazzName+"]--启动");
		While _while = (While) clazz.newInstance();
		Breaker result = _while.dowhile(bag, flowContext);
		boolean breakFlag = false;
		while(result.getCondition()){
			List<Node> nodes = node.getNodes();
			for(Node n:nodes){
				goNode(n);
				breakFlag = flowContext.isBreakLoop();
				if(breakFlag){
					flowContext.breakLoop(false);
					break;
				}
			}
			if(breakFlag) 
				break;
		}
		LogTools.i(this, "["+clazzName+"]--结束");
	}

	private void goThreadNode(Node node){
		if(isShutdown()) return;
		int threadNum = node.getPool();
		for(int i=0;i<threadNum;i++){
			MThread thread = new MThread(node);
			thread.start();
		}
	}
	
	class MThread extends Thread{
		
		private Node node;
		
		public MThread(Node node){
			this.node = node;
		}
		
		@Override
		public void run() {
			List<Node> nodes = node.getNodes();
			try {
				for(Node n:nodes){
						goNode(n);
				}
			} catch (ClassNotFoundException e) {
				LogTools.e(MThread.class, e.getMessage());
			} catch (InstantiationException e) {
				LogTools.e(MThread.class, e.getMessage());
			} catch (IllegalAccessException e) {
				LogTools.e(MThread.class, e.getMessage());
			}
		}
	}
	
	private boolean isShutdown(){
		return flowContext.getShutdown();
	}
	
	
}
