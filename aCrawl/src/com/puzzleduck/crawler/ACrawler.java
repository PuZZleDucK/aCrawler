/**
 * web crawler and search for app info
 * (c) me & GPL3
 */
package com.puzzleduck.crawler;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;

/**
 * @author PuZZleDucK
 * Main class to call ui and hold central DB
 */
public class ACrawler {

	protected final static Collection<AppData> appHashSet = Collections.synchronizedSet(new HashSet<AppData>());

	protected static ForkJoinPool forkPool;
	
	protected static JComboBox<String> startingAddressComboBox;

	protected static JTextArea todoLinkList;
	protected static JTextArea doneLinkList;
	
	/**
	 * @param args 
	 *     address to start web crawl from
	 */
	public static void main(String[] args) {
		// TODOne Init UI
		initUI(args);
		
		// TODOne InitDB
		initDB();

		
	}

	/**
	 *     Initialize db or linked list
	 */
	private static void initDB() {
		// just clearing hashset for now
		appHashSet.clear();
		
	}

	/**
	 *     Init UI
	 * @param args 
	 */
	private static void initUI(String[] args) 
	{
		//main frame
	    JFrame mainWindow = new JFrame();
	    int windowHeight = 600;
	    int windowWidth = 400;
	    mainWindow.setBounds(0, 0, windowWidth, windowHeight);

	    //main panel
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
	    mainWindow.getContentPane().add(mainPanel);

	    JLabel versionLabel = new JLabel("MarketScanner: Mk.2");
	    mainPanel.add(versionLabel);
	    mainWindow.setTitle("Scan Window");
		

	    startingAddressComboBox = new JComboBox<String>();
//		if(args[0] == null)
//		{
		    startingAddressComboBox.insertItemAt("http://play.google.com/store",0);
//		}else
//		{
//		    startingAddressComboBox.insertItemAt(args[0],0);
//		    startingAddressComboBox.insertItemAt("http://play.google.com/store",3);
//		}
	    startingAddressComboBox.insertItemAt("http://www.amazon.com/mobile-apps",1);
	    startingAddressComboBox.insertItemAt("http://www.appbrain.com/",2);
	    startingAddressComboBox.setSelectedIndex(0);

		JButton cdgCrawlButton = new JButton("Config Crawl");
		JButton cfgDbButton = new JButton("Config DB");
		JButton startButton = new JButton("Start Crawl");
	    JButton stopButton = new JButton("Stop Crawl");
	    

	    mainPanel.add(cdgCrawlButton);
	    mainPanel.add(cfgDbButton);
	    mainPanel.add(startingAddressComboBox);

//	    JTextArea doneText = new JTextArea();
//	    JPanel textPanel = new JPanel();
//	    textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.LINE_AXIS));
//	    textPanel.add(doneText);
//	    mainWindow.add(textPanel);

	    
	    mainPanel.add(startButton);
	    mainPanel.add(stopButton);


//	    
//	    todoLinkList = new JList();
//	    todoLinkList.setVisibleRowCount(3);
//	    JScrollPane todoListScroller = new JScrollPane(todoLinkList);
//	    todoListScroller.setPreferredSize(new Dimension(20, 10));
//
//	    mainWindow.add(todoListScroller);
		
	    mainWindow.setVisible(true);


	    System.out.println("done");
		//in button events:
//	    cdgCrawlButton.addActionListener();//TODO
//	    cfgDbButton.addActionListener();//TODO
	    // TODO Init exex/fork
		// TODO Init start exec
	    startButton.addActionListener(new ACrawlStarter());
	    		//stop exec
//	    stopButton.addActionListener();//TODO 

		
	}

}
