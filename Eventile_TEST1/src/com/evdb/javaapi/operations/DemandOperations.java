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
import com.evdb.javaapi.data.Demand;
import com.evdb.javaapi.data.Image;
import com.evdb.javaapi.data.Link;
import com.evdb.javaapi.data.SearchResult;
import com.evdb.javaapi.data.Tag;
import com.evdb.javaapi.data.request.DemandSearchRequest;
import com.evdb.javaapi.data.response.GenericResponse;

/**
 * Demand Operations
 * <p>
 * Sample code for dealing with demands:
 * <pre>
 * 	//Create our operation objects
	DemandOperations dop = new DemandOperations();
	PerformerOperations po = new PerformerOperations();
	
	Demand d = new Demand();
	
	//get the performer to associate the demand with
	Performer p = po.get("P0-123-12345-00");
	
	d.setPerformer(p);
	
	//set location
	d.setLocation("Guelph, Ontario, Canada");
	d.setDescription("Go guelph!");
	
	//create
	d = dop.create(d);
	
	//once created, the demand should have an SDID
	assert(d.getSdid().length() > 0);
	</pre>
 * @author tylerv
 *
 */
public class DemandOperations extends BaseOperations {

	private static final String DEMANDS_GET = "/demands/get";
	private static final String DEMANDS_NEW = "/demands/new";
	private static final String DEMANDS_MODIFY = "/demands/modify";
	private static final String DEMANDS_WITHDRAW = "/demands/withdraw";
	private static final String DEMANDS_RESTORE = "/demands/restore";
	private static final String DEMANDS_SEARCH = "/demands/search";
	private static final String DEMANDS_TAGS_LIST = "/demands/tags/list";
	private static final String DEMANDS_TAGS_NEW = "/demands/tags/add";
	private static final String DEMANDS_TAGS_DELETE = "/demands/tags/remove";
	private static final String DEMANDS_COMMENTS_NEW = "/demands/comments/new";
	private static final String DEMANDS_COMMENTS_MODIFY = "/demands/comments/modify";
	private static final String DEMANDS_COMMENTS_DELETE = "/demands/comments/delete";
	private static final String DEMANDS_LINKS_NEW = "/demands/links/new";
	private static final String DEMANDS_LINKS_DELETE = "/demands/links/remove";
	private static final String DEMANDS_IMAGES_ADD = "/demands/links/add";
	private static final String DEMANDS_IMAGES_REMOVE = "/demands/links/remove";

	
	/**
	 * Return a demand from an SDID
	 * <pre>
		//test retrieving a demand 
		DemandOperations dop = new DemandOperations();
		Demand d = dop.get("D0-123-2414-00");
		
		assertEquals(d.getSdid(), "D0-123-2414-00");
		assertEquals(d.getPerformer().getSpid(), "P0-123-2414-00");
		assertEquals(d.getPerformer().getName(), "Pretty Ricky");
		assert(d.getTags().size() > 0);
		</pre>
	 * @param sdid SDID of the demand to lookup 
	 * @return the demand object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public Demand get(String sdid) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Map<String, String> params = new HashMap();
		params.put("id", sdid);
		
		InputStream is = serverCommunication.invokeMethod(DEMANDS_GET, params);
		
		Demand v = (Demand)unmarshallRequest(Demand.class, is);
		
		return v;
	}
	
	/**
	 * Create a new demand
	 * @param d Demand to create
	 * @return The newly created Demand
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public Demand create(Demand d) throws  EVDBRuntimeException, EVDBAPIException   {
		if (d.getSdid() != null) {
			throw new EVDBRuntimeException("Create called on an demand with an existing SDID");
		}
		
		return createOrModify(d);
	}
	
	/**
	 * Modify a demand
	 * @param d Demand to modify
	 * @return The modified Demand
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public Demand modify(Demand d) throws  EVDBRuntimeException, EVDBAPIException   {
		if (d.getSdid() == null) {
			throw new EVDBRuntimeException("Modify called on an demand without an SDID");
		}
		
		return createOrModify(d);
	}
	
	/**
	 * Withdraws an demand
	 * @param sdid SDID of the demand to withdraw
	 * @param withdrawNote Note explaining the withdraw
	 * @throws Exception
	 */
	public void withdraw(String sdid, String withdrawNote) throws  EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("id", sdid);
		params.put("note", withdrawNote);

		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		InputStream is = serverCommunication.invokeMethod(DEMANDS_WITHDRAW, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Restores (i.e. un-withdraws) a demand
	 * @param sdid SVID of the demand to restore
	 * @throws Exception
	 */
	public void restore(String sdid) throws  EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("id", sdid);

		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		InputStream is = serverCommunication.invokeMethod(DEMANDS_RESTORE, params);
		
		unmarshallRequest(GenericResponse.class, is);
	}
	
