package edu.csupomona.cs480.data.provider;

import java.util.List;

import edu.csupomona.cs480.data.IP;


public interface IPManager {

	
	public IP getIP(String ip);
	
	public void updateIP(IP ip);

	/**
	 * Delete the given user from the storage.
	 *
	 * @param userId
	 */
	public void deleteIP(String ip);

	/**
	 * List all the current users in the storage.
	 *
	 * @return
	 */
	public List<IP> listAllIPs();	
}
