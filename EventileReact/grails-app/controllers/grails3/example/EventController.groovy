package grails3.example

import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestBuilder
import grails.web.RequestParameter
import org.grails.web.json.JSONObject
import org.springframework.http.HttpStatus
import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.core.context.SecurityContextHolder

class EventController {

    static responseFormats = ['json']
    static allowedMethods = ['GET', 'POST', 'PUT']

    def springSecurityService

    def index() { }

    def show(String q) {

        // since events from the search result were saved to database, we find the event
        def target_event = Event.findByEventbrite_id(q)

        // perform a GET to eventbrite to retrieve the complete event information since the event's description is trimmed in database
        def response_eventbrite = new RestBuilder().get("https://www.eventbriteapi.com/v3/events/{id}"){
            header "Authorization", "Bearer 2S34UCIHKW5MXVP4S5M7" // authenticate with header
            urlVariables id:q
        }

        // cast response to JSON object
        JSONObject obj = (JSONObject) response_eventbrite.json

        target_event.description = obj["description"]["text"]
        target_event.save()

        /** get the venue's information (address, long, lat) **/
        String venue_id = target_event.eventbrite_venue_id
        def venue_information = new RestBuilder().get("https://www.eventbriteapi.com/v3/venues/{id}") {
            header "Authorization", "Bearer 2S34UCIHKW5MXVP4S5M7" // authenticate with header
            urlVariables id:venue_id
        }

        // cast response to JSON object
        JSONObject venue_obj = (JSONObject) venue_information.json

        target_event.venue_address = venue_obj["address"]["localized_address_display"]
        target_event.longitude = venue_obj["address"]["longitude"]
        target_event.latitude = venue_obj["address"]["latitude"]
        target_event.save()


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

        User user = User.get(springSecurityService.principal.id)

        Event new_event = new Event(name: event_name, description: event_description,
                location: event_location, start_date: event_date, category_name: "test cat", eventbrite_id: "123123",
                eventbrite_url: "blahblah", creator: user)


        if (!new_event.save()){
            System.out.println(new_event.errors)
        } else {
            System.out.println("saved event")
        }

        user.save()

        System.out.println("event saved to user? " + user.getCreatedEvents())

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

    @Secured(['ROLE_USER'])
    def get_user_created_events(){
        User user = User.get(springSecurityService.principal.id)
        def created_events = user.createdEvents
        respond created_events
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        respond Collections.emptyList(), status: HttpStatus.BAD_REQUEST
    }
}
