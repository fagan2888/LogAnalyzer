package com.ib.parser;

import java.io.File;
import java.util.Scanner;

public class TwsLogParser {
	public static void parseTwsRestartInfo(File twsLogFile) throws Exception{
		Scanner scanner = new Scanner(twsLogFile);
		
		while(scanner.hasNextLine()){
			String currentLine = scanner.nextLine();
			if(currentLine.contains("TWS RESTART")){
				System.out.println(currentLine);
			}
		}
	}
}
