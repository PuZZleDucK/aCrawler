/**
 * (c) me & GPL3
 * search for links in provided URL
 */
package com.puzzleduck.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import javax.swing.JLabel;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * @author PuZZleDucK
 * (c)me & GPL3
 */
public class LinkSearch extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1576867691714283697L;//eclipse made me do it... I'm guessing i should use it... but how ?!?! :)
	private String thisUrl;
	private AppData thisFromApp;
	
	public LinkSearch(String startUrl, AppData fromApp) {
		thisUrl = startUrl;
		thisFromApp = fromApp;
	}

	@Override
	protected void compute() {
	    System.out.println("compute thread");

        try {//check if url valid
			URL urlObject = new URL(thisUrl);
		
			//create dummy app data
	        AppData urlAsApp = new AppData("",thisUrl);
	        urlAsApp.addLinkFromApp(thisFromApp);

			//check if already in completed list   
		    if(! ACrawler.appLinkedList.contains(urlAsApp))
	        {
	        	Parser parser = new Parser(urlObject.openConnection());//get links
	            NodeList list = parser.extractAllNodesThatMatch( new NodeClassFilter(LinkTag.class) );
//	            NodeList listAll = parser.extractAllNodesThatMatch( new NodeClassFilter() );//might have to search everything to get app confirmation?
//	            System.out.println("Parser link v all: " + list.size() +" vs. "+ listAll.size() +")");
	            
	            
	    		//build new recursive actions
//	            List<RecursiveAction> actions = new ArrayList<RecursiveAction>();

	            for(int i = 0; i < list.size(); i++)//for each link
	            {
		    		  //if valid
	                LinkTag currentFoundLink = (LinkTag) list.elementAt(i);
	                if(!currentFoundLink.getLink().isEmpty() )//all valid links
	                {
		                AppData newAppData = new AppData("",currentFoundLink.getLink().toString());
	                	if(!ACrawler.appLinkedList.contains(newAppData))//not in completed or todo already
	                	{
//	    	        	    System.out.println("Selected:" + (String) ACrawler.startingAddressComboBox.getSelectedItem());
//	    	        	    System.out.println("App url :" + newAppData.getAppUrl());
	    	            	//is still in same domain (app store)
	                		if(newAppData.getAppUrl().startsWith((String) ACrawler.startingAddressComboBox.getSelectedItem()))//need to change back later:    (String) ACrawler.startingAddressComboBox.getSelectedItem()
	                		{
	        	        	    ACrawler.forkPool.submit(new LinkSearch(newAppData.getAppUrl(), urlAsApp));
	                		}

	                	}else{ //not in list... else: add back link to other app
	                		AppData oldAppData = ACrawler.appLinkedList.get(ACrawler.appLinkedList.indexOf(newAppData)); //update app data with 'all' info, not just url
	                		oldAppData.addLinkFromApp(urlAsApp);
	            	        urlAsApp.addLinkToApp(thisFromApp);
	                	}
	                }//valid link
	            }//for each link

	            //add to completed list
	        	ACrawler.appLinkedList.add(urlAsApp);
	        }//else page already scanned
			
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//compute

}//class
