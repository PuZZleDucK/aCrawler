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
	    
	    
	      
	    drawX = 50;
	    drawY = 50;
	    Iterator i = ACrawler.appHashSet.iterator();
	    //need to switch to list to have order
	    
	    while( i.hasNext() )
	    {
	    	AppData thisAppData = (AppData) i.next();
	    	thisAppData.calcDraw(g);
	        g.fillRect(drawX, drawY-10, (int)thisAppData.getSizeY(), (int)thisAppData.getSizeX());
			g.setColor(Color.blue);
	        g.drawString(thisAppData.getPrintUrl(), drawX+5, drawY+5);
	        
	        
	      drawX += thisAppData.getSizeY()+20;//20px padding between cells
	      if( drawX >= 600 )
	      {
	        drawX = 50;
	        drawY += 80;
	      }

	    }    


	  }//paint

	
	
	
	
}
