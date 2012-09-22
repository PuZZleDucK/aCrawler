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
	
	public LinkSearch(String startUrl) {
		// TODOne
		thisUrl = startUrl;
	}

	@Override
	protected void compute() {
		// TODO Auto-generated method stub
	    System.out.println("compute thread");

		//check if url valid
        try {
			URL urlObject = new URL(thisUrl);
		
			//create dummy app data
	        AppData urlAsApp = new AppData("",thisUrl);
		    System.out.println("url");

			//check if already in completed list
	        if(! ACrawler.appHashSet.contains(urlAsApp))
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

		    		  //if valid
	                LinkTag currentFoundLink = (LinkTag) list.elementAt(i);
	                if(!currentFoundLink.getLink().isEmpty() )//all valid links
	                {
		        	    System.out.print("I.");
		                AppData newAppData = new AppData("",currentFoundLink.getLink().toString());
			    		  //not in completed or todo already
	                	if(!ACrawler.appHashSet.contains(newAppData))
	                	{
	    	        	    System.out.print("N.");
//	    	        	    System.out.println("Selected:" + (String) ACrawler.startingAddressComboBox.getSelectedItem());
//	    	        	    System.out.println("App url :" + newAppData.getAppUrl());
	    	            	//is still in same domain (app store)
	                		if(newAppData.getAppUrl().startsWith("https://play.google.com/store"))//need to change back later:    (String) ACrawler.startingAddressComboBox.getSelectedItem()
	                		{
	        	        	    System.out.print("K:...\n");
	                			actions.add(new LinkSearch(newAppData.getAppUrl()));
	                			//ACrawler.todoLinkList.add(new JLabel(newAppData.getAppUrl()));
	                		}

	                		
	                	}//not in list
	                }//valid link
	            }//for each link

	            //add to completed list
	        	ACrawler.appHashSet.add(urlAsApp);
    		//	ACrawler.doneLinkList.add(new JLabel(urlAsApp.getAppUrl()));
	        	
	        	
	        	
	        	
	        	//fork/exec children
	        	invokeAll(actions);
	        	
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
