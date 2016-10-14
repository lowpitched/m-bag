package com.mbag.test;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mbag.exception.UndefindNodeException;
import com.mbag.model.Flow;
import com.mbag.model.Node;
import com.mbag.util.NodeUtils;
@SuppressWarnings("unchecked")
public class XMLParseTest {
	
	public static final String ALL_NODE="if[not(@use='false')]|node[not(@use='false')]|for[not(@use='false')]|while[not(@use='false')]|thread[not(@use='false')]|flow[not(@use='false')]";

	public static void main(String[] args) throws DocumentException, UndefindNodeException {
	
		InputStream is = XMLParseTest.class.getClassLoader().getResourceAsStream(
				"mbag.xml");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);
		List<Element> flows = doc.selectNodes("mbag/flows[not(@use='false')]/flow[not(@use='false')]");
		for(Element flow:flows){
			Flow f = new Flow();
			List<Element> elems = flow.selectNodes(ALL_NODE);
			for(Element node:elems){
				Node n = genNode(node);
				f.addNode(n);
			}
			List<Node> nodes = f.getNodes();
			for (Node n1 : nodes) {
				System.err.println(n1);
				List<Node> n2s = n1.getNodes();
				for (Node n2 : n2s) {
					System.err.println("--"+n2);
					List<Node> n3s = n2.getNodes();
					for(Node n3:n3s){
						System.err.println("----"+n3);
						List<Node> n4s = n3.getNodes();
						for(Node n4:n4s){
							System.err.println("------"+n4);
						}
					}
				}
			}
		}
	}

	private static Node genNode(Element node) throws UndefindNodeException {
		Node n = new Node();
		n.setUse(true);
		n.setNodeType(NodeUtils.getNodeType(node.getName()));
		n.setPool(NodeUtils.getPoolAttr(node));
		n.setClazzName(node.attributeValue(Node.CLASS_ATTR));
		List<Element> children = node.selectNodes(ALL_NODE);
		if(children.size()==0) return n;
		for(Element child:children){
			n.addNode(genNode(child));
		}
		return n;
	}
	
}
