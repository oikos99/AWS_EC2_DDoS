package edu.csupomona.cs480.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

public class DistributeDDoSTermination {

	private static final String BASE_DIR = System.getProperty("user.home") + "/ip";
    public ArrayList<String> ipArray = new ArrayList<String>();

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
				
				url = new URL("http://" + zombieIP + ":8080/stop/?terminate=1");	
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			
  			new DistributeThread(url).start();
  		}
  		
  		System.out.println("Termination Distributed to " + ipArray.size() + " Zombies");
    }	
}
