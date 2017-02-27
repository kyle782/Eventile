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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.evdb.javaapi.APIConfiguration;
import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;
import com.evdb.javaapi.data.Comment;
import com.evdb.javaapi.data.Image;
import com.evdb.javaapi.data.Link;
import com.evdb.javaapi.data.Property;
import com.evdb.javaapi.data.Tag;
import com.evdb.javaapi.data.response.GenericErrorResponse;
import com.evdb.javaapi.data.response.GenericResponse;
import com.evdb.javaapi.network.ServerCommunication;

/**
 * The base operations class, contains methods common to all objects
 * @author tylerv
 *
 */
public abstract class BaseOperations {
	

	/**
	 * The main server communication object
	 */
	protected ServerCommunication serverCommunication;
	
	public BaseOperations() {
		serverCommunication = new ServerCommunication();
	}
	
	/**
	 * Return an unmarshaller
	 * @param clazz
	 * @return Unmarshaller
	 * @throws JAXBException
	 */
	protected Unmarshaller getUnmarshaller(Class clazz) throws JAXBException {
		
        JAXBContext jc = JAXBContext.newInstance(clazz);
        return jc.createUnmarshaller();

	}
	
	/**
	 * List the tags for an object
	 * @param clazz
	 * @param method
	 * @param id
	 * @return Object
	 * @throws EVDBAPIException 
	 * @throws EVDBRuntimeException 
	 */
	protected Object listTags(Class clazz, String method, String id) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		params.put("id", id);
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		return unmarshallRequest(clazz, is);
	}
	
	/**
	 * List properties
	 * @param clazz
	 * @param method
	 * @param id
	 * @return Object
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected Object listProperties(Class clazz, String method, String id) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		params.put("id", id);
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		return unmarshallRequest(clazz, is);
	}
	
	/**
	 * Add or remove tags from an object
	 * @param method
	 * @param id
	 * @param tagList
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected void modifyTags(String method, String id, List<Tag> tagList) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", id);
		
		String tagString = "";
		for (Tag t : tagList) {
			tagString += t.getTitle() + " ";
		}
		params.put("tags", tagString);
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		unmarshallRequest(GenericResponse.class, is);
		
	}
	
	/**
	 * Add a property
	 * @param method
	 * @param id
	 * @param property
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected int addProperty(String method, String id, Property property) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", id);
		params.put("name", property.getName());
		params.put("value", property.getValue());
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		GenericResponse go = (GenericResponse)unmarshallRequest(GenericResponse.class, is);
		property.setId(go.getPropertyId());
		return go.getPropertyId();
		
	}
	
	/**
	 * Remove a property
	 * @param method
	 * @param id
	 * @param property
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected void deleteProperty(String method, String id, Property property) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", id);
		params.put("name", property.getName());
		params.put("property_id", String.valueOf(property.getId()));
		
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		unmarshallRequest(GenericResponse.class, is);
		
	}
	
	/**
	 * Add a comment to an object
	 * @param method
	 * @param id
	 * @param comment
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected void addComment(String method, String id, Comment comment) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", id);
		params.put("comment", comment.getText());
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		unmarshallRequest(GenericResponse.class, is);
		
	}
	
	/**
	 * Modify a comment
	 * @param method
	 * @param comment
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected void modifyComment(String method, Comment comment) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("comment_id", String.valueOf(comment.getId()));
		params.put("comment", comment.getText());
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		unmarshallRequest(GenericResponse.class, is);
		
	}
	
	/**
	 * Delete a comment
	 * @param method
	 * @param comment
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected void deleteComment(String method, Comment comment) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("comment_id", String.valueOf(comment.getId()));
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		unmarshallRequest(GenericResponse.class, is);
		
	}
	
	/**
	 * Delete an image
	 * @param method
	 * @param id
	 * @param image
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected void deleteImage(String method, String id, Image image) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", id);
		params.put("image_id", String.valueOf(image.getId()));
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		unmarshallRequest(GenericResponse.class, is);
		
	}
	
	/**
	 * Add am image
	 * @param method
	 * @param id
	 * @param image
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected void addImage(String method, String id, Image image) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", id);
		params.put("image_id", String.valueOf(image.getId()));
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		unmarshallRequest(GenericResponse.class, is);
		
	}
	
	/**
	 * Add a link
	 * @param method
	 * @param id
	 * @param link
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected void addLink(String method, String id, Link link) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("id", id);
		params.put("link", link.getUrl());
		params.put("description", link.getDescription());
		params.put("link_type_id", String.valueOf(link.getType()));
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		unmarshallRequest(GenericResponse.class, is);
		
	}
	
	/**
	 * Remove a link
	 * @param method
	 * @param link
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	protected void deleteLink(String method, Link link) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		
		//modify needs this to be set
		params.put("link_id", String.valueOf(link.getId()));
		
		InputStream is = serverCommunication.invokeMethod(method, params);
		
		unmarshallRequest(GenericResponse.class, is);
		
	}
	
	/**
	 * Unmarshall server response data
	 * @param clazz Class to unmarshall to
	 * @param is  Input stream
	 * @return Unmarshalled object
	 * @throws JAXBException
	 * @throws EVDBRuntimeException
	 */
	protected Object unmarshallRequest(Class clazz, InputStream is) throws EVDBAPIException {
		
        JAXBContext jc;
        Unmarshaller u;
        Object o;
		try {
			jc = JAXBContext.newInstance(clazz);
	        u = jc.createUnmarshaller();
	        o =  u.unmarshal(is);        
		} catch (JAXBException e) {
			throw new EVDBAPIException("XML Parse Exception", e);
		}
		
        //Check to see if we received an error.  If so, return the error to the user
        if (o instanceof GenericErrorResponse) {
			throw new EVDBAPIException((GenericErrorResponse)o);
        }
        
        //if we are a generic response, ensure our status is OK
        if (clazz.getCanonicalName().equals(GenericResponse.class.getCanonicalName())) {
        	GenericResponse gr = (GenericResponse)o;
        	if (!gr.getStatus().equals("ok")) {
        		throw new EVDBAPIException("Command failed: " + gr.getMessage());
        	}
        }
        
        return o;
		
	}

}
