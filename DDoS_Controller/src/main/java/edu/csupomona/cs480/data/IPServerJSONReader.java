package edu.csupomona.cs480.data;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IPServerJSONReader {
	
	ArrayList<String> ipArray = new ArrayList<String>();

	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public JSONObject readJsonFromFile(String path) throws IOException, JSONException {
		
		File jsonFile = new File(path);

		InputStream is = new FileInputStream(jsonFile);
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	}
	  
	public ArrayList<String> getIPFromFile(String path) throws IOException, JSONException {
		JSONObject jsonObj = readJsonFromFile(path);
		
		Iterator<?> x = jsonObj.keys();
		JSONArray jsonArr = new JSONArray();

		while (x.hasNext()){
		    String key = (String) x.next();
		    jsonArr.put(jsonObj.get(key));
		}
		
		for(int i=0; i<jsonArr.length(); i++) {
			JSONObject obj = jsonArr.getJSONObject(i);
			ipArray.add(obj.getString("ip"));
		}
		
		return ipArray;		
	}

	public int getZombieCount() {

		return ipArray.size();
	}
}