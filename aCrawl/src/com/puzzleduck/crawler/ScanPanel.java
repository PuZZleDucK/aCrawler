/**
 * (c) me & GPL3
 * The GUI panel drawing links... lots of this must go (into other classes)
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
 * @author bminerds aka PuZZleDucK
 *
 */
public class ScanPanel extends JPanel {
	  public static Font thisFont = new Font( "Monospaced.bold" , 0 , 12 );

	  private Random rng = new Random();
	  public void paintComponent(Graphics g)
	  {
	    super.paintComponent(g);  
	    g.setColor(Color.white);  

	    int displayHeight = g.getClipBounds().height;
	    int displayWidth = g.getClipBounds().width;
//	    System.out.println("Display: (" + displayHeight +","+ displayWidth +")");
	    
	    //get list of fonts... put in menu later
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    Font[] allfonts = ge.getAllFonts();
//	    for(int i = 0; i < allfonts.length ;i++ )
//	    {
//	    	System.out.println("Font: " + allfonts[i].getName());
//	    }
	    
	    g.setFont(thisFont);
	    g.fillRect(0, 0 , g.getClipBounds(getVisibleRect()).width, g.getClipBounds(getVisibleRect()).height);//good... handles font and size perfectly
	    
	    Iterator<AppData> appListIterator = ACrawler.appLinkedList.iterator();
	    while( appListIterator.hasNext() )
	    {
	    	AppData thisAppData = (AppData) appListIterator.next();
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
	    	    	AppData.repelObjects(thisAppData, otherAppData);
	    	    	int displayMargin = 60;
	    	    	AppData.repelObjectFromBounds(thisAppData, displayWidth, displayHeight, displayMargin);
	    	    	
	    	    	
		    		
	    	    }//loop other linnks
	    	}//moving/updating link display
	    	
	        g.fillRect((int)thisAppData.getDrawX(), (int)thisAppData.getDrawY()-10, (int)thisAppData.getSizeY(), (int)thisAppData.getSizeX());
			g.setColor(Color.blue);
	        g.drawString(thisAppData.getPrintUrl(), (int)thisAppData.getDrawX()+5, (int)thisAppData.getDrawY()+5);
	        
	        //draw links to and from
	        Iterator<AppData> toLinks = thisAppData.getLinksToApp().iterator();
		    while( toLinks.hasNext() )
		    {
		    	g.setColor(Color.green);
		    	AppData thisToAppData = toLinks.next();
		    	g.drawLine((int)thisAppData.getDrawX()+5, (int)thisAppData.getDrawY(), 
		    			(int)thisToAppData.getDrawX(), (int)thisToAppData.getDrawY()+5);
		    }
	        Iterator<AppData> fromLinks = thisAppData.getLinksFromApp().iterator();
		    while( fromLinks.hasNext() )
		    {
		    	g.setColor(Color.red);
		    	AppData thisFromAppData = fromLinks.next();
		    	g.drawLine((int)thisAppData.getDrawX()+10, (int)thisAppData.getDrawY(), 
		    			(int)thisFromAppData.getDrawX(), (int)thisFromAppData.getDrawY()+10);
		    }
	        
	    }    //Iterator over apps

	  }//paint

	
}//class
