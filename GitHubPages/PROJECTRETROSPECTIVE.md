# Project Retrospective Page

## Workflow

What was the process or branching model each person used to pull, develop and push?
* Each person had their own branch that they worked in individually.
* When their part was completed and functional, the contents or changes in their personal branch were then merged into a develop branch (dev) that had all the completed and updated changes from everyone at that stage
* Once all the features were implemented for that stage, the contents of the dev branch was then pushed up to the master branch for submission

What development tools/frameworks did your project use?
* React.js was used to implement the frontend.
* Bootstrap was used as the template for the majority of user interface components
* Grails was used to implement an MVC model of application development, with grails domain classes being used to implement our database
* Webpack was used to bundle all of our frontend modules (javascript coded using a react framework) so that it could be used in a browser.
* Git was used as our VCS, enabling us to interact as a group.
* Gradle was used for build automation of the project
* JVM was necessary for any classes written using Groovy, as many features needed to be imported from JVM
* Intellij Idea was used as our IDE, allowing us to write code and interact with our VCS

How effectively did your team manage task distribution among team members?
* In our weekly team meeting, we broke down the tasks for that week into sub points and assigned each to a member of our team to complete. These tasks are written in the meeting minutes as well as on slack. 
* Additional team meetings to collaborate on specific tasks (ie. Backend coding fixes, implementation of specific features, interface issues, ect) would be scheduled wherever required at the end of the weekly meeting.
* At the next weekly meeting, we would go over the assignments each person had and work on parts that were previously incomplete
* We then went over the current state of the project and created new tasks that we once again distribute to the members of the group
* The same process repeats again all future meetings

## MVC / Design Patterns

What is the relationship between user interfaces, application logic, and data in your project?
* The user interacts with the interface to sign in and create a profile based on the Spring authentication procedure discussed in class.
 The user can create or edit user data through the interface in a way that permanently affects their associated user domain class.
* Event data can be stored and created by the user through the user interface but it is usually loaded by calls to Eventbrite

Did you implement your server-side program as a REST API?
* Yes. 
* A series of URL mappings were kept for any communication that needed to move from frontend to backend.
* This includes but is not limited to Searching for events, signing up with a profile, creating an event, commenting or rating an event.
* When the user needed to interact with the server, they would do so through one of these urls as an API call.

Which collection of classes serve as the M, V, and C in MVC?
* The User, Comment, Event, and Rating classes were implemented as Groovy domains, together constituting our project’s Model
* The Sign in page, Sign up page, Navigation bar, Welcome page, event page, User home dashboard and Search screen were all implemented as React classes and constituted our project’s View 
* The Controllers and Service classes for User, Welcome, Search, and the User’s Home Dashboard, were implemented using groovy. Additional controllers for the Events and comments were

How do these classes communicate with each other? Give an example based on a user action.
* Calls were made from the front end React classes to a series of controller classes. Which then used service classes to communicate with APIs such as Evenbrite or Google Maps. These communications effected the Elements of our database.
* For instance, a search would make a fetch call to a SearchController class, which then interacted with a service class that made API calls to Eventbrites Search functions. This adds a number of event elements which can be then accessed by the user, each with a corresponding Event Page 
* If you were to start the project again today, what aspects of code organization could be improved in your project?
* Angular was a tool which captivated our interest before the project began.
* The idea of having the whole MVC framework implemented through Angular may have made our code more unified and the communication from frontend to backend a little more simple
* This would have possibly saved some of the stress of formatting an api call, as we would not be making these with both React and Groovy. 

## Refactor Retrospective

Areas of your design that you felt were strong:
* Simplicity. At the core, this project was simply the interaction between a user and events. This was a very firm anchor. Despite having a variety of functions, every class was linked to one these two factors, which means it never really felt as though the role of a class was unclear in the big picture.
* With the main event search as well as the automatic searches used for the Home dashboard and the welcome page, the transfer of a large amount of information was done quite smoothly. A loop processed through a JSON response and events as individual tiles, one at a time. This felt smooth and could have been a very slow part of the project if implemented differently.

Areas of your design that you felt were weak:
* We tailored our categories based on Eventbrites categories, as these would have come with the response to the Event Searching API call. Searching through multiple event database services (such as Ticketmaster or Eventful) then became very difficult, as each service has it’s own system of tags that may not necessarily interact well with the Eventbrite system. Searching multiple databases was a goal of the app, but in order to realize it, we may have needed to establish our own categories and do the categorization of events found through the Eventbrite search ourselves.
* Our sort by functions were also something we ultimately depended on Eventbrite for. Not only was this a second limitation on our ability to use a search across multiple databases, but it also meant that we could only search by Event price based on the binary of free or paid events. A more thorough parsing of the event description could have revealed data like exact price, allowing us to sort events by increasing price, which would be a particularly useful search filter.

## Project Retrospective

What did your group do well?
* We were able to meet all the deadlines given to us. 
* We tended to be good at teaching eachother how to work with parts of the program we don’t normally use. This meant that workload could be better distributed. 

What could your group have done better?
* We could have set more concrete and timely deadlines for our parts
* The separation between roles sometimes dissolved when things became chaotic and it sometimes appeared that many members were working on the same thing, while other

What did you like about the tools and frameworks you used?
* We all already knew Java so doing the backend parts was not as hard as it could have been.
* Reactjs is relatively easy to learn and use.
* Github did provide many opportunities to work as a group despite varying schedules.
* The ability to use Grails to domains as a backend saved a little time learning something MySQL. 

What didn’t you like about the tools and frameworks you used?
* Using Reactjs and webpack really ballooned the code size and made compilation take a lot of time.
* Running the application in grails took a long time and made it difficult to quickly make changes to the application that was not working

