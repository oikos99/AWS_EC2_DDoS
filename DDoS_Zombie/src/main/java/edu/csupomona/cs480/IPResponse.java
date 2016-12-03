package edu.csupomona.cs480;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IPResponse {
	
		
	public void startCallBack()
	{
		Runnable helloRunnable = new Runnable() {
		    public void run() {

				String ipControlerServerAddr = "35.164.62.241";  // Controller IP address
				
				URL url = null;
				try {
					
					url = new URL("http://" + ipControlerServerAddr + ":8080/storeIP/?storeZombieIP=1");
				
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				
				try {
					HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
					httpCon.setDoOutput(true);
					httpCon.setRequestMethod("POST");
					OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
					System.out.println(httpCon.getResponseCode() + " " 
											+ httpCon.getResponseMessage() + " IP sent to Controller");

					out.close();
				} catch (ProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		    }
		};

		// Check in with Controller every 10 secs
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 10, TimeUnit.SECONDS);

	}
}
