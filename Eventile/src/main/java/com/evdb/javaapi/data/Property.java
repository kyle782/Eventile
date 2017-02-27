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
 * Several EVDB objects contain properties
 * @author tylerv
 *
 */
@XmlRootElement(name="property")
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {

    /**
     * Property ID
     */
	private int id;
	
	/**
	 * Name of the property
	 */
	private String name;
	
	/**
	 * Property value
	 */
	private String value;
	
	public Property() {
	}
	
	public Property(String name, String value) {
		this.name = name;
		this.value = value;
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
