package com.mbag.context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mbag.log.LogTools;
import com.mbag.model.Flow;
import com.mbag.parser.ConfigParser;
import com.mbag.pool.FlowPool;

public class DefaultConfigration extends Configration{

	public DefaultConfigration(FlowPool flowPool) {
		super(flowPool);
	}

	public static MbagContext config(InputStream is){
		LogTools.i(Configration.class, "start parse mbag.xml");
		List<Flow> flows = ConfigParser.parse(is);
		Map<String,Flow> fs = new HashMap<String,Flow>();
		int i=0;
		for(Flow flow:flows){
			fs.put(flow.getTradeCode(), flow);
			LogTools.i(DefaultConfigration.class, "flow"+i+":"+flow.toString());
			i++;
		}
		LogTools.i(DefaultConfigration.class, "end parse mbag.xml");
		return MbagContext.getInstance(FlowPool.getInstance(fs));
	}
	
	public static MbagContext config(){
		InputStream is = DefaultConfigration.class.getClassLoader().getResourceAsStream("mbag.xml");
		return config(is);
	}
	
	
	
}
