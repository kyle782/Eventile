package com.evdb.javaapi.data.request;

/**
 * Class encapsulating event search operations
 * <p>
 * Sample code for search for events:
 * <pre>
 *      EventOperations eo = new EventOperations();
        EventSearchRequest esr = new EventSearchRequest();

        esr.setLocation("San Diego");
        esr.setDateRange("2012050200-2013052100");
        esr.setPageSize(20);
        esr.setPageNumber(1);
        // These 2 lines will set the timeout to 60 seconds.Normally not needed
        // Unless you are using Google App Engine
        esr.setConnectionTimeout(60000);  // Used with Google App Engine only
        esr.setReadTimeout(60000);        // Used with Google App Engine only
        SearchResult sr = null;
        try {
                sr = eo.search(esr);
                if (sr.getTotalItems() > 1) {

                System.out.println("Total items: " + sr.getTotalItems());
                }
        }catch(EVDBRuntimeException var){
                System.out.println("Opps got runtime an error...");
        } catch( EVDBAPIException var){
                System.out.println("Opps got runtime an error...");
        }

        </pre>
 
 * @author dreiter
 *
 */
public class EventSearchRequest extends SearchRequest {
	
	private String category;
	private String dateRange = "future";
	
	private SortOrder sortOrder = SortOrder.RELEVANCE;
	
	/**
	 * Sort order for searches
	 * @author tylerv
	 *
	 */
	public enum SortOrder {
		RELEVANCE,
		DATE,
		TITLE,
		VENUE_NAME,
		DISTANCE
		
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the dateRange
	 */
	public String getDateRange() {
		return dateRange;
	}

	/**
	 * @param dateRange the dateRange to set
	 */
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	/**
	 * @return the sortOrder
	 */
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
