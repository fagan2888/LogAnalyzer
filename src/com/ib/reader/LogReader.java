package com.ib.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
//import com.ib.reader.ExtractZip;

public class LogReader {
	private String username;
	private final String TWSERRORREPORTURL = "https://wit1.interactivebrokers.com/cgi-bin/tws/tws_error_reader.pl";
	private String downloadDirectory;
	private String outputDirectory;
	
	private boolean isTWS;
	
	
	public void download() throws Exception{
		
		URL url = new URL("http://interactivebrokers.github.io/downloads/twsapi_macunix.972.16.zip");
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("GET");
    	InputStream in = connection.getInputStream();
    	FileOutputStream out = new FileOutputStream("download.zip");
    	copy(in, out, 1024);
    	out.close();
    	
    	ExtractZip.unZipIt("download.zip", "/Users/sitengjin/Documents/Github/LogAnalyzer");
	}
	
	public void copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
	    byte[] buf = new byte[bufferSize];
	    int n = input.read(buf);
	    while (n >= 0) {
	      output.write(buf, 0, n);
	      n = input.read(buf);
	    }
	    output.flush();
	  }
}
