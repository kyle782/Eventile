/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.operations;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.evdb.javaapi.APIConfiguration;
import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;
import com.evdb.javaapi.data.Category;
import com.evdb.javaapi.data.Comment;
import com.evdb.javaapi.data.Event;
import com.evdb.javaapi.data.Image;
import com.evdb.javaapi.data.Link;
import com.evdb.javaapi.data.Performer;
import com.evdb.javaapi.data.Property;
import com.evdb.javaapi.data.SearchResult;
import com.evdb.javaapi.data.Tag;
import com.evdb.javaapi.data.request.EventSearchRequest;
import com.evdb.javaapi.data.response.GenericResponse;

/**
 * Class encapsulating event operations
 * <p>
 * Sample code for dealing with events:
 * <pre>
 * 	//Create our operation objects
	EventOperations eo = new EventOperations();
	Event e = eo.get("E0-123-1234-00");
	
	e.setTitle("New Event Title ");
	eo.modify(e);
	e = eo.get("E0-123-1234-00");
	
	assertEquals(e.getTitle(), "New Event Title ");
	</pre>
 * @author tylerv
 *
 */
public class EventOperations extends BaseOperations{
	
	private static final String EVENTS_GET = "/events/get";
	private static final String EVENTS_NEW = "/events/new";
	private static final String EVENTS_MODIFY = "/events/modify";
	private static final String EVENTS_WITHDRAW = "/events/withdraw";
	private static final String EVENTS_RESTORE = "/events/restore";
	private static final String EVENTS_SEARCH = "/events/search";
	private static final String EVENTS_TAGS_LIST = "/events/tags/list";
	private static final String EVENTS_TAGS_NEW = "/events/tags/new";
	private static final String EVENTS_TAGS_DELETE = "/events/tags/delete";
	private static final String EVENTS_COMMENTS_NEW = "/events/comments/new";
	private static final String EVENTS_COMMENTS_MODIFY = "/events/comments/modify";
	private static final String EVENTS_COMMENTS_DELETE = "/events/comments/delete";
	private static final String EVENTS_LINKS_NEW = "/events/links/new";
	private static final String EVENTS_LINKS_DELETE = "/events/links/delete";
	private static final String EVENTS_IMAGES_ADD = "/events/links/add";
	private static final String EVENTS_IMAGES_REMOVE = "/events/links/remove";
	private static final String EVENTS_PERFORMERS_ADD = "/events/performers/add";
	private static final String EVENTS_PERFORMERS_REMOVE = "/events/performers/remove";
	private static final String EVENTS_PROPERTIES_LIST = "/events/properties/list";
	private static final String EVENTS_PROPERTIES_ADD = "/events/properties/add";
	private static final String EVENTS_PROPERTIES_REMOVE = "/events/properties/remove";
	private static final String EVENTS_CATEGORIES_ADD  = "/events/categories/add";
	private static final String EVENTS_CATEGORIES_REMOVE = "/events/categories/remove";

	
	/**
	 * Get an event from the EVDB website
	 * @param seid SEID to lookup
	 * @return The event object
	 * @throws Exception
	 */
	public Event get(String seid) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Map<String, String> params = new HashMap();
		params.put("id", seid);
		
		InputStream is = serverCommunication.invokeMethod(EVENTS_GET, params);
		
		Event e = (Event)unmarshallRequest(Event.class, is);
		
