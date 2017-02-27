/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi;


/**
 * Class for other, non-API exceptions
 * @author tylerv
 *
 */
public class EVDBRuntimeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3997854470176178872L;
	
	public EVDBRuntimeException(String msg) {
        super(msg);
    }

	public EVDBRuntimeException(String string, Exception e) {
		super(string, e);
	}

}
