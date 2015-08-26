package com.ctcc.asn.parser;

import java.util.LinkedHashMap;

public interface AsnParserItf {
	
	 final String  HEAD= "header" ;
	
	 final String  BODY= "body" ;
	
	 final String  TAIL= "tail" ;

	/**
	 * 获取头结点包含的字段tag以及类型
	 * @return
	 */
	public LinkedHashMap<Integer,Object> getHeadTagMap();
	/**
	 * 获取尾结点包含的字段tag以及类型
	 * @return
	 */
	public LinkedHashMap<Integer,Object> getTailTagMap();
	/**
	 * 获取record结点包含的字段tag以及类型
	 * @return
	 */
	public LinkedHashMap<Integer,Object> getRecordColumTagMap();
	
	/**
	 * 获取跟结点信息
	 * @return
	 */
	public int getRootApplication() ;
	
	
	/**
	 * 获取head结点信息
	 * @return
	 */
	public int getHeadApplication() ;
	/**
	 * 获取body结点信息
	 * @return
	 */
	public int getBodyApplication() ;
	/**
	 * 获取record结点信息
	 * @return
	 */
	public int getBodyRecordApplication() ;
	/**
	 * 获取tail结点信息
	 * @return
	 */
	public int getTailApplication() ;
	
	
	/**
	 * 输出结果文件
	 */
	public void printResultFile();
	
	
}
