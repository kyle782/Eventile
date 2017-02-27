/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.evdb.javaapi.data.response.GenericErrorResponse;

/**
 * Generic search result object
 * @author tylerv
 *
 */
@XmlRootElement(name="search")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({GenericErrorResponse.class})
public class SearchResult {
	
    /**
     * Total number of search items returned
     */
	@XmlElement(name="total_items")
	private int totalItems;
	
	/**
	 * Page size requested
	 */
	@XmlElement(name="page_size")
	private int pageSize;
	
	/**
	 * Total number of pages given the current page size
	 */
	@XmlElement(name="page_count")
	private int pageCount;
	
	/**
	 * Current page number
	 */
	@XmlElement(name="page_number")
	private int pageNumber;
	
	/**
	 * Number of items on the current page
	 */
	@XmlElement(name="page_items")
	private int pageItems;

	@XmlElement(name="first_item")
	private int firstItem;
	
	@XmlElement(name="last_item")
	private int lastItem;
	
	/**
	 * Time it took for the search to complete in milliseconds
	 */
	@XmlElement(name="search_time")
	private double searchTime;
	
	/**
	 * Any events returned by the search
	 */
	@XmlElementWrapper(name="events")
	@XmlElement(name="event")
	private List<Event> events;

	/**
	 * Any venues returned by the search
	 */
	@XmlElementWrapper(name="venues")
	@XmlElement(name="venue")
	private List<Venue> venues;

	/**
	 * Performers returned by the search
	 */
	@XmlElementWrapper(name="performers")
	@XmlElement(name="performer")
	private List<Performer> performers;
	
	/**
	 * Demands returned by the search
	 */
	@XmlElementWrapper(name="demands")
	@XmlElement(name="demand")
	private List<Demand> demands;
	
	/**
	 * @return the events
	 */
	public List<Event> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	/**
	 * @return the firstItem
	 */
	public int getFirstItem() {
		return firstItem;
	}

	/**
	 * @param firstItem the firstItem to set
	 */
	public void setFirstItem(int firstItem) {
		this.firstItem = firstItem;
	}

	/**
	 * @return the lastItem
	 */
	public int getLastItem() {
		return lastItem;
	}

	/**
	 * @param lastItem the lastItem to set
	 */
	public void setLastItem(int lastItem) {
		this.lastItem = lastItem;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the pageItems
	 */
	public int getPageItems() {
		return pageItems;
	}

	/**
	 * @param pageItems the pageItems to set
	 */
	public void setPageItems(int pageItems) {
		this.pageItems = pageItems;
	}

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the searchTime
	 */
	public double getSearchTime() {
		return searchTime;
	}

	/**
	 * @param searchTime the searchTime to set
	 */
	public void setSearchTime(double searchTime) {
		this.searchTime = searchTime;
	}

	/**
	 * @return the totalItems
	 */
	public int getTotalItems() {
		return totalItems;
	}

	/**
	 * @param totalItems the totalItems to set
	 */
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	/**
	 * @return the venues
	 */
	public List<Venue> getVenues() {
		return venues;
	}

	/**
	 * @param venues the venues to set
	 */
	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

	/**
	 * @return the performers
	 */
	public List<Performer> getPerformers() {
		return performers;
	}

	/**
	 * @param performers the performers to set
	 */
	public void setPerformers(List<Performer> performers) {
		this.performers = performers;
	}

	/**
	 * @return the demands
	 */
	public List<Demand> getDemands() {
		return demands;
	}

	/**
	 * @param demands the demands to set
	 */
	public void setDemands(List<Demand> demands) {
		this.demands = demands;
	}


}
