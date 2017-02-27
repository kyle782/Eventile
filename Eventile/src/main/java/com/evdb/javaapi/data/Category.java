/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The category object
 * @author tylerv
 *
 */
@XmlRootElement(name="category")
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {

    /**
     * Category ID
     */
	private String id;
	
	/**
	 * Category Name
	 */
	private String name;
	
	/**
	 * Return the category ID
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Set the category ID
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Get the category name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the category name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
