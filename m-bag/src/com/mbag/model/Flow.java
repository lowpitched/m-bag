package com.mbag.model;

import java.util.ArrayList;
import java.util.List;

import com.mbag.data.FlowConstant;

public class Flow {
	/**交易码*/
	private String tradeCode;
	/**流程名称*/
	private String name;
	/**是否可用*/
	private boolean isUse;
	/**是否走默认节点*/
	private boolean isGoDefaultNode;
	/**节点集合*/
	private List<Node> flow = new ArrayList<Node>();
	
	private FlowConstant flowConstant;
	
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public boolean isUse() {
		return isUse;
	}
	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}
	public boolean isGoDefaultNode() {
		return isGoDefaultNode;
	}
	public void setGoDefaultNode(boolean isGoDefaultNode) {
		this.isGoDefaultNode = isGoDefaultNode;
	}
	
	public boolean addNode(Node node){
		return flow.add(node);
	}
	
	public List<Node> getNodes(){
		return flow;
	}
	
	public boolean removeNode(Node node){
		 return flow.remove(node);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FlowConstant getFlowConstant() {
		return flowConstant;
	}
	public void setFlowConstants(FlowConstant flowConstant) {
		this.flowConstant = flowConstant;
	}
	@Override
	public String toString() {
		return "tradeCode:"+tradeCode+">>>"+flow.toString();
	}
	
}
