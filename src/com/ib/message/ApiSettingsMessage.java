package com.ib.message;

import java.util.ArrayList;
import java.util.HashMap;

public class ApiSettingsMessage extends SettingsMessage{
	/*
	private String dde;
	private String readOnlyApi;
	private String socketClient;
	private String autoOpenOrdDownload;
	private String includeFxPositions;
	private String includeContinuousUpdateChanges;
	private String port;
	private String useNegativeAutoRange;
	private String createApiMsgLogFile;
	private String includeMktData;
	private String logLevel;
	private String masterClientID;
	private String slowBufferTimeout;
	private String compExchSeparator;
	private String allowOnlyLocalhost;
	private ArrayList<String> trustedIPs;
	
	private String overridePrecautionaryConstraints;
	private String bypassBondWarning;
	private String bypassYtwWarning;
	private String bypassCalledBondWarning;
	private String bypassSameActionPairTradeWarning;
	*/
	
	private HashMap<String, String> apiSettingsList;
	private HashMap<String, String> apiPrecautionsList;
	
	private ArrayList<String> trustedIPs;
	
	public ApiSettingsMessage(){}
	
	public static final String[] APISETTINGSTAGS = {"dde", "readOnlyApi", "socketClient", "autoOpenOrdDownload", "includeFxPositions"
			, "includeContinuousUpdateChanges", "port", "useNegativeAutoRange", "createApiMsgLogFile", "includeMktData"
			, "logLevel", "masterClientID", "slowBufferTimeout", "compExchSeparator", "allowOnlyLocalhost"};
	
	public static final String[] APIPRECAUTIONSTAGS = {"overridePrecautionaryConstraints", "bypassBondWarning", "bypassYtwWarning"
			, "bypassCalledBondWarning", "bypassSameActionPairTradeWarning"};
	
	public void addTrustedIPs(String ip){
		if(trustedIPs == null){
			trustedIPs = new ArrayList<String>();
		}
		trustedIPs.add(ip);
	}
	
	public ArrayList<String> getCopyTrustedIPs(){
		return new ArrayList<String>(trustedIPs);
	}
	
	public void addApiAttrToSettingsList(String key, String value){
		if(apiSettingsList == null){
			apiSettingsList = new HashMap<String, String>();
		}
		apiSettingsList.put(key, value);
	}
	
	public HashMap<String, String> getCopyApiSettingsList(){
		return new HashMap<String, String>(apiSettingsList);
	}
	
	public void addApiAttrToPrecautionsList(String key, String value){
		if(apiPrecautionsList == null){
			apiPrecautionsList = new HashMap<String, String>();
		}
		apiPrecautionsList.put(key, value);
	}
	
	public HashMap<String, String> getCopyApiPrecautionsList(){
		return new HashMap<String, String>(apiPrecautionsList);
	}
}
