package com.ctcc.asn.parser.entity.impl;

import java.util.LinkedHashMap;

import com.ctcc.asn.parser.Entity;
public class FixMDR  implements Entity {
	
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
		recordColumTagMap.put(280, Integer.class);
		recordColumTagMap.put(350, String.class);
		recordColumTagMap.put(102, String.class);
		recordColumTagMap.put(103, String.class);
		recordColumTagMap.put(353, String.class);
		recordColumTagMap.put(356, Integer.class);
		recordColumTagMap.put(493, Integer.class);
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
