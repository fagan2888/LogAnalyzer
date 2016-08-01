package com.ib.parser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import com.ib.message.*;

import java.io.File;

public class SettingsLogParser {
	
	public static void parseAPISettingsFile(Document doc, SettingsMessage settingsMessage){
		
		ApiSettingsMessage apiSettingsMessage = null;
		
		if(settingsMessage instanceof ApiSettingsMessage){
			apiSettingsMessage = (ApiSettingsMessage) settingsMessage;
		}
		
		NodeList apiNList = doc.getElementsByTagName("ApiSettings");
		Element apiElement = (Element) apiNList.item(0);
		
		for(String attr: ApiSettingsMessage.APISETTINGSTAGS){
			String attrValue = apiElement.getAttribute(attr);
			apiSettingsMessage.addApiAttrToSettingsList(attr, attrValue);
		}
		
		for(String attr: ApiSettingsMessage.APIPRECAUTIONSTAGS){
			String attrValue = apiElement.getAttribute(attr);
			apiSettingsMessage.addApiAttrToPrecautionsList(attr, attrValue);
		}
		
		Element listOfStrings = (Element) apiNList.item(0).getFirstChild();
		NodeList trustedIPs = listOfStrings.getElementsByTagName("String");
		for(int i = 0; i < trustedIPs.getLength(); i++){
			apiSettingsMessage.addTrustedIPs(trustedIPs.item(i).getTextContent());
		}
		
		/*
		NodeList listOfStrings = doc.getElementsByTagName("ListOfStrings");
		for(int i = 0; i < listOfStrings.getLength(); i++){
			Element e = (Element) listOfStrings.item(i);
			if(e.getAttribute("varName").equals("trustedIPAddresses")){
				NodeList trustedIPs = e.getElementsByTagName("String");
				for(int j = 0; j < trustedIPs.)
			}
		}
		*/
		
	}
}
