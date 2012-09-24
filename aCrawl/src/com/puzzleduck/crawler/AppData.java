/**
 * (C)me & GPL3
 */
package com.puzzleduck.crawler;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author puzzleduck
 *
 */
public class AppData {

	  private String appName;
	  private String appUrl;
	  private String appDescription;
	  private Collection<AppData> linksToApp;
	  private Collection<AppData> linksFromApp; 
	  private int sizeX = 0; 
	  private int sizeY = 0;
	  private int drawX = 10; 
	  private int drawY = 10;
	
	

	/**
	 * storage object for data regarding an application
	 */
	public AppData() {
	    appName = "";
	    appUrl = "";
	    linksToApp = new HashSet<AppData>();
	    linksFromApp = new HashSet<AppData>();
	}

	  public AppData(String newName, String newUrl)
	  {
	    appName = newName;
	    appUrl = newUrl;
	    linksToApp = new HashSet<AppData>();
	    linksFromApp = new HashSet<AppData>();
	  }
	  

	  public AppData(String newName, String newUrl, Collection<AppData> linksTo, Collection<AppData> linksFrom)
	  {
	    appName = newName;
	    appUrl = newUrl;
	    linksToApp = linksTo;
	    linksFromApp = linksFrom;
	  }


	public String getAppName() {
		return appName;
	}



	public void setAppName(String appName) {
		this.appName = appName;
	}



	public String getAppUrl() {
		return appUrl;
	}



	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}



	public String getAppDescription() {
		return appDescription;
	}



	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}

	public void addLinksToApp(Collection<AppData> newLinks)
	{
		linksToApp.addAll(newLinks);
	}
	
	public void addLinksFromApp(Collection<AppData> newLinks)
	{
		linksFromApp.addAll(newLinks);
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

	public int getDrawX() {
		return drawX;
	}

	public void setDrawX(int drawX) {
		this.drawX = drawX;
	}

	public int getDrawY() {
		return drawY;
	}

	public void setDrawY(int drawY) {
		this.drawY = drawY;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	
	
	
	
	
}
