package edu.csupomona.cs480.data;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class DistributeThread extends Thread{
	
	URL postAddress;
	String distribType;
	
	public DistributeThread(URL url)
	{	
		postAddress = url;
	}
	
	public void run()
	{
		
		try {
			HttpURLConnection httpCon = (HttpURLConnection) postAddress.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			System.out.println(httpCon.getResponseCode() + " " + httpCon.getResponseMessage());

			out.close();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}