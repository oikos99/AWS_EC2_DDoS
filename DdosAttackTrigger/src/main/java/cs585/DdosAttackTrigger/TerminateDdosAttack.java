package cs585.DdosAttackTrigger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class TerminateDdosAttack {

	public static void main(String[] args) throws IOException{
	// TODO Auto-generated method stub
	
		String controllerAddr = "35.164.62.241";  // Controller IP address
		
		URL url = new URL("http://" + controllerAddr + ":8080/terminate/?confirm=1");	
		
		sendTerminatePOSTRequest(url);
	}
	
	public static void sendTerminatePOSTRequest(URL postAddress) {
		
		try {
			HttpURLConnection httpCon = (HttpURLConnection) postAddress.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			System.out.println(httpCon.getResponseCode() + httpCon.getResponseMessage() 
												+ "Termination sent to Controller");

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
