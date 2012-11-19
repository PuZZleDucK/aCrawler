/**
 *  (C)me & GPL3
 *  Just a receiver to call shutdown on the threads
 */
package com.puzzleduck.crawler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ForkJoinPool;

/**
 * @author bminerds
 *
 */
public class ACrawlStopper implements ActionListener {

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		ACrawler.forkPool.shutdownNow();
		System.out.println( "Crawl Shutdown.");
		//maybe should put ACrawler.forkPool = null; here too ?
	}//action
}//class
