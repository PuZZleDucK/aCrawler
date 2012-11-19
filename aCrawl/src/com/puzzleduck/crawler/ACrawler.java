/**
 * (c) me & GPL3
 * Web crawler and search for app info, main startup... init and build GUI
 */
package com.puzzleduck.crawler;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import javax.swing.UIManager;

/**
 * @author PuZZleDucK
 * Main class to call ui and hold central DB
 */
public class ACrawler 
{
	protected final static List<AppData> appLinkedList = Collections.synchronizedList(new LinkedList<AppData>());
	protected static ForkJoinPool forkPool;

	protected static JComboBox<String> startingAddressComboBox;
	protected static JComboBox<String> fontComboBox;
	protected static JComboBox<Integer> fontSizeComboBox;
	protected static JFrame mainWindow;
	protected static ScanPanel scanPanel;
	
	/**
	 * @param args 
	 *     address to start web crawl from
	 */
	public static void main(String[] args) 
	{
		initUI(args);
		initDB();
	}

	/**
	 *     Initialize db or linked list
	 */
	protected static void initDB() 
	{
		// just clearing list for now
		appLinkedList.clear();
	}

	/**
	 *     Init UI
	 * @param args 
	 */
	private static void initUI(String[] args) 
	{

		//init l&f
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");// or "com.sun.java.swing.plaf.gtk.GTKLookAndFeel" or "com.sun.java.swing.plaf.motif.MotifLookAndFeel" or "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"   
		} catch (Exception e) {
			e.printStackTrace();// doing nothing... auto fallback to system default
		}
		
		//main frame
	    mainWindow = new JFrame();
	    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int windowHeight = 800;
	    int windowWidth = 800;
	    mainWindow.setBounds(0, 0, windowWidth, windowHeight);

	    //main panel
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
	    mainWindow.getContentPane().add(mainPanel);

	    JLabel versionLabel = new JLabel("MarketScanner: Mk.3");
	    mainPanel.add(versionLabel);
	    mainWindow.setTitle("Scan Window");
		
	    //graphics panel
	    scanPanel = new ScanPanel();
	    scanPanel.setPreferredSize(new Dimension(800,600));
	    mainPanel.add(scanPanel);
	    
	    startingAddressComboBox = new JComboBox<String>();
		startingAddressComboBox.insertItemAt("http://play.google.com/store",0);
	    startingAddressComboBox.insertItemAt("http://www.amazon.com/mobile-apps",1);
	    startingAddressComboBox.insertItemAt("http://www.appbrain.com/",2);
	    startingAddressComboBox.setSelectedIndex(0);
	    JPanel lowerPanel = new JPanel();
	    JPanel comboPanel = new JPanel();
	    comboPanel.add(startingAddressComboBox);
	    lowerPanel.add(comboPanel);
	    fontComboBox = new JComboBox<String>();	  // fonts: ""      "" ""   or ""
	    fontComboBox.insertItemAt("Liberation Mono",0);
	    fontComboBox.insertItemAt("progenisis",1);
	    fontComboBox.insertItemAt("flatline",2);
	    fontComboBox.insertItemAt("Wargames",3);
	    fontComboBox.insertItemAt("Swift",4);
	    fontComboBox.insertItemAt("Monospaced.bold",5);
	    fontComboBox.insertItemAt("Marked Fool",6);
	    fontComboBox.insertItemAt("Liberation Mono",7);
	    fontComboBox.insertItemAt("Delphine",8);
	    fontComboBox.insertItemAt("Jokerman",9);
	    fontComboBox.insertItemAt("DroidMono",10);
	    fontComboBox.insertItemAt("Droid Mono",11);
	    fontComboBox.setSelectedIndex(5);
	    fontComboBox.addActionListener(new ACrawlFont());
	    comboPanel.add(fontComboBox);
	    fontSizeComboBox = new JComboBox<Integer>();
	    fontSizeComboBox.insertItemAt(new Integer( 6),0);
	    fontSizeComboBox.insertItemAt(new Integer( 8),1);
	    fontSizeComboBox.insertItemAt(new Integer(12),2);
	    fontSizeComboBox.insertItemAt(new Integer(14),3);
	    fontSizeComboBox.insertItemAt(new Integer(16),4);
	    fontSizeComboBox.insertItemAt(new Integer(18),5);
	    fontSizeComboBox.insertItemAt(new Integer(20),6);
	    fontSizeComboBox.insertItemAt(new Integer(22),7);
	    fontSizeComboBox.setSelectedIndex(2);
	    fontSizeComboBox.addActionListener(new ACrawlFont());
	    comboPanel.add(fontSizeComboBox);

		JButton cdgCrawlButton = new JButton("Config Crawl");
		JButton cfgDbButton = new JButton("Config DB");
		JButton startButton = new JButton("Start Crawl");
	    JButton stopButton = new JButton("Stop Crawl");
	    
	    lowerPanel.add(cdgCrawlButton);
	    lowerPanel.add(cfgDbButton);

	    lowerPanel.add(startButton);
	    lowerPanel.add(stopButton);
	    lowerPanel.setPreferredSize(new Dimension(10,10));

	    mainPanel.add(lowerPanel);

	    startButton.addActionListener(new ACrawlStarter());
	    stopButton.addActionListener(new ACrawlStopper());
	    mainWindow.setVisible(true);

	    //start canvas draw routine:...moving to alt thread...
	    ForkJoinPool uiPool = new ForkJoinPool(2);
	    uiPool.invoke( new BackgroundPaint() );
	    
	}//init ui

}//class
