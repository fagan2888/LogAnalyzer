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
	boolean isTWS;
	String twsLogFile = "tws.log";
	String settingsFile = "tws.error.xml";
	
	String outputDirectory;
	List<File> fileList;
	
	MarketDataSettingsMessage mdSettingsMessage;
	ApiSettingsMessage apiSettingsMessage;
	
	public LogReader(boolean isTWS){
		mdSettingsMessage = new MarketDataSettingsMessage();
		apiSettingsMessage = new ApiSettingsMessage();
		this.isTWS = isTWS;
		//outputDirectory = "C:/Users/Siteng Jin/Documents/GitHub/LogAnalyzer/Temp";
		outputDirectory = "/Users/sitengjin/Documents/Github/LogAnalyzer/Temp";
	}
	
	public void loadDiagnosticFiles(String dir){
		
	}
	
	public void loadFilesList() throws Exception{
		if(outputDirectory == null){
			throw new Exception("Invalid Directory");
		}
		
		if(fileList == null){
			File dir = new File(outputDirectory);
			fileList = (List<File>) FileUtils.listFiles(dir, new String[] {"log", "xml"}, true);
		}
	}
	
	public File getSettingsFile(){
		for(File file: fileList){
			if(file.getName().equals(this.settingsFile)){
				return new File(file.getPath());
			}
		}
		return null;
	}
	
	public File getTwsLogFile() throws Exception{
		loadFilesList();
		
		for(File file: fileList){
			if(file.getName().equals(this.twsLogFile)){
				return new File(file.getPath());
			}
		}
		
		return null;
	}
	
	public void parseSettingsFile(int choice) throws Exception{
		File settings = this.getSettingsFile();
		
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
			SettingsLogParser.parseMDSettingsFile(doc, mdSettingsMessage);
			break;
		default:
			break;
		}
	}
	
	public void parseTwsLogFile(int choice) throws Exception{
		File twsLogFile = this.getTwsLogFile();
		
		TwsLogParser twsLogParser = new TwsLogParser();
		
		switch (choice) {
		case Choices.TWS:
			TwsLogParser.parseTwsRestartInfo(twsLogFile);
			break;
		case Choices.MD:
			
			parseSettingsFile(Choices.MD);
			break;
		case Choices.API:
			
			parseSettingsFile(Choices.API);
			break;
		default:
			break;
		}
	}
	
	public void testPrint(){
		System.out.println(apiSettingsMessage.getCopyApiSettingsList().toString());
		System.out.println(apiSettingsMessage.getCopyApiPrecautionsList().toString());
		System.out.println(apiSettingsMessage.getCopyTrustedIPs().toString());
		System.out.println(mdSettingsMessage.getCopyMdSettingsList().toString());
		System.out.println(mdSettingsMessage.getCopyEsignalSettingsList().toString());
		System.out.println(mdSettingsMessage.getCopyEsignalSecSettingsListOpt().toString());
		System.out.println(mdSettingsMessage.getCopyEsignalSecSettingsListStk().toString());
		System.out.println(mdSettingsMessage.getCopyEsignalSecSettingsListFut().toString());
		System.out.println(mdSettingsMessage.getCopyEsignalSecSettingsListInd().toString());
		System.out.println(mdSettingsMessage.getCopySmartRoutSettingsList().toString());
	}
	
}
