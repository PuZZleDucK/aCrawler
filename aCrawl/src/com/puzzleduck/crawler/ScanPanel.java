/**
 * 
 */
package com.puzzleduck.crawler;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

/**
 * @author bminerds
 *
 */
public class ScanPanel extends JPanel {

	  private int drawX = 50;
	  private int drawY = 50;

	  
	  public void paintComponent(Graphics g)
	  {
	    super.paintComponent(g);  
	    g.setColor(Color.white);  
	    g.fillRect(0, 0 , g.getClipBounds(getVisibleRect()).width, g.getClipBounds(getVisibleRect()).height);
	    
	    
	      
//	    drawX = 50;
//	    drawY = 50;
	    
	    Iterator<AppData> i = ACrawler.appLinkedList.iterator();
//	    Iterator i = ACrawler.appHashSet.iterator();
	    //need to switch to list to have order
	    
	    while( i.hasNext() )
	    {
	    	AppData thisAppData = (AppData) i.next();
	    	thisAppData.calcDraw(g);//not location
	    	
	    	if(thisAppData.getDrawX() <= 10)//calc location here
	    	{
		    	thisAppData.setDrawX(drawX);
		    	thisAppData.setDrawY(drawY);
		    	
		    	drawX += thisAppData.getSizeY()+20;//20px padding between cells
		    	if( drawX >= 600 )
		      	{
		    		drawX = 50;
		        	drawY += 30;
		      	}
		    	
	    	}else{//collision moving????
	    		
	    	}
	    	
	        g.fillRect(thisAppData.getDrawX(), thisAppData.getDrawY()-10, (int)thisAppData.getSizeY(), (int)thisAppData.getSizeX());
			g.setColor(Color.blue);
	        g.drawString(thisAppData.getPrintUrl(), thisAppData.getDrawX()+5, thisAppData.getDrawY()+5);
	        
	        //draw links to and from
	        Iterator<AppData> toLinks = thisAppData.getLinksToApp().iterator();
		    while( toLinks.hasNext() )
		    {
		    	g.setColor(Color.green);
		    	AppData thisToAppData = toLinks.next();
		    	g.drawLine(thisAppData.getDrawX(), thisAppData.getDrawY(), 
		    			thisToAppData.getDrawX(), thisToAppData.getDrawY());
		    }
	        Iterator<AppData> fromLinks = thisAppData.getLinksFromApp().iterator();
		    while( fromLinks.hasNext() )
		    {
		    	g.setColor(Color.red);
		    	AppData thisFromAppData = fromLinks.next();
		    	g.drawLine(thisAppData.getDrawX(), thisAppData.getDrawY(), 
		    			thisFromAppData.getDrawX(), thisFromAppData.getDrawY());
		    }
	        
	        

	    }    


	  }//paint

	
	
	
	
}
