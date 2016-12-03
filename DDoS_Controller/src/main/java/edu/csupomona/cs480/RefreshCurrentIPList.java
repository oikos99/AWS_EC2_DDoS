package edu.csupomona.cs480;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.csupomona.cs480.data.IP;
import edu.csupomona.cs480.data.provider.IPManager;

public class RefreshCurrentIPList {
	final int SECONDS = 1000;
	
	public void start(final IPManager ipm)
	{		
		Runnable helloRunnable = new Runnable() {
		    public void run() {
		    	List<IP> iPObjList = ipm.listAllIPs();
		    	for(IP obj : iPObjList)
		    	{
		    		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		    	    Date checkedInDate = null;
		    	    try {
			    	    	checkedInDate = df.parse(obj.getCheckInTime());
			    	        //String newDateString = df.format(checkedInDate);
			    	        //System.out.println(newDateString);
		    	    } catch (Exception e) {
		    	        	e.printStackTrace();
		    	    }
		    	    
		    	    long checkInTime = checkedInDate.getTime();
		    		long currentTime = System.currentTimeMillis();
		    		
		    		if(checkInTime < currentTime - (10 * SECONDS))
		    		{
		    			//System.out.println("elapsed" + (currentTime - checkInTime)/1000);
		    			ipm.deleteIP(obj.getIP());
		    		}
	
		    	}
		    }
		};

		// Refresh IP list every 2 seconds 
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 2, TimeUnit.SECONDS);
	}
}