	/**
	 * Add tags to a demand
	 * @param sdid SDID
	 * @param tagList The list of tags to add
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addTags(String sdid, List<Tag> tagList) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyTags(DEMANDS_TAGS_NEW, sdid, tagList);
	}
	
	/**
	 * Add tags to an demand
	 * @param sdid Demand SDID
	 * @param tagList The list of tags to delete
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteTags(String sdid, List<Tag> tagList) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyTags(DEMANDS_TAGS_DELETE, sdid, tagList);
	}
	
	/**
	 * List the tags for a given SDID
	 * @param sdid SDID to return the list of tags for
	 * @return List of tags
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public List<Tag> getTags(String sdid) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Demand e = (Demand)listTags(Demand.class, DEMANDS_TAGS_LIST, sdid);
		
		return e.getTags();
	}
	
	/**
	 * Add a comment to a demand
	 * @param sdid SDID to add the comment to
	 * @param comment Comment object to add
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addComment(String sdid, Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addComment(DEMANDS_COMMENTS_NEW, sdid, comment);
	}
	
	/**
	 * Modify a comment
	 * @param comment Comment object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void modifyComment(Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.modifyComment(DEMANDS_COMMENTS_MODIFY, comment);
	}
	
	/**
	 * Delete a comment
	 * @param comment Comment object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteComment(Comment comment) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteComment(DEMANDS_COMMENTS_DELETE, comment);
	}
	
	/**
	 * Add a link to the demand
	 * @param sdid SDID to add the link to
	 * @param link Link object to add
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addLink(String sdid, Link link) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addLink(DEMANDS_LINKS_NEW, sdid, link);
	}
	
	/**
	 * Remove a link from the demand
	 * @param link Link to remove
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteLink(Link link) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteLink(DEMANDS_LINKS_DELETE, link);

	}
	
	/**
	 * Add an image to the given demand
	 * @param sdid SDID to add the image to
	 * @param image New image object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void addImage(String sdid, Image image) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.addImage(DEMANDS_IMAGES_ADD, sdid, image);
	}

	/**
	 * Remove the image
	 * @param sdid SDID to remove the image
	 * @param image Image object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public void deleteImage(String sdid, Image image) throws  EVDBRuntimeException, EVDBAPIException   {
		
		super.deleteImage(DEMANDS_IMAGES_REMOVE, sdid, image);
	}
	
	/**
	 * Demand search request
	 * <pre>
	 * 
	 	//Example of running a demand search
	 	DemandOperations dop = new DemandOperations();
		DemandSearchRequest psr = new DemandSearchRequest();
		psr.setKeywords("pretty ricky");
		
		SearchResult sr = dop.search(psr);
		
		assert(sr.getTotalItems() > 1);
		List<Demand> demands = sr.getDemands();
		
		assertNotNull(demands.get(0).getSdid());
		</pre>
	 * @param searchRequest
	 * @return Search result object
	 * @throws Exception
	 */
	public SearchResult search(DemandSearchRequest searchRequest) throws  EVDBRuntimeException, EVDBAPIException  {
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

		InputStream is = serverCommunication.invokeMethod(DEMANDS_SEARCH, params);
		
		return (SearchResult)unmarshallRequest(SearchResult.class, is);

	}

	/**
	 * Method to create or modify a demand
	 * @param v
	 * @return
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	private Demand createOrModify(Demand d) throws EVDBRuntimeException, EVDBAPIException  {
		
		Map<String, String> params = new HashMap();
		params.put("performer_id", d.getPerformer().getSpid());
		params.put("location", d.getLocation());
		params.put("description",  d.getDescription());
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", d.getSdid());
		
		String tagString = "";
		if (d.getTags() != null) {
			for (Tag t : d.getTags()) {
				tagString += t.getTitle() + " ";
			}
			params.put("tags", tagString);
		}
			
		String method = d.getSdid() == null ? DEMANDS_NEW : DEMANDS_MODIFY;
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		GenericResponse gr = (GenericResponse)unmarshallRequest(GenericResponse.class, is);
		
		d.setSdid(gr.getId());
		
		return d;
	}
}
