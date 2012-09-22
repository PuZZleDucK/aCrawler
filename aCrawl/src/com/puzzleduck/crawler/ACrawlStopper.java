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
public class ACrawlStopper implements ActionListener {

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		ACrawler.forkPool.shutdownNow();
		System.out.println( "Crawl Shutdown.");
		

	}

}
