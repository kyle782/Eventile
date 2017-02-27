/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.network;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.evdb.javaapi.APIConfiguration;
import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;

/**
 * Class responsible for actual server communication
 * @author tylerv
 *
 */
public class ServerCommunication {

	/**
	 * Constructor, setup networking parameters
	 *
	 */
	public ServerCommunication() {
        //use any configured proxies 
        System.setProperty("java.net.useSystemProxies","true");
	}
	
        private Integer read_timeout = 0;
        private Integer connect_timeout = 0;
 
	/**
	 * Main method used to call URLs at EVDB
	 * @param methodPath  Method to call
	 * @param parameters  List of parameters
	 * @return  The XML from the call
	 * @throws EVDBRuntimeException 
	 * @throws EVDBAPIException 
	 * @throws Exception 
	 */
	public InputStream invokeMethod(String methodPath, Map<String, String> parameters ) throws EVDBRuntimeException, EVDBAPIException {
		
		//actually make the request to EVDB
		String key = APIConfiguration.getApiKey();
		boolean isGet = false;
		boolean isForm = false;
		if (parameters.get("read_timeout") != null) {
	        	read_timeout= Integer.parseInt(parameters.get("read_timeout"));
                	parameters.remove("read_timeout");
		}

		if (parameters.get("connect_timeout") != null) {
	        	connect_timeout= Integer.parseInt(parameters.get("connect_timeout"));
                	parameters.remove("connect_timeout");
		}
		
		if ((key == null) || (key.length() == 0)) {
			throw new EVDBRuntimeException("No API Key specified");
		}
		
		//add our API key to the parameters list
		if (parameters == null) {
			parameters = new HashMap();
		}
		parameters.put("app_key", key);

		
		String urlParameters = constructURLString(parameters);
        
		String baseUrlString = APIConfiguration.getBaseURL() + methodPath;
		
		String urlToRequest = baseUrlString;
		
		if (urlParameters.length() < 900) {
			urlToRequest += "?" + urlParameters;
			isGet = true;
		}
		
		//if we have an image file to upload, set the proper content type
		if (parameters.containsKey("file_path")) {
			isGet = false;
			isForm = true;
		}
		
        InputStream in = null;
		try {
	        URL url = new URL(urlToRequest);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        OutputStream out;
	
	        byte[] buff;
	        
	        if (isGet) {
	                con.setRequestMethod("GET");
	                con.setDoOutput(false);
	                con.setDoInput(true);
                        if (connect_timeout > 0) {
                        	con.setConnectTimeout(connect_timeout);
				//System.out.println("you set conn timeout to " +  connect_timeout);
                        }
                        if (read_timeout > 0) {
				con.setReadTimeout(read_timeout);
				//System.out.println("you set read timeout to " + read_timeout);
                        }
	                con.connect();
	                in = con.getInputStream();
	        } else if (isForm) {
	        	con.setRequestMethod("POST");
	                con.setDoOutput(true);
	                con.setDoInput(true);
	                con.setUseCaches(false);
	                con.setDefaultUseCaches(false);
	                
	                String boundary = MultipartFormOutputStream.createBoundary();
	                // The following request properties were recommended by the MPF class
	                // TODO: Revisit this set, I think some aren't necessary
	                con.setRequestProperty("Accept", "*/*");
	                con.setRequestProperty("Content-Type", MultipartFormOutputStream.getContentType(boundary));
	                con.setRequestProperty("Connection", "Keep-Alive");
	                con.setRequestProperty("Cache-Control", "no-cache");
	                con.connect();
	                MultipartFormOutputStream mpout = new MultipartFormOutputStream(con.getOutputStream(), boundary);
	                // First, write out the parameters
	                for (Map.Entry<String,String>entry : parameters.entrySet()) {
	                    mpout.writeField(entry.getKey(),entry.getValue());
	                }
	                // Now write the image
	                String mimeType = "image/jpg";
	                String fullFilePath = parameters.get("file_path");
	                if (fullFilePath.endsWith("gif")) {
	                	mimeType = "image/gif";
	                } else if (fullFilePath.endsWith("png")) {
	                	mimeType = "image/png";
	                } else if (fullFilePath.endsWith("tiff")) {
	                	mimeType = "image/tiff";
	                }
	                mpout.writeFile("image_file",mimeType, new File(parameters.get("file_path")));
	                mpout.flush();
	                mpout.close();
	                // All done, get the InputStream
	                in = con.getInputStream();
	        } else {
	                con.setRequestMethod("POST");
	                con.setDoOutput(true);
	                con.setDoInput(true);
	                con.connect();
	                out = con.getOutputStream();
	                buff = urlParameters.getBytes("UTF8");
	                out.write(buff);
	                out.flush();
	                out.close();
	                in = con.getInputStream();
	        }
		} catch (Exception e) {
			throw new EVDBRuntimeException("URL or network communication error: " + e.getMessage(), e);
		}
        return in;
        
	}
	
	/**
	 * Expand the map of parameters to construct a URL string
	 * @param parameters
	 * @return
	 */
	private String constructURLString(Map<String, String> parameters) {
		String url = "";

        boolean first = true;
        
        for (Map.Entry<String,String>entry : parameters.entrySet()) {
            try {
            	//type checking, we can get nulls in here
            	if ((entry.getValue() == null) || (entry.getKey() == null)) {  
            		continue;
            	}
            	if (entry.getValue().length() == 0) {
            		continue;
            	}
            	
                if (first) {
                    first = false;
                } else {
                	url += "&";
                }
                url += URLEncoder.encode(entry.getKey(),"UTF-8")+"="+
                        URLEncoder.encode(entry.getValue(),"UTF-8");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
                // No Fixing this, really
                throw new Error("Unsupported Encoding Exception",ex);
            }
        }

        return url;
	}
}
