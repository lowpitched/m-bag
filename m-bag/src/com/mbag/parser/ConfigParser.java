package com.mbag.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mbag.constant.XPathConstant;
import com.mbag.data.FlowConstant;
import com.mbag.exception.UndefindNodeException;
import com.mbag.exception.UnsupportConstantTypeException;
import com.mbag.log.LogTools;
import com.mbag.model.Flow;
import com.mbag.model.Node;
import com.mbag.util.NodeUtils;

@SuppressWarnings("unchecked")
public class ConfigParser {

	
	public static List<Flow> parse(InputStream is){
		List<Flow> fs = new ArrayList<Flow>();
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			List<Element> flows = doc.selectNodes(XPathConstant.FLOW_PART);
			for(Element flow:flows){
				FlowConstant flowConstant = getFlowConstant(flow);
				Flow f = new Flow();
				f.setFlowConstants(flowConstant);
				f.setTradeCode(flow.attributeValue(Node.ID_ATTR));
				f.setName(flow.attributeValue(Node.NAME_ATTR));
				f.setUse(true);
				List<Element> elems = flow.selectNodes(XPathConstant.ALL_NODES_OF_FLOW);
				for(Element node:elems){
					Node n = genNode(node);
					f.addNode(n);
				}
				fs.add(f);
			}
		} catch (DocumentException e) {
			LogTools.e(ConfigParser.class, e.getMessage());
		} catch (UndefindNodeException e) {
			LogTools.e(ConfigParser.class, e.getMessage());
		}finally{
			if(null!=is){
				try {
					is.close();
				} catch (IOException e) {
					LogTools.e(ConfigParser.class, e.getMessage());
				}
			}
		}
		return fs;
	}
	
	private static Node genNode(Element node) throws UndefindNodeException {
		Node n = new Node();
		n.setUse(true);
		n.setNodeType(NodeUtils.getNodeType(node.getName()));
		n.setPool(NodeUtils.getPoolAttr(node));
		n.setClazzName(node.attributeValue(Node.CLASS_ATTR));
		List<Element> children = node.selectNodes(XPathConstant.ALL_NODES_OF_FLOW);
		if(children.size()==0) return n;
		for(Element child:children){
			n.addNode(genNode(child));
		}
		return n;
	}
	
	private static FlowConstant getFlowConstant(Element element){
		FlowConstant constants = new FlowConstant();
		List<Element> nodes = element.selectNodes(XPathConstant.FLOW_CONSTANT);
		try{
			for(Element node:nodes){
				String constantName = node.attributeValue(Node.NAME_ATTR);
				String constantValue = node.attributeValue(Node.VALUE_ATTR);
				String constantType = node.attributeValue(Node.TYPE_ATTR);
				constants.put(constantName, convertValueType(constantValue,constantType));
			}
		}catch(UnsupportConstantTypeException e){
			LogTools.e(ConfigParser.class, e.getMessage());
		}
		return constants;
	}

	private static Object convertValueType(String constantValue,String constantType) throws UnsupportConstantTypeException {
		if("string".equalsIgnoreCase(constantType)){
			return constantValue;
		}else if("int".equals(constantType)){
			return Integer.parseInt(constantType);
		}else if("float".equals(constantType)){
			return Float.parseFloat(constantType);
		}else if("double".equals(constantType)){
			return Double.parseDouble(constantType);
		}else if("long".equals(constantType)){
			return Long.parseLong(constantType);
		}else if("boolean".equals(constantType)){
			return Boolean.parseBoolean(constantType);
		}else if("short".equals(constantType)){
			return Short.parseShort(constantType);
		}else{
			throw new UnsupportConstantTypeException("配置文件常量类型错误");
		}
	}
	
	
}
