package edu.csupomona.cs480.data;

import java.util.Date;

public class IP {

	/** The unique user Id */
    private String ip;
    private String checkInTime = new Date(System.currentTimeMillis()).toString();


    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
}
