/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.evdb.javaapi.util.DateAdapter;

/**
 * Implementation of the Link object
 * @author tylerv
 *
 */
@XmlRootElement(name="link")
@XmlAccessorType(XmlAccessType.FIELD)
public class Link {
	
    /**
     * Link ID
     */
	@XmlAttribute
	private int id;
	
	/** 
	 * Link URL
	 */
	private String url;
	
	/**
	 * Type of link
	 */
	@XmlTransient
	private LinkType type;
	
	@XmlElement(name="type")
	private String typeString;
	
	/**
	 * Link description
	 */
	private String description;
	
	/**
	 * User who created the link
	 */
	private String username;
	
	/**
	 * Time the link was created
	 */
	@XmlElement(name="time")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date createdTime;
	
	public enum LinkType {
		INFO (1),
		BOX_OFFICE (2),
		NEWS (3),
		REVIEW (4),
		SPONSOR (5),
		TICKETS (6),
		CHAT (8),
		BLOG (15),
		OFFICIAL_SITE (17),
		PODCAST (18),
		WEBCAST (14),
		WEBSITE (19),
		OTHER (16);
		
		
		private int typeId;
		private LinkType(int typeId) {
			this.typeId = typeId;
		}
		
		public int asInteger() {
			return typeId;
		}
		
	}

	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		if (typeString != null) {
			this.type = LinkType.valueOf(typeString.toUpperCase());
		}
		return type.asInteger();
	}

	/**
	 * @param type the type to set
	 */
	public void setType(LinkType type) {
		this.type = type;
		typeString = type.toString();
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
