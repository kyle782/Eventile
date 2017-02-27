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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * If an error response is returned, this class is used.  Mapped from JAXB
 * @author tylerv
 *
 */
@XmlRootElement(name="error")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenericErrorResponse {
	
	@XmlAttribute(name="string")
	private String status;
	
	private String description;

	/**
	 * Description of the status
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Status of the request, will usually be "ok" or "error"
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

}
