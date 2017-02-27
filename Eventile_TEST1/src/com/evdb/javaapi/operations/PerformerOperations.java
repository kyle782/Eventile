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
import com.evdb.javaapi.data.Performer;
import com.evdb.javaapi.data.Property;
import com.evdb.javaapi.data.SearchResult;
import com.evdb.javaapi.data.Tag;
import com.evdb.javaapi.data.request.PerformerSearchRequest;
import com.evdb.javaapi.data.response.GenericResponse;

/**
 * Performer operations
 * <p>
 * Sample code for dealing with performers:
 * <pre>
 * 	//Create our operation objects
	PerformerOperations po = new PerformerOperations();
	Performer p = po.get("P0-123-1234-00");
	
	p.setShortBio("Test API Bio");
	
	po.modify(p);
	
	p = po.get("P0-123-1234-00");
	
	
	assertEquals(p.getSpid(), "P0-123-1234-00");
	assertEquals(p.getName(), "API Test Performer");
	assertEquals(p.getShortBio(), "Test API Bio");
	</pre>
 * @author tylerv
 *
 */
public class PerformerOperations extends BaseOperations {

	private static final String PERFORMERS_GET = "/performers/get";
	private static final String PERFORMERS_NEW = "/performers/new";
	private static final String PERFORMERS_MODIFY = "/performers/modify";
	private static final String PERFORMERS_WITHDRAW = "/performers/withdraw";
	private static final String PERFORMERS_RESTORE = "/performers/restore";
	private static final String PERFORMERS_SEARCH = "/performers/search";
	private static final String PERFORMERS_TAGS_LIST = "/performers/tags/list";
	private static final String PERFORMERS_TAGS_NEW = "/performers/tags/add";
	private static final String PERFORMERS_TAGS_DELETE = "/performers/tags/remove";
	private static final String PERFORMERS_COMMENTS_NEW = "/performers/comments/new";
	private static final String PERFORMERS_COMMENTS_MODIFY = "/performers/comments/modify";
	private static final String PERFORMERS_COMMENTS_DELETE = "/performers/comments/delete";
	private static final String PERFORMERS_LINKS_NEW = "/performers/links/add";
	private static final String PERFORMERS_LINKS_DELETE = "/performers/links/remove";
	private static final String PERFORMERS_IMAGES_ADD = "/performers/links/add";
	private static final String PERFORMERS_IMAGES_REMOVE = "/performers/links/remove";
	private static final String PERFORMERS_PROPERTIES_ADD = "/performers/properties/add";
	private static final String PERFORMERS_PROPERTIES_REMOVE = "/performers/properties/remove";
	private static final String PERFORMERS_PROPERTIES_LIST = "/performers/properties/list";

	
	/**
	 * Return a performer from an SPID
	 * @param spid SPID of the performer to lookup 
	 * @return the performer object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public Performer get(String spid) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Map<String, String> params = new HashMap();
		params.put("id", spid);
		
		InputStream is = serverCommunication.invokeMethod(PERFORMERS_GET, params);
		
		Performer v = (Performer)unmarshallRequest(Performer.class, is);
		
		return v;
	}
	
	/**
	 * Create a new performer
	 * <pre>
	 	//create our operation objects
		PerformerOperations po = new PerformerOperations();
		Performer p = new Performer();
		
		p.setName("API Test");
		p.setShortBio("Just a API test");
		
		//now call create
		p = po.create(p);
		
		assert(p.getSpid().length() > 0);
		
		//now get the performer
		Performer p2 = po.get(p.getSpid());
		assertEquals(p2.getName(), "API Test");
		assertEquals(p2.getShortBio(), "Just a API test");
	 </pre>
	 * @param p Performer to create
	 * @return newly created Performer
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public Performer create(Performer p) throws  EVDBRuntimeException, EVDBAPIException   {
		if (p.getSpid() != null) {
			throw new EVDBRuntimeException("Create called on an performer with an existing SPID");
		}
		
		return createOrModify(p);
	}
	
	/**
	 * Modify a performer
	 * @param p Performer to modify
	 * @return modified Performer
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public Performer modify(Performer p) throws  EVDBRuntimeException, EVDBAPIException   {
		if (p.getSpid() == null) {
			throw new EVDBRuntimeException("Modify called on an performer without an SPID");
		}
		
		return createOrModify(p);
	}
	
	/**
	 * Withdraws an performer
	 * @param spid SPID of the performer to withdraw
	 * @param withdrawNote Note explaining the withdraw
	 * @throws Exception
	 */
	public void withdraw(String spid, String withdrawNote) throws  EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("id", spid);
		params.put("note", withdrawNote);

		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		InputStream is = serverCommunication.invokeMethod(PERFORMERS_WITHDRAW, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Restores (i.e. un-withdraws) a performer
	 * @param spid SVID of the performer to restore
	 * @throws Exception
	 */
	public void restore(String spid) throws  EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("id", spid);

		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		InputStream is = serverCommunication.invokeMethod(PERFORMERS_RESTORE, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Add tags to a performer
	 * @param spid SPID
	 * @param tagList The list of tags to add
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addTags(String spid, List<Tag> tagList) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyTags(PERFORMERS_TAGS_NEW, spid, tagList);
	}
	
	/**
	 * Add tags to an performer
	 * @param spid Performer SPID
	 * @param tagList The list of tags to add
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteTags(String spid, List<Tag> tagList) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyTags(PERFORMERS_TAGS_DELETE, spid, tagList);
	}
	
	/**
	 * List the tags for a given SPID
	 * @param spid
	 * @return List of Tags
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public List<Tag> getTags(String spid) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Performer e = (Performer)listTags(Performer.class, PERFORMERS_TAGS_LIST, spid);
		
		return e.getTags();
	}
	
	/**
	 * Add a comment to a performer
	 * @param spid SPID of the performer to add the comment to
	 * @param comment Comment object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addComment(String spid, Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addComment(PERFORMERS_COMMENTS_NEW, spid, comment);
	}
	
	/**
	 * Modify a comment
	 * @param comment Comment to modify
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void modifyComment(Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyComment(PERFORMERS_COMMENTS_MODIFY, comment);
	}
	
	/**
	 * Delete a comment
	 * @param comment Comment to delete
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteComment(Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteComment(PERFORMERS_COMMENTS_DELETE, comment);
	}
	
	/**
	 * Add a link to the performer
	 * @param spid SPID to add the link to
	 * @param link Link object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addLink(String spid, Link link) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addLink(PERFORMERS_LINKS_NEW, spid, link);
	}
	
	/**
	 * Remove a link from the performer
	 * @param spid SPID of the performer
	 * @param link
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteLink(String spid, Link link) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("link_id", String.valueOf(link.getId()));
		params.put("id", spid);
		
		InputStream is = serverCommunication.invokeMethod(PERFORMERS_LINKS_DELETE, params);
		
		unmarshallRequest(GenericResponse.class, is);

	}
	
	/**
	 * Add an image to the given performer
	 * @param spid
	 * @param image
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addImage(String spid, Image image) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addImage(PERFORMERS_IMAGES_ADD, spid, image);
	}

	/**
	 * Remove the image
	 * @param spid
	 * @param image
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteImage(String spid, Image image) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteImage(PERFORMERS_IMAGES_REMOVE, spid, image);
	}
	
	/**
	 * Delete a property
	 * @param spid
	 * @param property
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteProperty(String spid, Property property) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteProperty(PERFORMERS_PROPERTIES_REMOVE, spid, property);
	}
	
	/**
	 * Add a property
	 * @param spid
	 * @param property
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public int addProperty(String spid, Property property) throws  EVDBRuntimeException, EVDBAPIException   {
		
		return super.addProperty(PERFORMERS_PROPERTIES_ADD, spid, property);
	}
	
	/**
	 * Return a list of properties
	 * @param spid
	 * @return List of Properties
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public List<Property> getProperties(String spid) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Performer p = (Performer)listProperties(Performer.class, PERFORMERS_PROPERTIES_LIST, spid);
		
		return p.getProperties();
	}

	/**
	 * Performer search request
	 * @param searchRequest PerformerSearchRequest object
	 * @return Search result object
	 * @throws Exception
	 */
	public SearchResult search(PerformerSearchRequest searchRequest) throws  EVDBRuntimeException, EVDBAPIException  {
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

		InputStream is = serverCommunication.invokeMethod(PERFORMERS_SEARCH, params);
		
		return (SearchResult)unmarshallRequest(SearchResult.class, is);

	}

	/**
	 * Method to create or modify a performer
	 * @param p Performer to create or modify
	 * @return
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	private Performer createOrModify(Performer p) throws EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("name", p.getName());
		params.put("short_bio", p.getShortBio());
		params.put("long_bio",  p.getLongBio());
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", p.getSpid());
		
		String tagString = "";
		if (p.getTags() != null) {
			for (Tag t : p.getTags()) {
				tagString += t.getTitle() + " ";
			}
			params.put("tags", tagString);
		}
			
		String method = p.getSpid() == null ? PERFORMERS_NEW : PERFORMERS_MODIFY;
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		GenericResponse gr = (GenericResponse)unmarshallRequest(GenericResponse.class, is);
		
		p.setSpid(gr.getId());
		
		return p;
	}

}
