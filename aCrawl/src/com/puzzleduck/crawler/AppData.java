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

	
	
	
	
	
}
