package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.IP;
import edu.csupomona.cs480.data.IPMap;
import edu.csupomona.cs480.util.ResourceResolver2;

public class FSIPManager implements IPManager {

	/**
	 * We persist all the user related objects as JSON.
	 * <p>
	 * For more information about JSON and ObjectMapper, please see:
	 * http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial
	 *
	 * or Google tons of tutorials
	 *
	 */
	private static final ObjectMapper JSON = new ObjectMapper();

	/**
	 * Load the user map from the local file.
	 *
	 * @return
	 */
	private IPMap getIPMap() {
		IPMap ipMap = null;
		File ipFile = ResourceResolver2.getIPFile();
		if (ipFile.exists()) {
			// read the file and convert the JSON content
			// to the UserMap object
			try {
				ipMap = JSON.readValue(ipFile, IPMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			ipMap = new IPMap();
		}
		return ipMap;
	}

	/**
	 * Save and persist the user map in the local file.
	 *
	 * @param userMap
	 */
	private void persistIPMap(IPMap ipMap) {
		try {
			// convert the user object to JSON format
			JSON.writeValue(ResourceResolver2.getIPFile(), ipMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IP getIP(String ip) {
		IPMap ipMap = getIPMap();
		return ipMap.get(ip);
	}

	@Override
	public void updateIP(IP ip) {
		IPMap ipMap = getIPMap();
		ipMap.put(ip.getIP(), ip);
		persistIPMap(ipMap);
	}

	@Override
	public void deleteIP(String ip) {
		IPMap ipMap = getIPMap();
		ipMap.remove(ip);
		persistIPMap(ipMap);
	}

	@Override
	public List<IP> listAllIPs() {
		IPMap ipMap = getIPMap();
		return new ArrayList<IP>(ipMap.values());
	}

}
