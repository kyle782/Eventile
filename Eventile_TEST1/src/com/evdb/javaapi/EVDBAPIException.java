/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi;

import com.evdb.javaapi.data.response.GenericErrorResponse;

/**
 * Class for exceptions thrown by the API itself
 * @author tylerv
 *
 */
public class EVDBAPIException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8235407395551567671L;

	public EVDBAPIException(String msg) {
        super(msg);
    }

	public EVDBAPIException(GenericErrorResponse response) {
		super(response.getDescription());
	}

	public EVDBAPIException(String string, Exception e) {
		super(string, e);
	}
}
