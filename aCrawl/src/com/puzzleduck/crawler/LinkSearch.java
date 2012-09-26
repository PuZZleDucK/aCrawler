/**
 * 
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
 * @author bminerds
 *
 */
public class LinkSearch extends RecursiveAction {

	private String thisUrl;
	private AppData thisFromApp;
	
	public LinkSearch(String startUrl, AppData fromApp) {
		// TODOne
		thisUrl = startUrl;
		thisFromApp = fromApp;
	}

	@Override
	protected void compute() {
		// TODOne Auto-generated method stub
	    System.out.println("compute thread");

		//check if url valid
        try {
			URL urlObject = new URL(thisUrl);
		
			//create dummy app data
	        AppData urlAsApp = new AppData("",thisUrl);
	        urlAsApp.addLinkFromApp(thisFromApp);
		    System.out.println("url");

			//check if already in completed list   
	        //if(! ACrawler.appHashSet.contains(urlAsApp))
		    if(! ACrawler.appLinkedList.contains(urlAsApp))
	        {
	    	    System.out.println("new");
	    	    //get links
	        	Parser parser = new Parser(urlObject.openConnection());
	            NodeList list = parser.extractAllNodesThatMatch( new NodeClassFilter(LinkTag.class) );

	    		//build new recursive actions
	            List<RecursiveAction> actions = new ArrayList<RecursiveAction>();

	            //for each link
	            for(int i = 0; i < list.size(); i++)
	            {
	        	    System.out.print("L.");
	        	    //paint much more often than once per page:
	        	    ACrawler.scanPanel.repaint();

		    		  //if valid
	                LinkTag currentFoundLink = (LinkTag) list.elementAt(i);
	                if(!currentFoundLink.getLink().isEmpty() )//all valid links
	                {
		        	    System.out.print("I.");
		                AppData newAppData = new AppData("",currentFoundLink.getLink().toString());
			    		  //not in completed or todo already
	                	if(!ACrawler.appLinkedList.contains(newAppData))
	                	{
	    	        	    System.out.print("N.");
//	    	        	    System.out.println("Selected:" + (String) ACrawler.startingAddressComboBox.getSelectedItem());
//	    	        	    System.out.println("App url :" + newAppData.getAppUrl());
	    	            	//is still in same domain (app store)
	                		if(newAppData.getAppUrl().startsWith("https://play.google.com/store"))//need to change back later:    (String) ACrawler.startingAddressComboBox.getSelectedItem()
	                		{
	        	        	    System.out.print("K:...\n");
//	                			actions.add(new LinkSearch(newAppData.getAppUrl()));//submit now instead
	        	        	    ACrawler.forkPool.submit(new LinkSearch(newAppData.getAppUrl(), urlAsApp));
	                			//ACrawler.todoLinkList.add(new JLabel(newAppData.getAppUrl()));
	                		}

	                		
	                	}else{ //not in list... else: add back link to other app
//	                		ACrawler.appHashSet.remove(newAppData);
//	                		ACrawler.appHashSet.add(newAppData);... now appLinkedList
	                		AppData oldAppData = ACrawler.appLinkedList.get(ACrawler.appLinkedList.indexOf(newAppData)); //update app data with 'all' info, not just url
	                		oldAppData.addLinkFromApp(urlAsApp);
	            	        urlAsApp.addLinkToApp(thisFromApp);
	                	}
	                }//valid link
	            }//for each link

	            //add to completed list
	        	ACrawler.appLinkedList.add(urlAsApp);
    		//	ACrawler.doneLinkList.add(new JLabel(urlAsApp.getAppUrl()));
	        	
	    	    ACrawler.scanPanel.repaint();

//	    	    ACrawler.scanPanel.updateUI();
//	    	    ACrawler.scanPanel.setVisible(true);
//	    	    ACrawler.scanPanel.validate();

	        	
	        	//fork/exec children
	        	
//	        	invokeAll(actions);
	        	//submit eirlier now
	        }
			
        
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

        
		
		
		//invoke children
		
		
		
		
		
		
	}

}
