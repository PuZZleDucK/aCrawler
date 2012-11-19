/**
 * (c) me & GPL3
 * hmm it's been a while and I'm not sure... this might be just to slow the giu (v0.7something was crazy fast, this could be just wheels spinning... could probably be better done using the executor or something benjamin :\)
 */
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
