/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi;

/**
 * API Configuration constants
 * @author tylerv
 *
 */
public class APIConfiguration {
	
	private static String apiKey;
    
    private static String baseURL = "http://api.eventful.com/rest/";
    
    private static String evdbUser;
    private static String evdbPassword;

    /**
     * Set the app key
     * @param newApiKey
     */
    public static void setApiKey(String newApiKey) {
        apiKey = newApiKey;
    }
    
    /**
     * Get the API key, after having stored it via setApiKey.
     * @return apiKey  The EVDB API key.
     * @throws EVDBRuntimeException 
     */
    public static String getApiKey() throws EVDBRuntimeException {
        if (apiKey == null) throw new EVDBRuntimeException("API Key not set");
        return apiKey;
    }
    
    /**
     * Base URL
     * @return the base URL
     */
    public static String getBaseURL() {
        return baseURL;
    }
    
    /**
     * Base URL
     * @return the base URL
     */
    public static String setBaseURL(String baseURL) {
        return APIConfiguration.baseURL = baseURL;
    }

	/**
	 * @return the evdbPassword
	 * @throws EVDBRuntimeException 
	 */
	public static String getEvdbPassword() throws EVDBRuntimeException {
        if (evdbPassword == null) throw new EVDBRuntimeException("Eventful Password Not Set, see APIConfiguration.setEvdbPassword method");
		return evdbPassword;
	}

	/**
	 * Set the EVDB login password
	 * @param evdbPassword the evdbPassword to set
	 */
	public static void setEvdbPassword(String evdbPassword) {
		APIConfiguration.evdbPassword = evdbPassword;
	}

	/**
	 * Return te EVDB Username
	 * @return the evdbUser
	 * @throws EVDBRuntimeException 
	 */
	public static String getEvdbUser() throws EVDBRuntimeException {
        if (evdbUser == null) throw new EVDBRuntimeException("Eventful Username Not Set, see APIConfiguration.setEvdbUser method");
		return evdbUser;
	}

	/**
	 * Set the EVDB API username
	 * @param evdbUser the evdbUser to set
	 */
	public static void setEvdbUser(String evdbUser) {
		APIConfiguration.evdbUser = evdbUser;
	}
}
