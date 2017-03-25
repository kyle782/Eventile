# Project Plan Page for Eventile

## Description

Eventile is the one stop shop for events happening across the major event platforms (EventBrite, Ticketmaster, and Eventful) on the internet. Eventile allows for users to search for events across the major platforms and displays the events as tiles in the browser. Eventile also allows users to create their own customized profile where they can pick and choose event categories that they are interested in. The users will then be shown events matching their interest. Below are some of the basic features that will be implemented in Eventile.

## Updates Summary, ver 1.1 (03/03/2017)
  Stage 2 has completed classes to handle user account creation and some event searching (more detail below). When a particular account is already created but a then a user attempts to create an account under that name, an error message is thrown and the duplicate account cannot be created. 

## Updates Summary, ver 1.2 (03/24/2017)
  In Stage 3 we have created multiple classes and controllers to handle various aspects of the app based on our user stories. Before a user is signed in, the welcome page will show popular events in the form of a "tile", which includes an image of the event and a brief description. 
  
  In the search page, the user now has the ability to sort their searches by date, distance, relevancy, or if they choose, filters to show only the free events. The events will be shown in the same way that it was in the welcome page but filtered to fit the choice made by the user. 
  
  Users are now also able to advertise their own events which will show on the site as a regular event. In each event, the user will now not only be able to see a brief description of the event, but also rate, comment, RSVP, or show their excitement for the event. Relevant events will now also be shown in the event page, allowing users to navigate to other events similar to the one that they have chosen previously.
  
  User profiles and information can now be changed by the user with one click in the user profile page. They will be given the option to change the the information that they signed up with and replace it with new ones if they wish. The profile page also now shows much more information than it did before. The page will now also show the user events that they created as well as events that they have RSVP'ed to and rated. The list of preferences that the user signed up with will also be shown, and of course, the user can change their preferences anytime in the profile page.
  
  
  
## Features Implemented

We Implemented all 14 of our list of user stories. Here are the 14 broken down into features

**_User Profile Stores Information_** <br> 
  Users of Eventile can sign up for a profile where they will be asked to provide their interests in terms of event categories. Such categories are decided depending on the APIs of the various event platforms that Eventile will be pulling from. Examples are concerts, seminars, parties, etc.

**_Suggested Events_** <br>
  Based on themes either manually entered by the user or taken from the events themselves, Eventile notifies the user of events they may prefer. User profile information like this is stored in our database and the themes to categorize events come with the events found by API calls to EventBrite, Ticketmaster, or Eventful.
  
**_Event Searching_** <br>
  When searching for a particular event Eventile's, event searching runs through multiple different databases of events in sequence. Eventile will make API call to Eventbrite, then to Ticketmaster, then to Eventful, in sequence to call to find the best match to whatever the user is searching for. 
  
**_Event Browsing_** <br>
  Without even searching, the user's event page will use API calls to Eventbrite and load a steady stream of events to browse through. 

**_Event Rating With Comments_** <br>
  Events will have a rating associated in order to notify users of how previous attendees felt about it. A comments section will also be available for past attendees to write about their experience at the event or previous events by the same host.
  
**_Users Can Filter Events_** <br>
  Events can be sorted based on price, distance, and rating. This allows for quick filtering of events based on the needs of the user

**_Sorting Events (by location, price, ect.)_** <br>
  Users can sort their displayed list of events depending on a specific criteria or filter that they choose. This can be done on any page and allows for users to see events in descending order depending on the filter that they chose. 

  
## Advanced Features Implemented

**_Event Advertising_** <br>
  Users will be able to create their own events through our events creation page. Their created event will then show up in searches dependent on the tags and categories that they provide in the event creation page.


## Advanced Features That Were Not Implemented

**_Social Media Integration_** <br>
  We have been looking into integrating the Facebook API into the application. This will allow for users to connect their Eventile profile to their Facebook accounts so that they can keep track of their friends' events.


[Go back to Home Page](../README.md)
