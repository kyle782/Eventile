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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Tag object implementation
 * @author tylerv
 *
 */
@XmlRootElement(name="tag")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tag {

    /**
     * Tag ID
     */
	@XmlAttribute
	private String id;
	
	/**
	 * Tag title
	 */
	private String title;
	
	/**
	 * Tag owner
	 */
	private String owner;
	
	/**
	 * Creates a tag
	 */
	public Tag() {
	}
	
	/**
	 * Creates a tag
	 * @param title Title element
	 */
	public Tag(String title) {
		this.title = title;
	}
	
	/**
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
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
