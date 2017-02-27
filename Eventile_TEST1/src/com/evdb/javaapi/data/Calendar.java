/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data representation of the calendar object
 * Mapped from XML using JAXB
 * @author tylerv
 *
 */
@XmlRootElement(name="calendar")
@XmlAccessorType(XmlAccessType.FIELD)
public class Calendar {

	@XmlAttribute(name="id")
	private String scid;
	
	private String name;
	private String owner;
	private String description;
	private String keywords;
	private int privacy;
	
	@XmlElement(name="notify_schedule")
	private String notifySchedule;
	
	@XmlElement(name="what_query")
	private String whatQuery;
	
	@XmlElement(name="where_query")
	private String whereQuary;
	
	@XmlElementWrapper(name="properties")
	@XmlElement(name="property")
 	private List<Property> properties;


	/**
	 * Calendar description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the calendar description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Return calendar keywords
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * Set calendar keywords
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * Calendar name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set calendar name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Calendar notification schedule
	 * @return the notifySchedule
	 */
	public String getNotifySchedule() {
		return notifySchedule;
	}

	/**
	 * Set the notification schedule
	 * @param notifySchedule the notifySchedule to set
	 */
	public void setNotifySchedule(String notifySchedule) {
		this.notifySchedule = notifySchedule;
	}

	/**
	 * Return the calendar owner
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Set the calendar owner
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Get the privacy of the calendar
	 * 1 is public, 2 is private
	 * @return the privacy
	 */
	public int getPrivacy() {
		return privacy;
	}

	/**
	 * Set the caledars privacy.  1 is public, 2 is private
	 * @param privacy the privacy to set
	 */
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}

	/**
	 * Return the calendar SCID
	 * @return the scid
	 */
	public String getScid() {
		return scid;
	}

	/**
	 * Set the calendar SCID
	 * @param scid the scid to set
	 */
	public void setScid(String scid) {
		this.scid = scid;
	}

	/**
	 * For dynamic calendars, get the what query
	 * @return the whatQuery
	 */
	public String getWhatQuery() {
		return whatQuery;
	}

	/**
	 * Set the calendar query
	 * @param whatQuery the whatQuery to set
	 */
	public void setWhatQuery(String whatQuery) {
		this.whatQuery = whatQuery;
	}

	/**
	 * For dynamic calendars, get the where query
	 * @return the whereQuary
	 */
	public String getWhereQuary() {
		return whereQuary;
	}

	/**
	 * Set the calendar where query
	 * @param whereQuary the whereQuary to set
	 */
	public void setWhereQuary(String whereQuary) {
		this.whereQuary = whereQuary;
	}

	/**
	 * Calendar properties
	 * @return the properties
	 */
	public List<Property> getProperties() {
		return properties;
	}

	/**
	 * Set the calendar properties
	 * @param properties the properties to set
	 */
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	

}
