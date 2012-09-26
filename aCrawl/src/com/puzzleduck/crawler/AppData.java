/**
 * (C)me & GPL3
 */
package com.puzzleduck.crawler;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author puzzleduck
 *
 */
public class AppData {

	  private String appName;
	  private String appUrl;
	  private String appDescription;
	  private LinkedList<AppData> linksToApp;
	  private LinkedList<AppData> linksFromApp; 
	  private double sizeX = 0; 
	  private double sizeY = 0;
	  private float drawX = 0; 
	  private float drawY = 0;
	  private String printUrl;
	  

	/**
	 * storage object for data regarding an application
	 */
	public AppData() {
	    appName = "";
	    appUrl = "NULL-START";
	    linksToApp = new LinkedList<AppData>();
	    linksFromApp = new LinkedList<AppData>();
		sizeX = 0; 
		sizeY = 0;
		drawX = 0; 
		drawY = 0;
	}

	  public AppData(String newName, String newUrl)
	  {
	    appName = newName;
	    appUrl = newUrl;
	    linksToApp = new LinkedList<AppData>();
	    linksFromApp = new LinkedList<AppData>();
		sizeX = 0; 
		sizeY = 0;
		drawX = 0; 
		drawY = 0;
		if(appUrl.length() > 30)
		{
			printUrl = "..." + appUrl.substring(appUrl.length()-30, appUrl.length());
		}else{
			printUrl = appUrl;
		}
	  }
	  
	public String getAppName() 
	{
		return appName;
	}



	public void setAppName(String appName) 
	{
		this.appName = appName;
	}



	public String getAppUrl() 
	{
		return appUrl;
	}



	public void setAppUrl(String appUrl) 
	{
		this.appUrl = appUrl;
	}



	public String getAppDescription() 
	{
		return appDescription;
	}

	public void setAppDescription(String appDescription) 
	{
		this.appDescription = appDescription;
	}
	
	public void addLinkToApp(AppData newLinks)
	{
		linksToApp.add(newLinks);
	}

	public void addLinkFromApp(AppData newLinks)
	{
		linksFromApp.add(newLinks);
	}

	public LinkedList<AppData> getLinksFromApp()
	{
		return linksFromApp;
	}
		
	public LinkedList<AppData> getLinksToApp()
	{
		return linksToApp;
	}
		
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
		result = prime * result + ((appUrl == null) ? 0 : appUrl.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppData other = (AppData) obj;
		if (appName == null) {
			if (other.appName != null)
				return false;
		} else if (!appName.equals(other.appName))
			return false;
		if (appUrl == null) {
			if (other.appUrl != null)
				return false;
		} else if (!appUrl.equals(other.appUrl))
			return false;
		return true;
	}

	public float getDrawX() {
		return drawX;
	}

	public void setDrawX(float drawX) {
		this.drawX = drawX;
	}

	public float getDrawY() {
		return drawY;
	}

	public void setDrawY(float drawY) {
		this.drawY = drawY;
	}

	public double getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public double getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public void calcDraw(Graphics g) {
		//calc size of text (url for now)
		//set height width & background color
		sizeY = g.getFontMetrics().getStringBounds(printUrl, g).getWidth() + 10;// 10 and 10 px margins
		sizeX = g.getFontMetrics().getStringBounds(printUrl, g).getHeight() + 10;
		g.setColor(Color.yellow);
		
	}


	public String getPrintUrl() {
		return printUrl;
	}

	public void setPrintUrl(String printUrl) {
		this.printUrl = printUrl;
	}

}//class
