/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data.request;


/**
 * Performer search request object
 * @author tylerv
 *
 */
public class PerformerSearchRequest extends SearchRequest {
	
	private SortOrder sortOrder = SortOrder.RELEVANCE;
	
	/**
	 * Sort order for searches
	 * @author tylerv
	 *
	 */
	public enum SortOrder {
		RELEVANCE,
		NAME,
		CATEGORY,
		MEMBER_COUNT,
		PERFORMER,
		CREATED
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
