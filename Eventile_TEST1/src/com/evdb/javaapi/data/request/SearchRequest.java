/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data.request;

/**
 * Simple Search Request Object.  Perform searches using one of the class-spectific
 * search request objects
 * 
 * @author tylerv
 *
 */
public abstract class SearchRequest {
	
    /**
     * Keywords for the search
     */
	private String keywords;
	
    /**
     * Includes to return for the search
     */
	private String includes;

	/**
	 * Location to run the search
	 */
	private String location;
	
	/**
	 * Location radius
	 */
	private int locationRadius;

	/**
	 *  change_multi_day_start parameter
	 */
	private int changemultidaystart = 0;
	
	/**
	 * Location units, either mi or km.  Defaults to mi
	 */
	private String locationUnits = "mi";

	/**
	 * Sort direction, ascending or descending.  Defaults to ascending
	 */
	private SortDirection sortDirection = SortDirection.ASCENDING;
	
	/**
	 * Page size, defaults to 10
	 */
	private int pageSize = 10;
	
	/**
	 * Connection Timeout in ms, defaults to 0 ( none specified)
	 */
	private int connectTimeOut = 0;
	
	/**
	 * Read Timeout in ms, defaults to 0 (none specified)
	 */
	private int readTimeOut = 0;
	
	/**
	 * Page number, defaults to 1
	 */
	private int pageNumber = 1;
	
	public abstract String getSortOrder(); 
	
	
	/**
	 * Types of Sort Direction
	 * @author tylerv
	 *
	 */
	public enum SortDirection {
		ASCENDING,
		DESCENDING 
	}


	/**
	 * Return search phrase
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * Set the search phrase
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * Return includes phrase
	 * @return the incdludes
	 */
	public String getIncludes() {
		return includes;
	}

	/**
	 * Set the search includes
	 * @param includes the includes to set 
	 */
	public void setIncludes(String includes) {
		this.includes = includes;
	}

	/**
	 * Get the change_multi_day_start parameter
	 * @return the change_mulit_day_start search parameter
	 */
	public int getChangeMultiDayStart() {
		return changemultidaystart;
	}

	/**
	 * Set the change_multi_day_start parameter
	 * @param change_mulit_day_start search parameter
	 */
	public void setChangeMultiDayStart(int val) {
		this.changemultidaystart = val;
	}

	/**
	 * Get the search location
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Get the connection timeout
	 * @return the connectTimeOut (in ms)
	 */
	public int getConnectionTimeOut() {
		return connectTimeOut;
	}

	/**
	 * Get the read timeout
	 * @return the readTimeOut (in ms)
	 */
	public int getReadTimeOut() {
		return readTimeOut;
	}

	/**
	 * Set the connection timeout (for users of Google App Engine that need to change the default of 5 seconds)<br>Non Google App Engine users should not need to set this.
	 * 
	 * @param timeout the connectTimeOut to set (in ms) (0 is the defualt which disables the timeout)
	 */
	public void setConnectionTimeout(int timeout) {
		this.connectTimeOut = timeout;
	}

	/**
	 * Set the read timeout (for users of Google App Engine that need to change the default of 5 seconds)<br>Non Google App Engine users should not need to set this.
	 * 
	 * @param timeout the readTimeOut to set (in ms) (0 is the defualt which disables the timeout)
	 */
	public void setReadTimeout(int timeout) {
		this.readTimeOut = timeout;
	}

	/**
	 * Set the location.  Empty (or null) means search all locations
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	/**
	 * Get the location radius
	 * @return the locationRadius
	 */
	public int getLocationRadius() {
		return locationRadius;
	}


	/**
	 * Set the location radius
	 * @param locationRadius the locationRadius to set
	 */
	public void setLocationRadius(int locationRadius) {
		this.locationRadius = locationRadius;
	}


	/**
	 * Get the location units
	 * @return the locationUnits
	 */
	public String getLocationUnits() {
		return locationUnits;
	}

	/**
	 * Set the location units.  Either "mi" or "km".  Default is "mi"
	 * @param locationUnits the locationUnits to set
	 */
	public void setLocationUnits(String locationUnits) {
		this.locationUnits = locationUnits;
	}


	/**
	 * Page number of search results
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * Set the page number of search results to return.  Default is 1
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * Page size, default is 10
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}


	/**
	 * Number of items displayed on a page of search results. Default is 10
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	/**
	 * Sort direction
	 * @return the sortDirection
	 */
	public SortDirection getSortDirection() {
		return sortDirection;
	}


	/**
	 * Set sort direction.  Default is SortDirection.ASCENDING
	 * @param sortDirection the sortDirection to set
	 */
	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}
	
}
