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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.evdb.javaapi.data.response.GenericErrorResponse;
import com.evdb.javaapi.util.DateAdapter;

/**
 * Event class.  
 * <p>
 * Use the EventOperations class to manipulate
 * @see com.evdb.javaapi.operations.EventOperations
 * @author tylerv
 *
 */
@XmlRootElement(name="event")
@XmlSeeAlso({GenericErrorResponse.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {
	
    /**
     * Event ID, or SEID in Eventful parlance
     */
	@XmlAttribute(name="id")
	private String seid;
	
	/**
	 * Event title
	 */
	private String title;
	
	/**
	 * Event URL
	 */
	private String url;
	
	/**
	 * Description
	 * 
	 */
	private String description;
	
	/**
	 * Event start time
	 */
	@XmlElement(name="start_time")
	@XmlJavaTypeAdapter(DateAdapter.class)
	protected Date startTime;
	
	/**
	 * Event end time.  Not all events have end times
	 */
	@XmlElement(name="stop_time")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date stopTime;
	
	/**
	 * Timezone of the event in Olson format
	 */
	@XmlElement(name="tz_olson_path")
	private String olsonPath;
	
	/**
	 * Whether the event is an all-day event
	 */
	@XmlElement(name="all_day")
	private boolean allDay = false;
	
	/**
	 * Whether the event is free
	 */
	private boolean free = false;
	
	/**
	 * Event price
	 */
	private String price;
	
	/**
	 * Whether the event is withdrawn (deleted)
	 */
	private boolean withdrawn;
	
	@XmlElement(name="withdrawn_note")
	private String withdrawnNote;
	
	/**
	 * Privacy of the event.  "1" is public
	 */
	private int privacy = 1;
	
	/**
	 * List of parent events
	 */
	@XmlElementWrapper(name="parents")
	@XmlElement(name="parent")
	private List<Event> parents;
	
	/**
	 * List of child events
	 */
	@XmlElementWrapper(name="children")
	@XmlElement(name="child")
	private List<Event> children;
	
	/**
	 * List of links
	 */
	@XmlElementWrapper(name="links")
	@XmlElement(name="link")
	private List<Link> links;
	
	/**
	 * Event comments
	 */
	@XmlElementWrapper(name="comments")
	@XmlElement(name="comment")
	private List<Comment> comments;
	
	/**
	 * Event trackbacks
	 */
	@XmlElementWrapper(name="trackbacks")
	@XmlElement(name="trackback")
 	private List<Trackback> trackbacks;

	/**
	 * Performers for the event
	 */
	@XmlElementWrapper(name="performers")
	@XmlElement(name="performer")
 	private List<Performer> performers;

	/**
	 * MultipleImages
	 */
	@XmlElementWrapper(name="images")
	@XmlElement(name="image")
 	private List<Image> images;
	
	/**
	 * SingleImage
	 */
	@XmlElement(name="image")
 	private List<Image> singleImageList;
	
	/**
	 * List f tags
	 */
	@XmlElementWrapper(name="tags")
	@XmlElement(name="tag")
 	private List<Tag> tags;

	/**
	 * Calendars the event belongs to
	 */
	@XmlElementWrapper(name="calendars")
	@XmlElement(name="calendar")
 	private List<Calendar> calendars;
 	
	/**
	 * Event properties
	 */
	@XmlElementWrapper(name="properties")
	@XmlElement(name="property")
 	private List<Property> properties;
	
	/**
	 * Groups the event belongs to
	 */
	@XmlElementWrapper(name="groups")
	@XmlElement(name="group")
 	private List<Group> groups;

	/**
	 * Users watching or going to the event
	 */
	@XmlElementWrapper(name="going")
	@XmlElement(name="user")
 	private List<User> going;

	/**
	 * Calendars the event appears on
	 */
	@XmlElementWrapper(name="categories")
	@XmlElement(name="category")
 	private List<Category> categories;

	@XmlTransient
	private Venue venue;
	
	@XmlElement(name="venue_name")
	private String venueName;
	
	@XmlElement(name="venue_id")
	private String svid;
	
	@XmlElement(name="venue_type")
	private String venueType;
	
	@XmlElement(name="venue_display")
	private int venueDisplay;
	
	@XmlElement(name="venue_address")
	private String venueAddress;
	
	@XmlElement(name="city_name")
	private String venueCity;
	
	@XmlElement(name="region_name")
	private String venueRegion;
	
	@XmlElement(name="region_abbr")
	private String venueRegionAbbreviation;
	
	@XmlElement(name="postal_code")
	private String venuePostalCode;
	
	@XmlElement(name="country_name")
	private String venueCountry;
	
	@XmlElement(name="county_abbr2")
	private String venueCountryTwoLetterAbbreviation;
	
	@XmlElement(name="country_abbr")
	private String venueCountryThreeLetterAbbreviation;
	
	@XmlElement(name="latitude")
	private double venueLatitude;
	
	@XmlElement(name="longitude")
	private double venueLongitude;
	
	@XmlElement(name="geocode_type")
	private String venueGeocodeType;
	
	@XmlElement(name="recur_string")
	private String recurString;
	
	public String getRecurString() {
		return recurString;
	}

	public void setRecurString(String recurString) {
		this.recurString = recurString;
	}
	
	/*
	@XmlElementWrapper(name="recurrence")
	@XmlElement(name="description")
	private String recurDescription;
	*/

	/*
	public String getRecurDescription() {
		return recurDescription;
	}

	public void setRecurDescription(String recurDescription) {
		this.recurDescription = recurDescription;
	}
*/
	/**
	 * Return the venue from the given information.  If a venue object does not yet exist, but
	 * an SVID or venue name is present, a venue object will be constructed from this data and 
	 * returned
	 * @return The Venue Object
	 */
	public Venue getVenue() {
		//return a venue given the aforementioned data
		
		if (venue != null) {
			return venue;
		}
		
		if (((venueName == null) || (venueName.length() == 0))
				&& ((svid == null) || (svid.length() == 0))) {
			return null;
		}
				
		Venue v = new Venue();
		
		v.setName(venueName);
		v.setSvid(svid);
		v.setType(venueType);
		v.setDisplay(venueDisplay);
		v.setAddress(venueAddress);
		v.setCity(venueCity);
		v.setRegion(venueRegion);
		v.setRegionAbbreviation(venueRegionAbbreviation);
		v.setPostalCode(venuePostalCode);
		v.setCountry(venueCountry);
		v.setCountryTwoLetterAbbreviation(venueCountryTwoLetterAbbreviation);
		v.setCountryThreeLetterAbbreviation(venueCountryThreeLetterAbbreviation);
		v.setLatitude(venueLatitude);
		v.setLongitude(venueLongitude);
		v.setGeocodeType(venueGeocodeType);
		
		
		return v;
	}
	
	/**
	 * Whether the event is all day or not
	 * @return the allDay
	 */
	public boolean isAllDay() {
		return allDay;
	}

	/**
	 * Whether the event is all day or not
	 * @param allDay the allDay to set
	 */
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	/**
	 * Event description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Event description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * The event SEID
	 * @return the seid
	 */
	public String getSeid() {
		return seid;
	}

	/**
	 * The event SEID
	 * @param seid the seid to set
	 */
	public void setSeid(String seid) {
		this.seid = seid;
	}

	/**
	 * Event start time
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Event start time
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Event stop time
	 * @return the stopTime
	 */
	public Date getStopTime() {
		return stopTime;
	}

	/**
	 * Event stop time
	 * @param stopTime the stopTime to set
	 */
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * Event title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Event URL
	 * @return the URL
	 */
	public String getURL() {
		return url;
	}

	/**
	 * Title of the event
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Venue for the event
	 * @param venue the venue to set
	 */
	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	/**
	 * Event children
	 * @return the children
	 */
	public List<Event> getChildren() {
		return children;
	}

	/**
	 * Event children
	 * @param children the children to set
	 */
	public void setChildren(List<Event> children) {
		this.children = children;
	}

	/**
	 * Whether the event is free or not
	 * @return the free
	 */
	public boolean isFree() {
		return free;
	}

	/**
	 * @param free the free to set
	 */
	public void setFree(boolean free) {
		this.free = free;
	}

	/**
	 * Return parent events
	 * @return the parents
	 */
	public List<Event> getParents() {
		return parents;
	}

	/**
	 * Set parent events
	 * @param parents the parents to set
	 */
	public void setParents(List<Event> parents) {
		this.parents = parents;
	}

	/**
	 * Price of the event
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Set the event price
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * Whether the event is withdrawn (or deleted) or not
	 * @return the withdrawn
	 */
	public boolean isWithdrawn() {
		return withdrawn;
	}

	/**
	 * Delete the event.  
     * Use the EventOperations class to manipulate
     * @see com.evdb.javaapi.operations.EventOperations#withdraw(String, String)
	 * @param withdrawn the withdrawn to set
	 */
	public void setWithdrawn(boolean withdrawn) {
		this.withdrawn = withdrawn;
	}

	/**
	 * Get the withdrawn note
	 * @return the withdrawnNote
	 */
	public String getWithdrawnNote() {
		return withdrawnNote;
	}

	/**
	 * Set the withdrawn note.  
     * Use the EventOperations class to manipulate
     * @see com.evdb.javaapi.operations.EventOperations#withdraw(String, String)
	 * @param withdrawnNote the withdrawnNote to set
	 */
	public void setWithdrawnNote(String withdrawnNote) {
		this.withdrawnNote = withdrawnNote;
	}

	/**
	 * List of event links
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * Set event links.  
     * Use the EventOperations class to manipulate
     * @see com.evdb.javaapi.operations.EventOperations#addLink(String, Link)
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/**
	 * Return event comments
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * Set the event comments.  
     * Use the EventOperations class to manipulate
     * @see com.evdb.javaapi.operations.EventOperations#addComment(String, Comment)
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Get the list of performers for the event
	 * @return the performers
	 */
	public List<Performer> getPerformers() {
		return performers;
	}

	/**
	 * Set the list of event performers.  
     * Use the EventOperations class to manipulate
     * @see com.evdb.javaapi.operations.EventOperations#addPerformer(String, Performer)
	 * @param performers the performers to set
	 */
	public void setPerformers(List<Performer> performers) {
		this.performers = performers;
	}

	/**
	 * Trackback URLs to the event
	 * @return the trackbacks
	 */
	public List<Trackback> getTrackbacks() {
		return trackbacks;
	}

	/**
	 * Set the trackback URLs.
	 * @param trackbacks the trackbacks to set
	 */
	public void setTrackbacks(List<Trackback> trackbacks) {
		this.trackbacks = trackbacks;
	}

	/**
	 * List of images for the event
	 * @return the images
	 */
	public List<Image> getImages() {
		if(singleImageList != null) {
			return singleImageList;
		} else {
			return images;
		}
	}

	/**
	 * List of images to set.  
     * Use the EventOperations class to manipulate
     * @see com.evdb.javaapi.operations.EventOperations#addImage(String, Image)
	 * @param images the images to set
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}

	/**
	 * Return the list of event tags
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * Set the list of event tags.  
     * Use the EventOperations class to manipulate
     * @see com.evdb.javaapi.operations.EventOperations#addTags(String, List)
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * Return a list of calendars featuring this event
	 * @return the calendars
	 */
	public List<Calendar> getCalendars() {
		return calendars;
	}

	/**
	 * Set calendars with this event.  
     * Use the CalendarOperations class to manipulate
	 * @param calendars the calendars to set
	 */
	public void setCalendars(List<Calendar> calendars) {
		this.calendars = calendars;
	}

	/**
	 * A list of event properites
	 * @return the properties
	 */
	public List<Property> getProperties() {
		return properties;
	}

	/**
	 * Set the event properties.  
     * Use the EventOperations class to manipulate
     * @see com.evdb.javaapi.operations.EventOperations#addProperty(String, Property)
	 * @param properties the properties to set
	 */
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	/**
	 * A list of event categories
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * Set the event categories.  
     * Use the EventOperations class to manipulate
     * @see com.evdb.javaapi.operations.EventOperations#addTags(String, List)
	 * @param categories the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	/**
	 * List of users going to the event
	 * @return the going
	 */
	public List<User> getGoing() {
		return going;
	}

	/**
	 * Set the users going to the event.  
     * Use the UserOperations class to manipulate
	 * @param going the going to set
	 */
	public void setGoing(List<User> going) {
		this.going = going;
	}

	/**
	 * Groups that feature this event
	 * @return the groups
	 */
	public List<Group> getGroups() {
		return groups;
	}

	/**
	 * Set the group list
	 * @param groups the groups to set
	 */
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	/**
	 * Event privacy.  1 is Public, 2 is Private
	 * @return the privacy
	 */
	public int getPrivacy() {
		return privacy;
	}

	/**
	 * Set the event privacy.  1 is Public, 2 is Private
	 * @param privacy the privacy to set
	 */
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}

	/**
	 * The timezone olson path for the event
	 * @return the olsonPath
	 */
	public String getOlsonPath() {
		return olsonPath;
	}

	/**
	 * The timezone olson path for the event
	 * @param olsonPath the olsonPath to set
	 */
	public void setOlsonPath(String olsonPath) {
		this.olsonPath = olsonPath;
	}

	/**
	 * SVID for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the svid
	 */
	public String getSvid() {
		return svid;
	}

	/**
	 * SVID for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param svid the svid to set
	 */
	public void setSvid(String svid) {
		this.svid = svid;
	}

	/**
	 * Address for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueAddress
	 */
	public String getVenueAddress() {
		return venueAddress;
	}

	/**
	 * Address for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueAddress the venueAddress to set
	 */
	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}

	/**
	 * City for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueCity
	 */
	public String getVenueCity() {
		return venueCity;
	}

	/**
	 * City for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueCity the venueCity to set
	 */
	public void setVenueCity(String venueCity) {
		this.venueCity = venueCity;
	}

	/**
	 * Country for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueCountry
	 */
	public String getVenueCountry() {
		return venueCountry;
	}

	/**
	 * Country for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueCountry the venueCountry to set
	 */
	public void setVenueCountry(String venueCountry) {
		this.venueCountry = venueCountry;
	}

	/**
	 * Country Abbreviations for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueCountryThreeLetterAbbreviation
	 */
	public String getVenueCountryThreeLetterAbbreviation() {
		return venueCountryThreeLetterAbbreviation;
	}

	/**
	 * Country Abbreviation for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueCountryThreeLetterAbbreviation the venueCountryThreeLetterAbbreviation to set
	 */
	public void setVenueCountryThreeLetterAbbreviation(
			String venueCountryThreeLetterAbbreviation) {
		this.venueCountryThreeLetterAbbreviation = venueCountryThreeLetterAbbreviation;
	}

	/**
	 * Country Abbreviation for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueCountryTwoLetterAbbreviation
	 */
	public String getVenueCountryTwoLetterAbbreviation() {
		return venueCountryTwoLetterAbbreviation;
	}

	/**
	 * Country Abbreviation for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueCountryTwoLetterAbbreviation the venueCountryTwoLetterAbbreviation to set
	 */
	public void setVenueCountryTwoLetterAbbreviation(
			String venueCountryTwoLetterAbbreviation) {
		this.venueCountryTwoLetterAbbreviation = venueCountryTwoLetterAbbreviation;
	}

	/**
	 * Display variable for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueDisplay
	 */
	public int getVenueDisplay() {
		return venueDisplay;
	}

	/**
	 * Display variable for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueDisplay the venueDisplay to set
	 */
	public void setVenueDisplay(int venueDisplay) {
		this.venueDisplay = venueDisplay;
	}

	/**
	 * Geocode Type for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueGeocodeType
	 */
	public String getVenueGeocodeType() {
		return venueGeocodeType;
	}

	/**
	 * Geocode Type for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueGeocodeType the venueGeocodeType to set
	 */
	public void setVenueGeocodeType(String venueGeocodeType) {
		this.venueGeocodeType = venueGeocodeType;
	}

	/**
	 * Latitude for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueLatitude
	 */
	public double getVenueLatitude() {
		return venueLatitude;
	}

	/**
	 * Latitude for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueLatitude the venueLatitude to set
	 */
	public void setVenueLatitude(double venueLatitude) {
		this.venueLatitude = venueLatitude;
	}

	/**
	 * Longitude for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueLongitude
	 */
	public double getVenueLongitude() {
		return venueLongitude;
	}

	/**
	 * Longitude for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueLongitude the venueLongitude to set
	 */
	public void setVenueLongitude(double venueLongitude) {
		this.venueLongitude = venueLongitude;
	}

	/**
	 * Name for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueName
	 */
	public String getVenueName() {
		return venueName;
	}

	/**
	 * Name for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueName the venueName to set
	 */
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	/**
	 * Postal Code for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venuePostalCode
	 */
	public String getVenuePostalCode() {
		return venuePostalCode;
	}

	/**
	 * Postal Code for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venuePostalCode the venuePostalCode to set
	 */
	public void setVenuePostalCode(String venuePostalCode) {
		this.venuePostalCode = venuePostalCode;
	}

	/**
	 * Region for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueRegion
	 */
	public String getVenueRegion() {
		return venueRegion;
	}

	/**
	 * Region for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueRegion the venueRegion to set
	 */
	public void setVenueRegion(String venueRegion) {
		this.venueRegion = venueRegion;
	}

	/**
	 * Region Abbreviation for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueRegionAbbreviation
	 */
	public String getVenueRegionAbbreviation() {
		return venueRegionAbbreviation;
	}

	/**
	 * Region Abbreviation for the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueRegionAbbreviation the venueRegionAbbreviation to set
	 */
	public void setVenueRegionAbbreviation(String venueRegionAbbreviation) {
		this.venueRegionAbbreviation = venueRegionAbbreviation;
	}

	/**
	 * Tyoe of the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @return the venueType
	 */
	public String getVenueType() {
		return venueType;
	}

	/**
	 * Tyoe of the venue the event appears at
	 * Note that the Venue member is the preferred way to retrieve this information
	 * @param venueType the venueType to set
	 */
	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}

}
