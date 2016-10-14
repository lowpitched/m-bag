package com.mbag.util;

import org.dom4j.Element;

import com.mbag.exception.UndefindNodeException;
import com.mbag.model.Node;

public class NodeUtils {

	private NodeUtils(){}
	
	public static int getNodeType(String nodeType) throws UndefindNodeException{
		if(com.mbag.model.Node.NODE.equals(nodeType)){
			return com.mbag.model.Node.DEFAULT_NODE;
		}else if(com.mbag.model.Node.IF.equals(nodeType)){
			return com.mbag.model.Node.IF_NODE;
		}else if(com.mbag.model.Node.FOR.equals(nodeType)){
			return com.mbag.model.Node.FOR_NODE;
		}else if(com.mbag.model.Node.WHILE.equals(nodeType)){
			return com.mbag.model.Node.WHILE_NODE;
		}else if(com.mbag.model.Node.THREAD.equals(nodeType)){
			return com.mbag.model.Node.THREAD_NODE;
		}else if(com.mbag.model.Node.SUBFLOW.equals(nodeType)){
			return com.mbag.model.Node.SUBFLOW_NODE;
		}else{
			throw new UndefindNodeException("未定义节点："+nodeType);
		}
	}
	
	public static int getPoolAttr(Element node){
		String pool = node.attributeValue(Node.THREAD_POOL_ATTR);
		return ("".equals(pool)||null==pool)?0:Integer.parseInt(pool);
	}
	
}
