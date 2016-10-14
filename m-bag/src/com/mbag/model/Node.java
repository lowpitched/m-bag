package com.mbag.model;

import java.util.ArrayList;
import java.util.List;


public class Node {
	
	public static final String NODE="node";
	
	public static final String IF="if";
	
	public static final String FOR="for";
	
	public static final String WHILE="while";
	
	public static final String THREAD="thread";
	
	public static final String SUBFLOW="subflow";
	
	public static final String CLASS_ATTR="class";
	
	public static final String USE_ATTR="use";
	
	public static final String NAME_ATTR="name";
	
	public static final String VALUE_ATTR="value";
	
	public static final String TYPE_ATTR="type";
	
	public static final String ID_ATTR="id";
	
	public static final String THREAD_POOL_ATTR="pool";
	
	public static final int DEFAULT_NODE = 1;
	
	public static final int IF_NODE = 2;
	
	public static final int WHILE_NODE = 3;
	
	public static final int FOR_NODE = 4;
	
	public static final int SUBFLOW_NODE = 5;
	
	public static final int THREAD_NODE = 6;
	
	/**流程ID*/
	private String flowId;
	/**子流程ID*/
	private String childFlowId;
	/**节点类名*/
	private String clazzName;
	/**是否可用*/
	private boolean isUse;
	/**节点类型*/
	private int nodeType;
	/**线程池线程数*/
	private int pool;
	
	
	/**是否是正在运行的节点*/
	private boolean isCurrent;
	/**是否运行过*/
	private boolean isGo;
	
	private List<Node> nodes = new ArrayList<Node>();

	public int getPool() {
		return pool;
	}
	public void setPool(int pool) {
		this.pool = pool;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getChildFlowId() {
		return childFlowId;
	}
	public void setChildFlowId(String childFlowId) {
		this.childFlowId = childFlowId;
	}
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public boolean isUse() {
		return isUse;
	}
	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}
	public int getNodeType() {
		return nodeType;
	}
	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public boolean addNode(Node node){
		return nodes.add(node);
	}
	public boolean removeNode(Node node){
		return nodes.remove(node);
	}
	public boolean isCurrent() {
		return isCurrent;
	}
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	public boolean isGo() {
		return isGo;
	}
	public void setGo(boolean isGo) {
		this.isGo = isGo;
	}
	@Override
	public String toString() {
		return clazzName+nodes.toString();
	}
	
}
