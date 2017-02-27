/**
 * (c) 2007 Eventful, Inc.
 * All rights reserved
 * 
 * Please see the accompanying LICENSE file for licensing information
 */
package com.evdb.javaapi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Helper class for marhalling and unmarshalling EVDB date formats via JAXB
 * @author tylerv
 *
 */
public class DateAdapter extends XmlAdapter<String, Date> {

	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	  public Date unmarshal(String date) throws Exception {
	    return df.parse(date);
	  }

	  public String marshal(Date date) throws Exception {
	    return df.format(date);
	  }
	}
