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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Trackback object, record of external references to the object
 * @author tylerv
 *
 */
@XmlRootElement(name="trackback")
@XmlAccessorType(XmlAccessType.FIELD)
public class Trackback {
	private int id;
	
	@XmlElement(name="blog_name")
	private String blogName;
	
	private String excerpt;
	
	@XmlElement(name="trackback_url")
	private String url;
	
	@XmlElement(name="time")
	private Date timeReceived;

	/**
	 * @return the blogName
	 */
	public String getBlogName() {
		return blogName;
	}

	/**
	 * @param blogName the blogName to set
	 */
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	/**
	 * @return the excerpt
	 */
	public String getExcerpt() {
		return excerpt;
	}

	/**
	 * @param excerpt the excerpt to set
	 */
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
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
	 * @return the timeReceived
	 */
	public Date getTimeReceived() {
		return timeReceived;
	}

	/**
	 * @param timeReceived the timeReceived to set
	 */
	public void setTimeReceived(Date timeReceived) {
		this.timeReceived = timeReceived;
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
	
	

}