		return e;
	}
	
	/**
	 * Event search request
	 * <pre>
		EventOperations eo = new EventOperations();
		
		//create the search request for music events in San Diego
		EventSearchRequest esr = new EventSearchRequest();
		esr.setChangeMultiDayStart(1);
		esr.setKeywords("music");
		esr.setLocation("San Diego");
		
		SearchResult sr = eo.search(esr);
		
		assert(sr.getTotalItems() > 1);
		
		//here is our list of results
		List<Event> events = sr.getEvents();	 
	   </pre>
	 * @param searchRequest
	 * @return Search result object
	 * @throws Exception
	 */
	public SearchResult search(EventSearchRequest searchRequest) throws  EVDBRuntimeException, EVDBAPIException  {
		Map<String, String> params = new HashMap();
		
		params.put("keywords", searchRequest.getKeywords());
		params.put("include", searchRequest.getIncludes());
		params.put("location", searchRequest.getLocation());
		params.put("date", searchRequest.getDateRange());
		params.put("category", searchRequest.getCategory());
		params.put("within", String.valueOf(searchRequest.getLocationRadius()));
		params.put("change_multi_day_start", String.valueOf(searchRequest.getChangeMultiDayStart()));
		params.put("units", searchRequest.getLocationUnits());
		params.put("sort_order", searchRequest.getSortOrder().toLowerCase());
		params.put("sort_direction", searchRequest.getSortDirection().toString().toLowerCase());
		params.put("page_size",String.valueOf(searchRequest.getPageSize()));
		params.put("page_number",String.valueOf(searchRequest.getPageNumber()));

		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		params.put("connect_timeout",Integer.toString(searchRequest.getConnectionTimeOut()));
		params.put("read_timeout",Integer.toString(searchRequest.getReadTimeOut()));
		
		InputStream is = serverCommunication.invokeMethod(EVENTS_SEARCH, params );
		
		return (SearchResult)unmarshallRequest(SearchResult.class, is);

	}
	
	/**
	 * Fills in the data for an event given an event object
	 * @param e Event to lookup, must have an SEID
	 * @return The Event object
	 * @throws Exception
	 */
	public Event get(Event e) throws  EVDBRuntimeException, EVDBAPIException   {
		
		return get(e.getSeid());
	}
	
	/**
	 * Creates a new event. Sample code:
	 * <pre>
		EventOperations eo = new EventOperations();
		
		//set our start date
		Calendar calStart = Calendar.getInstance();
		
		calStart.set(2007, 11, 22, 15, 15);
		
		Event e = new Event();
		e.setTitle("API test event");
		e.setDescription("An API Test Event");
		e.setFree(true);
		e.setStartTime(calStart.getTime());;
		
		List<Tag> tagList = new ArrayList();
		
		//add some tags
		tagList.add(new Tag("ttest9"));
		tagList.add(new Tag("music"));
		
		e.setTags(tagList);

		//create the event
		Event newEvent = eo.create(e);
		
		assertNotNull(newEvent.getSeid()); 
	   </pre>
	 * @param e The new event object
	 * @return The updated event object with SEID set
	 * @throws Exception
	 */
	public Event create(Event e) throws  EVDBRuntimeException, EVDBAPIException   {
		if (e.getSeid() != null) {
			throw new EVDBRuntimeException("Create called on an event with an existing SEID");
		}
		
		return createOrModify(e);
	}
	
	/**
	 * List the tags for a given SEID
	 * @param seid
	 * @return List of Tags
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public List<Tag> getTags(String seid) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Event e = (Event)listTags(Event.class, EVENTS_TAGS_LIST, seid);
		
		return e.getTags();
	}
	
	/**
	 * Return a list of properties
	 * @param seid
	 * @return List of Properties
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public List<Property> getProperties(String seid) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Event e = (Event)listProperties(Event.class, EVENTS_PROPERTIES_LIST, seid);
		
		return e.getProperties();
	}
	
	/**
	 * Add tags to an event
	 * @param seid Event SEID
	 * @param tagList The list of tags to add
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addTags(String seid, List<Tag> tagList) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyTags(EVENTS_TAGS_NEW, seid, tagList);
	}
	
	/**
	 * Add tags to an event
	 * @param seid Event SEID
	 * @param tagList The list of tags to add
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteTags(String seid, List<Tag> tagList) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyTags(EVENTS_TAGS_DELETE, seid, tagList);
	}
	
	/**
	 * Add a comment to an event
	 * @param seid
	 * @param comment
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addComment(String seid, Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addComment(EVENTS_COMMENTS_NEW, seid, comment);
	}
	
	/**
	 * Modify a comment
	 * @param comment
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void modifyComment(Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyComment(EVENTS_COMMENTS_MODIFY, comment);
	}
	
	/**
	 * Add an image to the given event
	 * @param seid
	 * @param image
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addImage(String seid, Image image) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addImage(EVENTS_IMAGES_ADD, seid, image);
	}

	/**
	 * Remove the image
	 * @param seid
	 * @param image
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteImage(String seid, Image image) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteImage(EVENTS_IMAGES_REMOVE, seid, image);
	}
	
	/**
	 * Delete a property
	 * @param seid
	 * @param property
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteProperty(String seid, Property property) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteProperty(EVENTS_PROPERTIES_REMOVE, seid, property);
	}
	
	/**
	 * Add a property
	 * @param seid
	 * @param property
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public int addProperty(String seid, Property property) throws  EVDBRuntimeException, EVDBAPIException   {
		
		return super.addProperty(EVENTS_PROPERTIES_ADD, seid, property);
	}
	
	/**
	 * Add a performer
	 * @param seid
	 * @param performer
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addPerformer(String seid, Performer performer) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", seid);
		params.put("performer_id", String.valueOf(performer.getSpid()));
		
		InputStream is = serverCommunication.invokeMethod(EVENTS_PERFORMERS_ADD, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Remove a performer
	 * @param seid
	 * @param performer
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deletePerformer(String seid, Performer performer) throws  EVDBRuntimeException, EVDBAPIException   {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", seid);
		params.put("performer_id", String.valueOf(performer.getSpid()));
		
		InputStream is = serverCommunication.invokeMethod(EVENTS_PERFORMERS_REMOVE, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}

	/**
	 * Add a category to an event
	 * @param seid
	 * @param category
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addCategory(String seid, Category category) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", seid);
		params.put("category_id", String.valueOf(category.getId()));
		
		InputStream is = serverCommunication.invokeMethod(EVENTS_CATEGORIES_ADD, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Remove a category from an event
	 * @param seid
	 * @param category
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteCategory(String seid, Category category) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", seid);
		params.put("category_id", String.valueOf(category.getId()));
		
		InputStream is = serverCommunication.invokeMethod(EVENTS_CATEGORIES_REMOVE, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Delete a comment
	 * @param comment
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteComment(Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteComment(EVENTS_COMMENTS_DELETE, comment);
	}	
	
	/**
	 * Modifies an existing event
	 * @param e The event object
	 * @return The updated event object
	 * @throws Exception
	 */
	public Event modify(Event e) throws  EVDBRuntimeException, EVDBAPIException   {
		if (e.getSeid() == null) {
			throw new EVDBRuntimeException("Modify called on an event without an SEID");
		}
		
		return createOrModify(e);
	}
	
	/**
	 * Add a link to an event
	 * @param seid SEID to add
	 * @param link Link object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addLink(String seid, Link link) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addLink(EVENTS_LINKS_NEW, seid, link);
	}
	
	/**
	 * Remove a link from an event
	 * @param link Link object to remove
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteLink(Link link) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteLink(EVENTS_LINKS_DELETE, link);
	}

	
	/**
	 * Withdraws an event
	 * @param seid SEID of the event to withdraw
	 * @param withdrawNote Note explaining the withdraw
	 * @throws Exception
	 */
	public void withdraw(String seid, String withdrawNote) throws  EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("id", seid);
		params.put("note", withdrawNote);

		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		InputStream is = serverCommunication.invokeMethod(EVENTS_WITHDRAW, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Restores (un-withdraws) an event
	 * @param seid SEID of the event to restore
	 * @throws Exception
	 */
	public void restore(String seid) throws  EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("id", seid);

		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		InputStream is = serverCommunication.invokeMethod(EVENTS_RESTORE, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Wrapper method used by both create and modify to serialize the event data into get data
	 * @param e
	 * @return
	 * @throws EVDBRuntimeException 
	 * @throws EVDBAPIException 
	 * @throws  
	 * @throws Exception
	 */
	private Event createOrModify(Event e) throws EVDBRuntimeException, EVDBAPIException  {
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Map<String, String> params = new HashMap();
		params.put("title", e.getTitle());
		params.put("start_time", e.getStartTime() == null ? "" : sdf.format(e.getStartTime()));
		params.put("stop_time",  e.getStopTime() == null ? "" : sdf.format(e.getStopTime()));
		params.put("tz_olson_path", e.getOlsonPath());
		params.put("all_day", e.isAllDay() ? "1" : "0");
		params.put("description", e.getDescription());
		params.put("privacy", String.valueOf(e.getPrivacy()));
		params.put("price", e.getPrice());
		params.put("free", e.isFree() ? "1" : "0");
		params.put("venue_id", e.getVenue() == null ? "" : e.getVenue().getSvid());
		
		if ((e.getParents() != null) && (e.getParents().size() > 0)) {
			params.put("parent_id", e.getParents().get(0).getSeid());
		}
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", e.getSeid());
		
		String tagString = "";
		if (e.getTags() != null) {
			for (Tag t : e.getTags()) {
				tagString += t.getTitle() + " ";
			}
			params.put("tags", tagString);
		}
		
		String method = e.getSeid() == null ? EVENTS_NEW : EVENTS_MODIFY;
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		GenericResponse gr = (GenericResponse)unmarshallRequest(GenericResponse.class, is);
		
		e.setSeid(gr.getId());
		
		return e;
	}

	
}
