package com.ctcc.asn.parser.entity.impl;

import java.util.LinkedHashMap;

import com.ctcc.asn.parser.Entity;

public class FixCDR implements Entity {
	
	@Override
	public LinkedHashMap<Integer, Object> getHeadTagMap() {
		headTagMap.put(50, Integer.class);
		headTagMap.put(52, String.class);
		headTagMap.put(53, String.class);
		headTagMap.put(54, String.class);
		headTagMap.put(55, Integer.class);
		return headTagMap;
	}

	@Override
	public LinkedHashMap<Integer, Object> getTailTagMap() {
		tailTagMap.put(50, Integer.class);
		tailTagMap.put(52, String.class);
		tailTagMap.put(53, String.class);
		tailTagMap.put(57, Integer.class);
		tailTagMap.put(63, Integer.class);
		return tailTagMap;
	}

	@Override
	public LinkedHashMap<Integer, Object> getRecordColumTagMap() {
		
		recordColumTagMap.put(450, Integer.class);
		recordColumTagMap.put(280, String.class);
		recordColumTagMap.put(102, String.class);
		recordColumTagMap.put(103, String.class);
		recordColumTagMap.put(153, String.class);
		recordColumTagMap.put(315, String.class);
		recordColumTagMap.put(466, String.class);
		recordColumTagMap.put(114, String.class);
		recordColumTagMap.put(467, String.class);
		recordColumTagMap.put(245, String.class);
		recordColumTagMap.put(293, String.class);
		recordColumTagMap.put(105, Integer.class);
		recordColumTagMap.put(462, String.class);
		recordColumTagMap.put(463, String.class);
		recordColumTagMap.put(498, Integer.class);
		recordColumTagMap.put(432, Integer.class);
		recordColumTagMap.put(117, Integer.class);
		recordColumTagMap.put(493, Integer.class);
		recordColumTagMap.put(119, Integer.class);
		recordColumTagMap.put(495, Integer.class);
		
		recordColumTagMap.put(2150, String.class);
		recordColumTagMap.put(496, Integer.class);
		recordColumTagMap.put(497, Integer.class);
		recordColumTagMap.put(488, Integer.class);
		recordColumTagMap.put(489, String.class);
		recordColumTagMap.put(491, Integer.class);
		recordColumTagMap.put(490, String.class);
		recordColumTagMap.put(483, String.class);
		recordColumTagMap.put(484, String.class);
		recordColumTagMap.put(485, String.class);
		
		
		return recordColumTagMap;
	}

}
