/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * Base API response class, mapped from XML via JAXB
 * @author tylerv
 *
 */
@XmlSeeAlso({GenericErrorResponse.class})
@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenericResponse {
	
	@XmlAttribute
	private String status;
	
	private String message;
	private String id;
	
	@XmlElement(name="property_id")
	private int propertyId;

	/**
	 * @return the property_id
	 */
	public int getPropertyId() {
		return propertyId;
	}

	/**
	 * @param propertyId the property_id to set
	 */
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * Response status.  Will usually be "ok" for successful operations
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Response ID
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retruns the response message
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
