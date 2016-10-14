package com.mbag.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class IOUtils {

	private IOUtils(){}
	
	public static String readInputStream(InputStream in,String charset) throws IOException{
		StringBuilder builder = new StringBuilder();
		int len = 0;
		byte[] buff = new byte[1024];
		while((len=in.read(buff))>0){
			builder.append(new String(buff,0,len,charset));
		}
		return builder.toString();	
	}
	
	
	public static List<String> readInputStreamToList(InputStream in) throws IOException{
		List<String> records = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,"gbk"));
		String line = null;
		while((line=reader.readLine())!=null){
			records.add(line);
		}
		return records;
	}
}
