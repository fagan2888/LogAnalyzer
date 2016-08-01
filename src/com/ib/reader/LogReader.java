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

public class LogReader {
	String outputDirectory;
	List<File> fileList;
	
	MarketDataSettingsMessage mdSettings;
	ApiSettingsMessage apiSettings;
	
	public LogReader(){}
	
	public void loadFilesList(){
		File dir = new File(outputDirectory);
		fileList = (List<File>) FileUtils.listFiles(dir, new String[] {"log", "xml"}, true);
	}
}
