package com.mbag.util;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mbag.log.LogTools;





@SuppressWarnings("unused")
public class FormatUtils {

	private FormatUtils(){}

	private static String valueAddLen(String formatValue){
		int len = formatValue.length();
		String formatLen = formatLen(len);
		return formatLen.concat(formatValue);
	}
	
	public static String formatValue(int length, String value, boolean isNumber) {
		String formatValue=null;
		if(isNumber){
			formatValue = addZeroFront(length,value);
		}else{
			formatValue=String.format("%-"+length+"s", value);
		}
		return formatValue;
	}

	public static String addZeroFront(int length, String value) {
		if(null==value){
			throw new RuntimeException("");
		}
		int curLen =value.length();
		StringBuilder formatValue=new StringBuilder();
		formatValue.append(value);
		while(curLen<length){
			formatValue.insert(0,"0");
			curLen++;
		}
		return formatValue.toString();
	}
	
	public static String addZeroFront(int length,int value){
		return addZeroFront(length,String.valueOf(value));
	}
	

	private static String formatLen(int len) {
		return String.format("%03d", len);
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> checkNull(Class clazz,Object obj){
		List<String> nullList=new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			field.setAccessible(true);
			Object o=null;
			try {
				o = field.get(obj);
			} catch (IllegalArgumentException e) {
				LogTools.e(FormatUtils.class, "非法参数错误",e);
			} catch (IllegalAccessException e) {
				LogTools.e(FormatUtils.class, null, e);
			}
			if(null==o){
				nullList.add(field.getName());
			}
		}
		return nullList;
	}
	
}
