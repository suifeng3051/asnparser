package com.ctcc.asn.parser;

import java.util.LinkedHashMap;

public interface Entity {
	public static LinkedHashMap<Integer,Object> headTagMap = new LinkedHashMap<Integer, Object>();
	public static LinkedHashMap<Integer,Object> recordColumTagMap = new LinkedHashMap<Integer, Object>();
	public static LinkedHashMap<Integer,Object> tailTagMap = new LinkedHashMap<Integer, Object>();
	public abstract LinkedHashMap<Integer, Object> getHeadTagMap();
	public abstract LinkedHashMap<Integer, Object> getTailTagMap();
	public abstract LinkedHashMap<Integer, Object> getRecordColumTagMap();

}
