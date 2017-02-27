/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.operations;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.evdb.javaapi.APIConfiguration;
import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;
import com.evdb.javaapi.data.response.GenericResponse;

/**
 * Image operations
 * @author tylerv
 *
 */
public class ImageOperations extends BaseOperations {
	
	private static final String IMAGES_REMOVE = "/images/remove";
	private static final String IMAGES_NEW = "/images/new";

	
	/**
	 * Add an image to the EVDB system
	 * @param url
	 * @param caption
	 * @return The image ID used
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public String uploadImageFromURL(String url, String caption) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		params.put("image_url", url);
		params.put("caption", caption);
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());

		InputStream is = serverCommunication.invokeMethod(IMAGES_NEW, params);
		
		GenericResponse gr = (GenericResponse)unmarshallRequest(GenericResponse.class, is);
		
		return gr.getId();
		
	}
	
	/**
	 * Add an image form the local filesystem
	 * @param file File to upload
	 * @param caption
	 * @return id of the newly added image
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException
	 */
	public String uploadImageFromLocalFile(File file, String caption) throws EVDBRuntimeException, EVDBAPIException {
		Map<String, String> params = new HashMap();
		params.put("caption", caption);
		params.put("user", APIConfiguration.getEvdbUser());
		params.put("password", APIConfiguration.getEvdbPassword());
		params.put("file_path", file.toString());

		InputStream is = serverCommunication.invokeMethod(IMAGES_NEW, params);
		
		GenericResponse gr = (GenericResponse)unmarshallRequest(GenericResponse.class, is);
		
		return gr.getId();
		
	}

}
