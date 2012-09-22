/**
 *  (C)me & GPL3
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

		// TODO optional execuitor and fork/join count config
		 //   jCrawler.executorService = Executors.newFixedThreadPool(6);
		ACrawler.forkPool = new ForkJoinPool(4);

		String startUrl = (String) ACrawler.startingAddressComboBox.getSelectedItem();
		
	    try
	    {   
		    System.out.println( "Crawl start: " + startUrl );
	    	
	    	
	    	ACrawler.forkPool.invoke( new LinkSearch(startUrl) );
	    	
	    	
	    }catch(Exception e)
	    {
	      System.out.println( "Error starting crawl: " + e.toString() );
	    }

		
		

	}

}
