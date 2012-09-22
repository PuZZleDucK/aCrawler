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
	    
	    
	    g.setColor(Color.blue);  
	    drawX = 10;
	    drawY = 10;
	    Iterator i = ACrawler.appHashSet.iterator();
	    while( i.hasNext() )
	    {
	      AppData o = (AppData) i.next(); 
	      g.fillRect(drawX, drawY, 5, 5);
	      g.drawString(o.getAppUrl(), drawX, drawY);
	      drawX += 15;
	      if( drawX >= 200 )
	      {
	        drawX = 10;
	        drawY += 20;
	      }

	    }    


	  }//paint

	
	
	
	
}
