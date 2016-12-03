package edu.csupomona.cs480.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.csupomona.cs480.data.DdosAttack;


/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	DdosAttack attackReference = null;
	
	@RequestMapping(value = "/attack/", method = RequestMethod.POST)
	
	void triggerAttack(@RequestParam("targetAddress") String targetAddr,
           @RequestParam("numberOfThreads") String numThreads)
	{
		DdosAttack da = new DdosAttack();
		da.attack(targetAddr, numThreads);
		
		attackReference = da;
	}

	@RequestMapping(value = "/stop/", method = RequestMethod.POST)
	
	void terminateAttack(@RequestParam("terminate") String terminate)
	{
		attackReference.terminateAttack();
		new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
            	System.out.println("Attack Terminated");
            }
        }, 
        	2000 
		);
	}
}