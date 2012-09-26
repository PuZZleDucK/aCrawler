package com.puzzleduck.crawler;

import java.util.concurrent.RecursiveAction;

public class BackgroundPaint extends RecursiveAction {
	
	
	@Override
	protected void compute()
	{
//	    System.out.println("compute ui");
	    while(true)
	    {
		    ACrawler.scanPanel.repaint();//...moving to alt thread... done :D ... ok I'm off, 'prolly won't be back for a while

		    try {
				Thread.sleep(20);//adjust for fine controll over gui update speeds
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	    }
		
	}
	
	

}