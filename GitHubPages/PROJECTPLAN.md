# Project Plan Page for Eventile

## Description

Eventile is the one stop shop for events happening across the major event platforms (EventBrite, Ticketmaster, and Eventful) on the internet. Eventile allows for users to search for events across the major platforms and displays the events as tiles in the browser. Eventile also allows users to create their own customized profile where they can pick and choose event categories that they are interested in. The users will then be shown events matching their interest. Below are some of the basic features that will be implemented in Eventile.

## Updates (03/03/2017)
  Stage 2 has completed classes to handle user account creation and some event searching (more detail below). When a user account is already created but a user attempts to create an account under that name, an error message is thrown and the duplicate account cannot be created. 

## Basic Features

**_User Profile Stores Information_** <br> 
  Users of Eventile can sign up for a profile where they will be asked to provide their interests in terms of event categories. Such categories are decided depending on the APIs of the various event platforms that Eventile will be pulling from. Examples are concerts, seminars, parties, etc.
  
  ### Implemented in Stage 2 using a grails user domain. A signup form takes in user name, email, password, and preferences     and creates their lasting account

**_Suggested events_** <br>
  Based on themes either manually entered by the user or taken from the events themselves, Eventile notifies the user of events they may prefer. User profile information like this is stored in our database and the themes to categorize events come with the events found by API calls to EventBrite, Ticketmaster, or Eventful.
  
**_Event Searching_** <br>
  When searching for a particular event Eventile's, event searching runs through multiple different databases of events in sequence. Eventile will make API call to Eventbrite, then to Ticketmaster, then to Eventful, in sequence to call to find the best match to whatever the user is searching for. 
  
  ### Implemented in Stage 2 using API calls to Eventbrite's event database. Currrently only running calls to search           Eventbrite.
  
**_Event Browsing_** <br>
  Without even searching, the user's event page will use API calls to Eventbrite and load a steady stream of events to browse through. 
  
**_Event Advertising_** <br>
  Users will be able to create their own events through our events creation page. Their created event will then show up in searches dependent on the tags and categories that they provide in the event creation page.

**_Event Rating With Comments_** <br>
  Events will have a rating associated in order to notify users of how previous attendees felt about it. A comments section will also be available for past attendees to write about their experience at the event or previous events by the same host.
  
**_Event Categories_** <br>
  Events will be sorted by categories depending on the categorization in the given API. When a user signs up for a profile, they will be asked to pick categories of events that they are interested in. They then will have a homepage dashboard filled with events pulled from various major event websites that will display events related to their interests.
  
  ### Implemented in Stage 2 using Eventbrite's categories. Categories are represented by a groovy domain and when a user is   created, they are given the opportunity to add interests representing categories. 

**_Sorting Events (by location, price, ect.)_** <br>
  Users can sort their displayed list of events depending on a specific criteria or filter that they choose. This can be done on any page and allows for users to see events in descending order depending on the filter that they chose. 

**_See People Going to Event_** <br>
  Users are able to see others users who RSVP'ed to an event on the event page.
  
## Possible Advanced Features

**_Social Media Integration_** <br>
  We have been looking into integrating the Facebook API into the application. This will allow for users to connect their Eventile profile to their Facebook accounts so that they can keep track of their friends' events.
 
[Go back to Home Page](../README.md)
