/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.evdb.javaapi.data.response.GenericErrorResponse;
import com.evdb.javaapi.util.DateAdapter;

/**
 * Demand Object.  
 * <p>
 * Use the DemandOperations class to manipulate
 * @see com.evdb.javaapi.operations.DemandOperations
 * @author tylerv
 *
 */
@XmlRootElement(name="demand")
@XmlSeeAlso({GenericErrorResponse.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Demand {
	
    /**
     * Demand ID, or SDID in Eventful parlance
     */
	@XmlElement(name="id")
	private String sdid;

	/**
	 * Demand description
	 */
	private String description;
	
	/**
	 * Status of the demand
	 */
	private String status;
	
	/**
	 * Location of the demand
	 */
	private String location;
	
	/**
	 * Member count
	 */
	@XmlElement(name="member_count")
	private int memberCount;
	
	/**
	 * Creation date
	 */
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date created;

	/**
	 * Username of the demand creator
	 */
	private String creator;
	
	/**
	 * Whether the demand has been withdrawn
	 */
	private boolean withdrawn;
	
	@XmlElement(name="withdrawn_note")
	private String withdrawnNote;
	
	/**
	 * Performer being demanded
	 */
	private Performer performer;
	
	/**
	 * Demand images
	 */
	@XmlElementWrapper(name="images")
	@XmlElement(name="image")
 	private List<Image> images;
	
	/**
	 * Demand tags
	 */
	@XmlElementWrapper(name="tags")
	@XmlElement(name="tag")
 	private List<Tag> tags;
	
	/**
	 * Events created from this demand
	 */
	@XmlElementWrapper(name="events")
	@XmlElement(name="event")
	private List<Event> events;

	/**
	 * Links from this demand
	 */
	@XmlElementWrapper(name="links")
	@XmlElement(name="link")
	private List<Link> links;
	
	/**
	 * Demand comments
	 */
	@XmlElementWrapper(name="comments")
	@XmlElement(name="comment")
	private List<Comment> comments;

	/**
	 * Return demand comments
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * Set demand comments.  
	 * Use the DemandOperations class to manipulate
	 * @see com.evdb.javaapi.operations.DemandOperations#addComment(String, Comment)
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Get the demand created date
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Set the demand created date
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Return the demand creator name
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Set the demand creator name
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * Return the demand description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the demand description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * List of events created by this demand
	 * @return the events
	 */
	public List<Event> getEvents() {
		return events;
	}

	/**
	 * Set the events 
	 * @param events the events to set
	 */
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	/**
	 * Get the demadn images
	 * @return the images
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * Set the demand image list.  
	 * Use the DemandOperations class to manipulate
	 * @see com.evdb.javaapi.operations.DemandOperations#addImage(String, Image)
	 * @param images the images to set
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}

	/**
	 * Return the demand links
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * Set the links for a demand.  
	 * Use the DemandOperations class to manipulate
	 * @see com.evdb.javaapi.operations.DemandOperations#addLink(String, Link)
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/**
	 * Return the demand location
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Set the demand location
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Return the total number of demand members
	 * @return the memberCount
	 */
	public int getMemberCount() {
		return memberCount;
	}

	/**
	 * Set the number of demand members
	 * @param memberCount the memberCount to set
	 */
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	/**
	 * Return the performer being demanded
	 * @return the performer
	 */
	public Performer getPerformer() {
		return performer;
	}

	/**
	 * Set the performer being demanded. Once the demand has been created, this cannot be changed
	 * @param performer the performer to set
	 */
	public void setPerformer(Performer performer) {
		this.performer = performer;
	}

	/**
	 * Return the SDID
	 * @return the sdid
	 */
	public String getSdid() {
		return sdid;
	}

	/**
	 * Set the SDID
	 * @param sdid the sdid to set
	 */
	public void setSdid(String sdid) {
		this.sdid = sdid;
	}

	/**
	 * Return the demand status
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the demand status
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * List the demand tags
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * Set the demand tags.  
	 * Use the DemandOperations class to manipulate
	 * @see com.evdb.javaapi.operations.DemandOperations#addTags(String, List)
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * Return whether the demand is withdrawn
	 * @return the withdrawn
	 */
	public boolean isWithdrawn() {
		return withdrawn;
	}

	/**
	 * Set whether the demand is withdrawn
	 * @param withdrawn the withdrawn to set
	 */
	public void setWithdrawn(boolean withdrawn) {
		this.withdrawn = withdrawn;
	}

	/**
	 * Note for why the demand was withdrawn
	 * @return the withdrawnNote
	 */
	public String getWithdrawnNote() {
		return withdrawnNote;
	}

	/**
	 * Set the withdrawn note
	 * @param withdrawnNote the withdrawnNote to set
	 */
	public void setWithdrawnNote(String withdrawnNote) {
		this.withdrawnNote = withdrawnNote;
	}


}
