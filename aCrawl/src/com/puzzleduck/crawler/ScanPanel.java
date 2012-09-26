/**
 * 
 */
package com.puzzleduck.crawler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

/**
 * @author bminerds
 *
 */
public class ScanPanel extends JPanel {
	  private static Font thisFont = new Font ("Liberation Mono", 0 , 8);// or "Jokerman"
	  // fonts: progenisis flatline Wargames Swift Monospaced.plain "Marked Fool" "Liberation Mono" Delphine 
	  private Random rng = new Random();
	  public void paintComponent(Graphics g)
	  {
	    super.paintComponent(g);  
	    g.setColor(Color.white);  

	    int displayHeight = g.getClipBounds().height/3*2;
	    int displayWidth = g.getClipBounds().width;
	    System.out.println("Display: (" + displayHeight +","+ displayWidth +")");
	    
	    //get list of fonts... put in menu later
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    Font[] allfonts = ge.getAllFonts();
//	    for(int i = 0; i < allfonts.length ;i++ )
//	    {
//	    	System.out.println("Font: " + allfonts[i].getName());
//	    }
	    
	    g.setFont(thisFont);
	    g.fillRect(0, 0 , g.getClipBounds(getVisibleRect()).width, g.getClipBounds(getVisibleRect()).height);
	    
	    Iterator<AppData> i = ACrawler.appLinkedList.iterator();
	    while( i.hasNext() )
	    {
	    	AppData thisAppData = (AppData) i.next();
	    	thisAppData.calcDraw(g);//not location ... things like box size and text size
	    	if(thisAppData.getDrawX() <= 1)//calc location here
	    	{//new location
		    	thisAppData.setDrawX(displayWidth/2 + rng.nextInt(10)-5);//now dumping into the middle... near the middle
		    	thisAppData.setDrawY(displayHeight/2 + rng.nextInt(10)-5);

	    	}else
	    	{//collision moving updating
//	    		repel from each other.
	    		Iterator<AppData> other = ACrawler.appLinkedList.iterator();
	    	    while( other.hasNext() )
	    	    {
	    	    	AppData otherAppData = (AppData) other.next();
	    	    	//small local effect
	    	    	if(   Math.abs(thisAppData.getDrawX() - otherAppData.getDrawX()) < 30 )
	    	    	{
			    		if( thisAppData.getDrawX() < otherAppData.getDrawX() )
			    		{
			    			thisAppData.setDrawX(thisAppData.getDrawX()-1);
			    		}
			    		if( thisAppData.getDrawX() > otherAppData.getDrawX() )
			    		{
			    			thisAppData.setDrawX(thisAppData.getDrawX()+1);
			    		}
	    	    	}//repel x
	    	    	if(   Math.abs(thisAppData.getDrawY() - otherAppData.getDrawY()) < 30 )
	    	    	{
			    		if( thisAppData.getDrawY() < otherAppData.getDrawY() )
			    		{
			    			thisAppData.setDrawY(thisAppData.getDrawY()-1);
			    		}
			    		if( thisAppData.getDrawY() > otherAppData.getDrawY() )
			    		{
			    			thisAppData.setDrawY(thisAppData.getDrawY()+1);
			    		}
	    	    	}//repel y
	    	    	
		    		//repel from bounds
		    		if( thisAppData.getDrawX() < (displayWidth/5) )
		    		{
		    			thisAppData.setDrawX(thisAppData.getDrawX()+2);
		    		}
		    		if( thisAppData.getDrawX() > (displayWidth/5*4) ) // (5 * (5-1))    (n * (n-1))
		    		{
		    			thisAppData.setDrawX(thisAppData.getDrawX()-2);
		    		}
		    		if( thisAppData.getDrawY() < (displayHeight/5) )
		    		{
		    			thisAppData.setDrawY(thisAppData.getDrawY()+2);
		    		}
		    		if( thisAppData.getDrawY() > (displayHeight/5*4) )
		    		{
		    			thisAppData.setDrawY(thisAppData.getDrawY()-2);//getting pushed off the edge due to pressure
		    		}
		    		
		    		
		    		//block at bounds
		    		if( thisAppData.getDrawX() < 0 )
		    		{
		    			thisAppData.setDrawX(0);
		    		}
		    		if( thisAppData.getDrawX() > displayWidth ) // (5 * (5-1))    (n * (n-1))
		    		{
		    			thisAppData.setDrawX(displayWidth);
		    		}
		    		if( thisAppData.getDrawY() < 0 )
		    		{
		    			thisAppData.setDrawY(0);
		    		}
		    		if( thisAppData.getDrawY() > displayHeight )
		    		{
		    			thisAppData.setDrawY(displayHeight);//getting pushed off the edge due to pressure
		    		}
	    	    	
	    	    	
	    	    	
		    		
	    	    }
	    		
	    		
	    		
	    	}
	    	
	        g.fillRect((int)thisAppData.getDrawX(), (int)thisAppData.getDrawY()-10, (int)thisAppData.getSizeY(), (int)thisAppData.getSizeX());
			g.setColor(Color.blue);
	        g.drawString(thisAppData.getPrintUrl(), (int)thisAppData.getDrawX()+5, (int)thisAppData.getDrawY()+5);
	        
	        //draw links to and from
	        Iterator<AppData> toLinks = thisAppData.getLinksToApp().iterator();
		    while( toLinks.hasNext() )
		    {
		    	g.setColor(Color.green);
		    	AppData thisToAppData = toLinks.next();
		    	g.drawLine((int)thisAppData.getDrawX(), (int)thisAppData.getDrawY(), 
		    			(int)thisToAppData.getDrawX(), (int)thisToAppData.getDrawY());
		    }
	        Iterator<AppData> fromLinks = thisAppData.getLinksFromApp().iterator();
		    while( fromLinks.hasNext() )
		    {
		    	g.setColor(Color.red);
		    	AppData thisFromAppData = fromLinks.next();
		    	g.drawLine((int)thisAppData.getDrawX(), (int)thisAppData.getDrawY(), 
		    			(int)thisFromAppData.getDrawX(), (int)thisFromAppData.getDrawY());
		    }
	        
	        

	    }    


	  }//paint

	
	
	
	
}
