package com.ctcc.asn.parser;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AsnParserAbs implements AsnParserItf {

	private FileInputStream is  ;
	
	/**
	 * 文件体输出路径
	 */
	protected BufferedWriter bw  ;
	/**
	 * 文件头和尾输出路径
	 */
	protected BufferedWriter extbw  ;
	
	public AsnParserAbs(FileInputStream is ,File outFile){
		this.is = is ;
		
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8") );
			
			extbw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile+".ht"),"utf-8") ); 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	private  int getLength() throws Exception {
		
		int length = 0 ;
		
		int b = is.read() ;
		
		if(b==-1){
    		return length;
    	}
		
		
		if((b >> 7)==0){
			
			length = b&0x7F;
			
		}else{
			
			int lengthCount = b&0x7F; 
			
			for (int i = lengthCount; i >0; i--) {
				
				length += is.read() << ((i-1)*8);  
			
			}
			
		}
		
		return length ; 
	}
	private  Map<String,Integer> getLength(int pos) throws Exception {
		
		Map<String,Integer> resultMap = new HashMap<String, Integer>();
		
		int length = 0 ;
		
		int b = is.read() ;
		
		pos++;
		
		if(b==-1){
			
			resultMap.put("length", length);
			
			resultMap.put("pos", pos);
			
			return resultMap;
		}
		
		
		if((b >> 7)==0){
			
			length = b&0x7F;
			
		}else{
			
			int lengthCount = b&0x7F; 
			
			for (int i = lengthCount; i >0; i--) {
				
				length += is.read() << ((i-1)*8);  
				
				pos++;
				
			}
			
		}
		
		resultMap.put("length", length);
		
		resultMap.put("pos", pos);
		
		return resultMap ; 
	}
	
	
	
	private  long getTag() throws Exception{
		
		int b =  is.read() ;
    	
    	long tag = 0 ;
    	
    	if(b==-1){
    		return -1;
    	}
    	
    	if((b&0x1F)<=30){
    		
    		tag = b&0x1F ;
    		
    	}else{
    		
    		int tagB = is.read() ;
    		
    		if(0==(tagB>>7)){
    			
    			tag = tagB;
    			
    		}else{
    			
    			tag =+(tag<<7) + (tagB&0x7F) ;
    			
    			while(1==((tagB = is.read())>>7)) {
        			
    				tag =+(tag<<7) + (tagB&0x7F) ;
	   				 
	       		}
    			
    			tag =+(tag<<7) + (tagB&0x7F) ;
    		}
    		
    	}
    	
    	return tag ;
		
	}
	
	
	public  Map<String,Integer> getTag(int pos) throws Exception{
		
		Map<String,Integer> resultMap = new HashMap<String, Integer>();
		
		int b =  is.read() ;
		
		pos++;
		
		int tag = 0 ;
		
		if(b==-1){
			
			resultMap.put("tag", -1);
			
			resultMap.put("pos", pos);
			
			return resultMap;
		}
		
		if((b&0x1F)<=30){
			
			tag = b&0x1F ;
			
		}else{
			
			int tagB = is.read() ;
			
			pos++;
			
			if(0==(tagB>>7)){
				
				tag = tagB;
				
			}else{
				
				tag =+(tag<<7) + (tagB&0x7F) ;
				
				while(1==((tagB = is.read())>>7)) {
					
					tag =+(tag<<7) + (tagB&0x7F) ;
					
					pos++;
					
				}
				pos++;
				tag =+(tag<<7) + (tagB&0x7F) ;
			}
			
		}
		
		resultMap.put("tag", tag);
		
		resultMap.put("pos", pos);
		
		return resultMap ;
		
	}

	
	public  void getValue() throws Exception{
		
    	long tag = getTag();
    	
    	if(tag==getRootApplication()){
    	
    		int length = getLength();
    		
    		System.out.println("root application-length:"+length);
    		
    		
    	}else if (tag == getHeadApplication()){
    		
    		Map<String,String> headMap = new LinkedHashMap<String, String>();
    		
    		
    		int length = getLength();
    		
    		System.out.println("head application-length:"+length);
    		
    		int pos = 0 ;
    		
    		while( pos <length ){
    			
    			Map<String,Integer> tagMap = getTag( pos);
    			
    			int headColumTag = tagMap.get("tag");
    			
    			pos = tagMap.get("pos");
    			
    			Map<String,Integer> lengthMap = getLength(pos);
	    		
	    		int fieldLength = lengthMap.get("length");
	    		
	    		pos = lengthMap.get("pos");
	    		
	    		pos += fieldLength;
	    		
	    		byte[] fieldValueBytes = new byte[fieldLength] ;
	    		
	    		is.read(fieldValueBytes);
    			
	    		Object cls = getHeadTagMap().get(headColumTag);
    			
    			if(cls==String.class){

    	    		headMap.put(headColumTag+"", new String(fieldValueBytes));
    				
    			}else if(cls==Integer.class){
    	    		
    	    		StringBuffer buffer = new StringBuffer();
    	    		
    	    		for (int i = 0; i < fieldValueBytes.length; i++) {
    					byte val = fieldValueBytes[i];
    					
    					buffer.append((int)val);
    				}
    	    		
    	    		headMap.put(headColumTag+"", buffer.toString());
    			}else{
    				
    				headMap.put(headColumTag+"", "PNA");
    			}
    			
    		}
    		
    		extbw.write(getMapString(headMap));
    		
    		extbw.flush();
    		
    		
    	}else if (tag == getBodyApplication()){
    		
    		int length = getLength();
    		
    		System.out.println("records application-length:"+length);
    		
    		int pos = 0 ;
    		
    		while( pos <length ){
    			
    			Map<String,Integer> tagMap = getTag(pos);
    			
    			int recordTag = tagMap.get("tag");
    			
    			pos = tagMap.get("pos");
    			
    			if (recordTag == getBodyRecordApplication()){
    	    		
    				Map<String,String> recordMap = new LinkedHashMap<String, String>();
    				
    				Map<String,Integer> lengthMap = getLength( pos);
    	    		
    	    		int recordsLength = lengthMap.get("length");
    	    		
    	    		pos = lengthMap.get("pos");
    	    		
    	    		pos += recordsLength ;
    				
    				System.out.println("record application-length:"+recordsLength);
    				
    				int recordcolumpos = 0 ;
    				
    				while(recordcolumpos<recordsLength){
    					
    					recordcolumpos = parseRecord(is, recordcolumpos,recordMap);
    					
    				}
    				
    				bw.write(getRecordColumMapString(recordMap));
    	    		
    	    		bw.flush();
    	    		
    	    	}
    		}
    		
    	}else if (tag == getTailApplication()){
    		
    		Map<String,String> tailMap = new LinkedHashMap<String, String>();
    		
    		
    		int length = getLength();
    		
    		System.out.println("tail application-length:"+length);
    		
    		int pos = 0 ;
    		
    		while(pos<=length){
    			
    			Map<String,Integer> tagMap = getTag( pos);
    			
    			int tailColumTag = tagMap.get("tag");
    			
    			pos = tagMap.get("pos");
    			
    			Map<String,Integer> lengthMap = getLength(pos);
	    		
	    		int fieldLength = lengthMap.get("length");
	    		
	    		pos = lengthMap.get("pos");
	    		
	    		pos += fieldLength;
	    		
	    		byte[] fieldValueBytes = new byte[fieldLength] ;
	    		
	    		is.read(fieldValueBytes);
    			
	    		Object cls = getHeadTagMap().get(tailColumTag);
    			
    			if(cls==String.class){
    	    		
    	    		tailMap.put(tailColumTag+"", new String(fieldValueBytes));
    				
    			}else if(cls==Integer.class){
    	    		
    	    		StringBuffer buffer = new StringBuffer();
    	    		
    	    		for (int i = 0; i < fieldValueBytes.length; i++) {
    					byte val = fieldValueBytes[i];
    					
    					buffer.append((int)val);
    				}
    	    		
    	    		tailMap.put(tailColumTag+"", buffer.toString());
    			}else{
    				
    				tailMap.put(tailColumTag+"", "PNA");
    			}
    			
    		}
    		
    		extbw.write(getMapString(tailMap));
    		
    		extbw.flush();
    		
    		extbw.close();
    		
    		return ;
    	}
    	
    	getValue();
    	
    }
	
	public  int parseRecord(InputStream is,int pos,Map<String,String> recordMap) throws Exception {
		
		Map<String,Integer> tagMap = getTag(pos);
		
		int tag = tagMap.get("tag");
		
		pos = tagMap.get("pos");
		
		Map<String,Integer> resultMap = getLength( pos);
		
		int length = resultMap.get("length");
		
		pos = resultMap.get("pos");
		
		pos += length;
		
		byte[] fieldValueBytes = new byte[length] ;
		
		is.read(fieldValueBytes);
		
		Object cls = getRecordColumTagMap().get(tag);
		
		if (cls == String.class){
			
    		recordMap.put(tag+"", new String(fieldValueBytes));
    		
		}else if(cls == Integer.class){
    		
    		StringBuffer buffer = new StringBuffer();
    		
    		for (int i = 0; i < fieldValueBytes.length; i++) {
				byte val = fieldValueBytes[i];
				
				buffer.append((int)val);
			}
    		
    		recordMap.put(tag+"", buffer.toString());
		}else{
			
			recordMap.put(tag+"", "PNA");
		}
		
		return pos ;
		
	}
	
	private String getRecordColumMapString(Map<String,String> map){
		
		StringBuffer buffer = new StringBuffer();
		
		if(map!=null&&map.size()>0){
			
			Iterator<Entry<Integer, Object>> iter = getRecordColumTagMap().entrySet().iterator();

			while(iter.hasNext()){
				
				Entry<Integer, Object> entry = iter.next();
				
				String key = entry.getKey()+"";
				
				
				buffer.append((map.get(key)==null?"":map.get(key))+"|");
			}
			
			buffer.append("\r\n");
		
			map = null ;
		}
		
		return buffer.toString();
	}
	
	

	private String getMapString(Map<String,String> map){
		
		StringBuffer buffer = new StringBuffer();
		
		if(map!=null&&map.size()>0){
			
			Iterator<Entry<String, String>> iter = map.entrySet().iterator();

			while(iter.hasNext()){
				
				Entry<String, String> entry = iter.next();
				
				buffer.append(entry.getValue()+"|");
			}
			
			buffer.append("\r\n");
		
			map = null ;
		}
		
		return buffer.toString();
	}
	
	
}
