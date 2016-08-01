package com.ib.mamager;

import com.ib.parser.*;
import com.ib.reader.*;
import java.io.File;
import com.ib.parser.Choices;

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
		try {
			reader.loadFilesList();
			File currentSettingsFile = reader.getTwsErrorXml();
			reader.parseSettingsFile(currentSettingsFile, Choices.API);
			reader.testPrint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
