/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.operations;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;
import com.evdb.javaapi.data.Location;

/**
 * Class encapsulating location operations
 * <p>
 * Sample code for dealing with events:
 * <pre>
 * 	//Create our operation objects
	LocationOperations lo = new LocationOperations();
	Location l = lo.search("Los Angeles");
		
	assertEquals(l.getRegion(), "California");
	assertEquals(l.getMetro(), "Los Angeles");
	</pre>
 * @author tylerv
 *
 */
public class LocationOperations extends BaseOperations {
	
	private static final String LOCALES_SEARCH = "/locales/search";
	
	
	/**
	 * Search for a location and resolve it to a metro/city/region
	 * @param location The location to search for
	 * @return The resolved location, or null if no location could be resolved
	 * @throws EVDBRuntimeException
	 * @throws EVDBAPIException 
	 */
	public Location search(String location) throws  EVDBRuntimeException, EVDBAPIException   {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("l", location);
		
		InputStream is = serverCommunication.invokeMethod(LOCALES_SEARCH, params);
		
		Location l = (Location)unmarshallRequest(Location.class, is);
		
		if ((l.getPostalCode() == null) &&
				(l.getCity() == null) && 
				(l.getMetro() == null) &&
				(l.getRegion() == null) &&
				(l.getCountry() == null)) {
			//our search failed, return null
			return null; 
		}
		
		return l;
	}
	


}
