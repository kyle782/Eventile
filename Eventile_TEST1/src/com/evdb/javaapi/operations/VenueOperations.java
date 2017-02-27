/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.operations;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.evdb.javaapi.APIConfiguration;
import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;
import com.evdb.javaapi.data.Comment;
import com.evdb.javaapi.data.Image;
import com.evdb.javaapi.data.Link;
import com.evdb.javaapi.data.Property;
import com.evdb.javaapi.data.SearchResult;
import com.evdb.javaapi.data.Tag;
import com.evdb.javaapi.data.Venue;
import com.evdb.javaapi.data.request.VenueSearchRequest;
import com.evdb.javaapi.data.response.GenericResponse;

/**
 * Venue object operations
 * @author tylerv
 *
 */
public class VenueOperations extends BaseOperations {
	
	private static final String VENUES_GET = "/venues/get";
	private static final String VENUES_NEW = "/venues/new";
	private static final String VENUES_MODIFY = "/venues/modify";
	private static final String VENUES_WITHDRAW = "/venues/withdraw";
	private static final String VENUES_RESTORE = "/venues/restore";
	private static final String VENUES_SEARCH = "/venues/search";
	private static final String VENUES_TAGS_LIST = "/venues/tags/list";
	private static final String VENUES_TAGS_NEW = "/venues/tags/new";
	private static final String VENUES_TAGS_DELETE = "/venues/tags/delete";
	private static final String VENUES_COMMENTS_NEW = "/venues/comments/new";
	private static final String VENUES_COMMENTS_MODIFY = "/venues/comments/modify";
	private static final String VENUES_COMMENTS_DELETE = "/venues/comments/delete";
	private static final String VENUES_LINKS_NEW = "/venues/links/new";
	private static final String VENUES_LINKS_DELETE = "/venues/links/delete";
	private static final String VENUES_IMAGES_ADD = "/venues/links/add";
	private static final String VENUES_IMAGES_REMOVE = "/venues/links/remove";
	private static final String VENUES_PROPERTIES_ADD = "/venues/properties/add";
	private static final String VENUES_PROPERTIES_REMOVE = "/venues/properties/remove";

