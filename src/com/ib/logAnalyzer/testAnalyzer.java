package com.ib.logAnalyzer;

import com.ib.reader.*;

public class testAnalyzer {
	public static void main(String [] arg){
		LogReader reader = new LogReader();
		try {
			reader.download();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}
