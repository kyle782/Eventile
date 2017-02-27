/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Group object
 * @author tylerv
 *
 */
@XmlRootElement(name="group")
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {
	
    /**
     * Group ID, or SGID in eventful parlance
     */
	@XmlAttribute(name="id")
	private String sgid;
	
	/**
	 * Group name
	 */
	@XmlElement(name="group_name")
	private String name;
	
	/**
	 * ID of the user that created the group
	 */
	@XmlElement(name="creator_id")
	private int creatorId;
	
	@XmlElement(name="group_description")
	private String description;

	/**
	 * @return the creatorId
	 */
	public int getCreatorId() {
		return creatorId;
	}

	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	/**
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sgid
	 */
	public String getSgid() {
		return sgid;
	}

	/**
	 * @param sgid the sgid to set
	 */
	public void setSgid(String sgid) {
		this.sgid = sgid;
	}


}
