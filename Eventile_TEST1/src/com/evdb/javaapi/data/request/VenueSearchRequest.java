/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data.request;


/**
 * Venue search request impl
 * @author tylerv
 *
 */
public class VenueSearchRequest extends SearchRequest {

	private SortOrder sortOrder = SortOrder.POPULARITY;
	
	/**
	 * Sort order for searches
	 * @author tylerv
	 *
	 */
	public enum SortOrder {
		RELEVANCE,
		DATE,
		EVENT_COUNT,
		POPULARITY
	}

	/* (non-Javadoc)
	 * @see com.evdb.javaapi.data.request.SearchRequest#getSortOrder()
	 */
	@Override
	public String getSortOrder() {
		return sortOrder.toString().toLowerCase();
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

}
