# Project Plan Page for Eventile

### Implementation Plan

**_User Profile Stores Preferences_** <br>
  Based on themes either manually entered by the user or taken from the events themselves, Eventile notifies the user of events they may prefer. User profile information like this is stored in our database and the themes to categorize events come with the events found by API calls to Eventbrite, Ticketmaster, or Eventful.
  
**_Event Searching_** <br>
  When searching for a particular event Eventile's, event searching runs through multiple different databases of events in sequence. Eventile will make API calls first to Eventbrite, then to Ticketmaster then to Eventful in sequence to call to find the best match to whatever the user is searching for. 
  
**_Event Browsing_** <br>
  Without even searching, the user's event page will use API calls to Eventbrite and load a steady stream of events to browse through.
  


[Go back to Home Page](../README.md)