	/**
	 * Return a venue from an SVID
	 * @param svid SVID of the venue to lookup 
	 * @return the venue object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public Venue get(String svid) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Map<String, String> params = new HashMap();
		params.put("id", svid);
		
		InputStream is = serverCommunication.invokeMethod(VENUES_GET, params);
		
		Venue v = (Venue)unmarshallRequest(Venue.class, is);
		
		return v;
	}
	
	/**
	 * Create a new venue
	 * @param v
	 * @return The newly created Venue
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public Venue create(Venue v) throws  EVDBRuntimeException, EVDBAPIException   {
		if (v.getSvid() != null) {
			throw new EVDBRuntimeException("Create called on an venue with an existing SVID");
		}
		
		return createOrModify(v);
	}
	
	/**
	 * Modify a venue
	 * @param v
	 * @return Venue the modified Venue
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public Venue modify(Venue v) throws  EVDBRuntimeException, EVDBAPIException   {
		if (v.getSvid() == null) {
			throw new EVDBRuntimeException("Modify called on an venue without an SVID");
		}
		
		return createOrModify(v);
	}
	
	/**
	 * Withdraws an venue
	 * @param svid SVID of the venue to withdraw
	 * @param withdrawNote Note explaining the withdraw
	 * @throws Exception
	 */
	public void withdraw(String svid, String withdrawNote) throws  EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("id", svid);
		params.put("note", withdrawNote);

		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		InputStream is = serverCommunication.invokeMethod(VENUES_WITHDRAW, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Restores (i.e. un-withdraws) a venue
	 * @param svid SVID of the venue to restore
	 * @throws Exception
	 */
	public void restore(String svid) throws  EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("id", svid);

		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		InputStream is = serverCommunication.invokeMethod(VENUES_RESTORE, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Add tags to a venue
	 * @param svid SVID
	 * @param tagList The list of tags to add
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addTags(String svid, List<Tag> tagList) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyTags(VENUES_TAGS_NEW, svid, tagList);
	}
	
	/**
	 * Add tags to an venue
	 * @param svid Venue SVID
	 * @param tagList The list of tags to add
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteTags(String svid, List<Tag> tagList) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyTags(VENUES_TAGS_DELETE, svid, tagList);
	}
	
	/**
	 * List the tags for a given SVID
	 * @param svid
	 * @return List<Tag>
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public List<Tag> getTags(String svid) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Venue e = (Venue)listTags(Venue.class, VENUES_TAGS_LIST, svid);
		
		return e.getTags();
	}
	
	/**
	 * Add a comment to an event
	 * @param svid
	 * @param comment
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addComment(String svid, Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addComment(VENUES_COMMENTS_NEW, svid, comment);
	}
	
	/**
	 * Modify a comment
	 * @param comment
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void modifyComment(Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyComment(VENUES_COMMENTS_MODIFY, comment);
	}
	
	/**
	 * Delete a comment
	 * @param comment
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteComment(Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteComment(VENUES_COMMENTS_DELETE, comment);
	}
	
	/**
	 * Add a link to the venue
	 * @param svid the venue id 
	 * @param link
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addLink(String svid, Link link) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addLink(VENUES_LINKS_NEW, svid, link);
	}
	
	/**
	 * Remove a link from the venue
	 * @param link
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteLink(Link link) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteLink(VENUES_LINKS_DELETE, link);
	}
	
	/**
	 * Add an image to the given event
	 * @param svid
	 * @param image
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addImage(String svid, Image image) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addImage(VENUES_IMAGES_ADD, svid, image);
	}

	/**
	 * Remove the image
	 * @param svid
	 * @param image
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteImage(String svid, Image image) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteImage(VENUES_IMAGES_REMOVE, svid, image);
	}
	
	/**
	 * Delete a property
	 * @param svid
	 * @param property
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteProperty(String svid, Property property) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteProperty(VENUES_PROPERTIES_REMOVE, svid, property);
	}
	
	/**
	 * Add a property
	 * @param svid
	 * @param property
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public int addProperty(String svid, Property property) throws  EVDBRuntimeException, EVDBAPIException   {
		
		return super.addProperty(VENUES_PROPERTIES_ADD, svid, property);
	}

	/**
	 * Venue search request
	 * @param searchRequest
	 * @return Search result object
	 * @throws Exception
	 */
	public SearchResult search(VenueSearchRequest searchRequest) throws  EVDBRuntimeException, EVDBAPIException  {
		Map<String, String> params = new HashMap();
		
		params.put("keywords", searchRequest.getKeywords());
		params.put("location", searchRequest.getLocation());
		params.put("within", String.valueOf(searchRequest.getLocationRadius()));
		params.put("units", searchRequest.getLocationUnits());
		params.put("sort_order", searchRequest.getSortOrder().toLowerCase());
		params.put("sort_direction", searchRequest.getSortDirection().toString().toLowerCase());
		params.put("page_size",String.valueOf(searchRequest.getPageSize()));
		params.put("page_number",String.valueOf(searchRequest.getPageNumber()));

		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		InputStream is = serverCommunication.invokeMethod(VENUES_SEARCH, params);
		
		return (SearchResult)unmarshallRequest(SearchResult.class, is);

	}

	/**
	 * Method to create or modify a venue
	 * @param v
	 * @return
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	private Venue createOrModify(Venue v) throws EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("name", v.getName());
		params.put("address", v.getAddress());
		params.put("city",  v.getCity());
		params.put("region", v.getRegion());
		params.put("postal_code", v.getPostalCode());
		params.put("description", v.getDescription());
		params.put("country", v.getCountry());
		params.put("privacy", String.valueOf(v.getPrivacy()));
		params.put("venue_type", v.getType());
		params.put("url", v.getUrl());
		params.put("url_type", v.getUrlType());
		
		if ((v.getParents() != null) && (v.getParents().size() > 0)) {
			params.put("parent_id", v.getParents().get(0).getSvid());
		}
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", v.getSvid());
		
		String tagString = "";
		if (v.getTags() != null) {
			for (Tag t : v.getTags()) {
				tagString += t.getTitle() + " ";
			}
			params.put("tags", tagString);
		}
			
		String method = v.getSvid() == null ? VENUES_NEW : VENUES_MODIFY;
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		GenericResponse gr = (GenericResponse)unmarshallRequest(GenericResponse.class, is);
		
		v.setSvid(gr.getId());
		
		return v;
	}
}
