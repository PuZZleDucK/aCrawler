/**
 *  (C)me & GPL3
 *  Webcrawl start/restart listener ... need i say more.
 */
package com.puzzleduck.crawler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ForkJoinPool;

/**
 * @author bminerds
 *
 */
public class ACrawlStarter implements ActionListener {

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		//check for running instances first... and simply kill if found... would be nice to go through and resume someday... not today
		if(ACrawler.forkPool == null)
		{
			ACrawler.forkPool = new ForkJoinPool(4);
			
		}else{
			ACrawler.forkPool = null;
			ACrawler.initDB();
			ACrawler.forkPool = new ForkJoinPool(4);
		}
		
		String startUrl = (String) ACrawler.startingAddressComboBox.getSelectedItem();
		
	    try
	    {   
		    System.out.println( "Crawl start: " + startUrl );
	    	ACrawler.forkPool.invoke( new LinkSearch(startUrl, new AppData()) );
	    }catch(Exception e)
	    {
	      System.out.println( "Error starting crawl: " + e.toString() );
	    }
	}//action
}//class
