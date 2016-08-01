package com.ib.reader;

import com.ib.parser.*;
import com.ib.message.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import java.util.HashMap;

public class LogReader {
	String outputDirectory;
	List<File> fileList;
	
	MarketDataSettingsMessage mdSettingsMessage;
	ApiSettingsMessage apiSettingsMessage;
	
	public LogReader(){
		mdSettingsMessage = new MarketDataSettingsMessage();
		apiSettingsMessage = new ApiSettingsMessage();
		outputDirectory = "C:/Users/Siteng Jin/Documents/GitHub/LogAnalyzer/Temp";
	}
	
	public void loadDiagnosticFiles(String dir){
		
	}
	
	public void loadFilesList() throws Exception{
		if(outputDirectory == null){
			throw new Exception("Invalid Directory");
		}
		File dir = new File(outputDirectory);
		fileList = (List<File>) FileUtils.listFiles(dir, new String[] {"log", "xml"}, true);
	}
	
	public File getTwsErrorXml(){
		for(File file: fileList){
			if(file.getName().equals("tws.error.xml")){
				return new File(file.getPath());
			}
		}
		return null;
	}
	
	public void parseSettingsFile(File settings, int choice) throws Exception{
		if(settings == null){
			throw new Exception("Invalid File");
		}
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(settings);
		doc.getDocumentElement().normalize();
		
		SettingsLogParser settingsLogParser = new SettingsLogParser();
		
		switch (choice) {
		case Choices.API:
			SettingsLogParser.parseAPISettingsFile(doc, apiSettingsMessage);
			break;
		case Choices.MD:
			
			break;
		default:
			break;
		}
	}
	
	public void testPrint(){
		System.out.println(apiSettingsMessage.getCopyApiSettingsList().toString());
		System.out.println(apiSettingsMessage.getCopyApiPrecautionsList().toString());
		System.out.println(apiSettingsMessage.getCopyTrustedIPs().toString());
		
	}
	
}
