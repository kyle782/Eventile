/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data representation of the Location object
 * Mapped from XML using JAXB
 * @author tylerv
 *
 */
@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
	
	private String original;
	
	@XmlElement(name="postal_code")
	private String postalCode;
	
	@XmlElement(name="city_name")
	private String city; 

	@XmlElement(name="metro_name")
	private String metro; 

	@XmlElement(name="region_name")
	private String region; 
	
	@XmlElement(name="country_name")
	private String country; 
	
	@XmlElement(name="lat")
	private double latitude;

	@XmlElement(name="lon")
	private double longitude;

	/**
	 * @return The original request that we were asked to resolve
	 */
	public String getOriginal() {
		return original;
	}

	/**
	 * @param original the original to set
	 */
	public void setOriginal(String original) {
		this.original = original;
	}

	/**
	 * @return The postal code of the location
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return The city name of the location
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city name to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Metro Name, only returned if the city is in a metro
	 */
	public String getMetro() {
		return metro;
	}

	/**
	 * @param metro the metro name to set
	 */
	public void setMetro(String metro) {
		this.metro = metro;
	}

	/**
	 * @return Region name
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region name to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return Country name
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country name to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return Latitude of the location, only applies to cities and postal codes
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return Longitude of the location, only applies to cities and postal codes
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
