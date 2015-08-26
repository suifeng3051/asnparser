package com.ctcc.asn.parser.entity.impl;

import java.util.LinkedHashMap;

import com.ctcc.asn.parser.Entity;

public class OthDDR implements Entity{
	
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
		tailTagMap.put(69, String.class);
		tailTagMap.put(70, String.class);
		tailTagMap.put(71, String.class);
		tailTagMap.put(63, Integer.class);
		return tailTagMap;
	}

	@Override
	public LinkedHashMap<Integer, Object> getRecordColumTagMap() {
		recordColumTagMap.put(450, String.class);
		recordColumTagMap.put(102, String.class);
		recordColumTagMap.put(101, String.class);
		recordColumTagMap.put(307, String.class);
		recordColumTagMap.put(308, String.class);
		recordColumTagMap.put(309, String.class);
		recordColumTagMap.put(469, String.class);
		recordColumTagMap.put(470, String.class);
		recordColumTagMap.put(471, String.class);
		recordColumTagMap.put(315, String.class);
		recordColumTagMap.put(112, String.class);
		recordColumTagMap.put(256, String.class);
		recordColumTagMap.put(257, String.class);
		recordColumTagMap.put(113, Integer.class);
		recordColumTagMap.put(245, String.class);
		recordColumTagMap.put(246, String.class);
		recordColumTagMap.put(105, Integer.class);
		recordColumTagMap.put(322, Integer.class);
		recordColumTagMap.put(493, Integer.class);
		recordColumTagMap.put(119, Integer.class);
		recordColumTagMap.put(494, Integer.class);
		recordColumTagMap.put(121, String.class);
		recordColumTagMap.put(2150, String.class);
		recordColumTagMap.put(496, String.class);
		recordColumTagMap.put(497, Integer.class);
		recordColumTagMap.put(461, String.class);
		recordColumTagMap.put(106, String.class);
		recordColumTagMap.put(460, String.class);
		recordColumTagMap.put(488, String.class);
		recordColumTagMap.put(489, String.class);
		recordColumTagMap.put(491, Integer.class);
		recordColumTagMap.put(490, String.class);
		recordColumTagMap.put(492, Integer.class);
		recordColumTagMap.put(483, String.class);
		recordColumTagMap.put(484, String.class);
		recordColumTagMap.put(485, String.class);
		recordColumTagMap.put(486, String.class);
		recordColumTagMap.put(487, String.class);
		return recordColumTagMap;
	}

	
}
