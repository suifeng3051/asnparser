package com.ctcc.asn.parser.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ctcc.asn.parser.ConstantValue;
import com.ctcc.asn.parser.EntityParser;

public class ParseMain {

	public static void main(String[] args1) {
		String[] args={"d:/input/","OTH_CDR_20150430.027.083.999.005.01.SR20150430135822","d:/output"};
		
		if (args == null || args.length == 0) {
			throw new NullPointerException("args is null");
		}
		String directory = null;
		String filename = null;
		String outPutDirectory = null;
		if (args.length < 2) {
			throw new IllegalArgumentException("argument size error,must be least 2 ");
		}
		if (args.length == 3) {
			outPutDirectory = args[2];
		}
		directory = args[0];
		if (directory == null || "".equals(directory)) {
			throw new NullPointerException("asn1 directory is null!");
		}
		File directoryFile = new File(directory);
		if (!directoryFile.exists()) {
			throw new IllegalArgumentException("asn1 directory not exgist!");
		}
		filename = args[1];
		if (filename == null || "".equals(filename)) {
			throw new NullPointerException("fileName is null!");
		}
		final String fname = filename;
		try {
			ExecutorService executor = Executors.newFixedThreadPool(100);
			File[] files = directoryFile.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.indexOf(fname) != -1) {
						return true;
					}
					return false;
				}
			});
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				File outFile = null;
				if (outPutDirectory == null) {
					outFile = new File(file.getParent(), file.getName() + ".parse_result");
				} else {
					outFile = new File(outPutDirectory, file.getName() + ".parse_result");
				}
				
				int province=ConstantValue.ANHUI;
				int type=ConstantValue.FIX_CDR;
				EntityParser parserTask = new EntityParser(province,type,new FileInputStream(file), outFile);
				executor.execute(parserTask);
			}
			executor.shutdown();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
