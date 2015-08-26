package com.ctcc.asn.parser;

import java.util.Map;

import com.ctcc.asn.parser.entity.impl.FixCDR;
import com.ctcc.asn.parser.entity.impl.FixDDR;
import com.ctcc.asn.parser.entity.impl.FixMDR;
import com.ctcc.asn.parser.entity.impl.FixVDR;
import com.ctcc.asn.parser.entity.impl.OthCDR;

public class EntityFactory {
	public static Map<String, Entity> cdrEntityMap;

	private EntityFactory() {

	}

	public static Entity getInstance(int type, int province) {
		Entity entity = null;
		switch (province) {
		
		default:
			switch (type) {
			case ConstantValue.FIX_CDR:
				entity = new FixCDR();
				break;
			case ConstantValue.FIX_DDR:
				entity = new FixDDR();
				break;
			case ConstantValue.FIX_MDR:
				entity = new FixMDR();
				break;
			case ConstantValue.FIX_VDR:
				entity = new FixVDR();
				break;
			case ConstantValue.OTH_CDR:
				entity = new OthCDR();
				break;
			case ConstantValue.OTH_DDR:
				entity = new OthCDR();
				break;
			case ConstantValue.OTH_MDR:
				entity = new OthCDR();
				break;
			case ConstantValue.OTH_VDR:
				entity = new OthCDR();
				break;
			}
		}

		return entity;
	}

}
