package grails3.example

import grails.plugin.springsecurity.annotation.Secured
import grails.web.RequestParameter
import org.springframework.http.HttpStatus
import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.core.context.SecurityContextHolder

class EventController {

    static responseFormats = ['json']
    static allowedMethods = ['GET', 'POST', 'PUT']

    def springSecurityService

    def index() { }

    def show(String q) {

        // since events from the search result were saved to database, we simply find the event and return it
        def target_event = Event.findByEventbrite_id(q)

        respond target_event

    }

    @Secured(['ROLE_USER'])
    // obtain parameters from the REST call in createEvent() in create-event.js
    def create_event(@RequestParameter('event_name') String event_name,
                     @RequestParameter('event_date') String event_date,
                     @RequestParameter('event_location') String event_location,
                     @RequestParameter('event_description') String event_description){

        if (!event_name || !event_date || !event_location || !event_description){
            throw new IllegalArgumentException("You must enter a name, description, location, and start date for the event!")
        }

        System.out.println("name = " + event_name + ", date = " + event_date + ", location = " +
                event_location + ", description = " + event_description)

        Event new_event = new Event(name: event_name, description: event_description,
                location: event_location, start_date: event_date).save(flush:true)

        System.out.println("created event in controller!")

        User user = User.get(springSecurityService.principal.id)
        System.out.println("got the user ")

        //user.addToCreated_events(new_event)
        System.out.println("added")

        user.save(flush:true)
        System.out.println("saved")

        respond new_event, status: HttpStatus.CREATED

    }

    @Secured(['ROLE_USER'])
    // obtain parameters from the REST call in update_rating() in event-page.js
    def update_rating(@RequestParameter('q') String q, @RequestParameter('r') int r){
        def target_event = Event.findByEventbrite_id(q)

        int current_num_ratings = target_event.num_ratings

        if (current_num_ratings == 0){
            target_event.total_rating = r
            target_event.num_ratings++
            target_event.average_rating = r
        } else {
            target_event.num_ratings++
            target_event.total_rating+= r
            target_event.average_rating = (target_event.total_rating)/(target_event.num_ratings)
        }

        // save to database, print errors for debugging if unable to save
        if(!target_event.save(flush:true) ) {
            System.out.println(target_event.errors)
        }

        respond target_event
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        respond Collections.emptyList(), status: HttpStatus.BAD_REQUEST
    }
}
