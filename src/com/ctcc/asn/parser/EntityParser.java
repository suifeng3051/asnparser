package com.ctcc.asn.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

public class EntityParser extends AsnParserAbs implements Runnable{
	public static int  ROOTAPPLICATION =   1 ;
	public static int  HEADAPPLICATION =   33 ;
	public static int  BODYAPPLICATION =   32 ;
	public static int  RECORDAPPLICATION = 32 ;
	public static int  TAILAPPLICATION =   34 ;	
	
	public  LinkedHashMap<Integer,Object> tailTagMap = new LinkedHashMap<Integer, Object>();
	public  LinkedHashMap<Integer,Object> recordColumTagMap = new LinkedHashMap<Integer, Object>();
	public  LinkedHashMap<Integer,Object> headTagMap = new LinkedHashMap<Integer, Object>();
	
	public  Entity cdrEntity=null;
	
	private EntityParser(FileInputStream is, File outFile) {
		super(is, outFile);
	}
	public EntityParser(int province,int type,FileInputStream is, File outFile) {
		super(is, outFile);
		cdrEntity=EntityFactory.getInstance(type,province);
		
		headTagMap=cdrEntity.getHeadTagMap();
		tailTagMap=cdrEntity.getTailTagMap();
		recordColumTagMap=cdrEntity.getRecordColumTagMap();
	}
	

	@Override
	public LinkedHashMap<Integer, Object> getHeadTagMap() {
		return headTagMap;
	}

	@Override
	public LinkedHashMap<Integer, Object> getTailTagMap() {
		return tailTagMap;
	}

	@Override
	public LinkedHashMap<Integer, Object> getRecordColumTagMap() {
		return recordColumTagMap;
	}

	@Override
	public int getRootApplication() {
		return ROOTAPPLICATION;
	}

	@Override
	public int getHeadApplication() {
		return HEADAPPLICATION;
	}

	@Override
	public int getBodyApplication() {
		return BODYAPPLICATION;
	}

	@Override
	public int getBodyRecordApplication() {
		return RECORDAPPLICATION;
	}

	@Override
	public int getTailApplication() {
		return TAILAPPLICATION;
	}

	@Override
	public void printResultFile() {
		long startTime = System.currentTimeMillis();
		try {
			getValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis() ;
		try {
			bw.write("cost:"+((endTime-startTime)/1000)+"s");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		try {
			getValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis() ;
		try {
			bw.write("cost:"+((endTime-startTime)/1000)+"s");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
