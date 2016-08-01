package com.ib.mamager;

import com.ib.parser.*;
import com.ib.reader.*;

public class LogManager {
	private String twsVersion;
	private String apiVersion;
	private LogReader reader;
	
	public LogManager(String twsVersion, String apiVersion){
		this.twsVersion = twsVersion;
		this.apiVersion = apiVersion;
	}
	
	public LogManager(){
		this.twsVersion = null;
		this.apiVersion = null;
		reader = new LogReader();
	}
	
	public void start(){
		reader.loadFilesList();
	}
}
