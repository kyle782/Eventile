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

    @Secured(['ROLE_USER'])
    def show_created_event(String q){
        // since events from the search result were saved to database, we find the event
        def target_event = Event.findByEventbrite_id(q)
        respond target_event, status: HttpStatus.ACCEPTED
    }

    def show_created_event_public(String q){
        def target_event = Event.findByEventbrite_id(q)
        respond target_event, status: HttpStatus.ACCEPTED
    }

    @Secured(['ROLE_USER'])
    def show_rated_event(String q){
        Long id = Integer.parseInt(q)
        System.out.println("id = " + id)
        def target_event = Event.findById(id)
        System.out.println("target_event is " + target_event)
        respond target_event
    }

    @Secured(['ROLE_USER'])
    def check_if_user_rsvpd(String q){
        def target_event = Event.findByEventbrite_id(q)
        User user = User.get(springSecurityService.principal.id)

        ArrayList<String> response = new ArrayList<String>()
        boolean found = false

        for (User attendee : target_event.attendees){
            if (attendee.username == user.username){
                found = true
            }
        }
        if (found){
            response.add(0, "found")
            respond response
        } else {
            respond response
        }

    }

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

        if (obj["status_code"] == 404){
            throw new IllegalArgumentException("Could not find event")
        }

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
                     @RequestParameter('event_description') String event_description,
                     @RequestParameter('event_location') String event_location,
                     @RequestParameter('event_date') String event_date,
                     @RequestParameter('event_time') String event_time,
                     @RequestParameter('event_timezone') String event_timezone,
                     @RequestParameter('event_category') String event_category,
                     @RequestParameter('event_img') String event_img){

        if (!event_name || !event_date || !event_location || !event_description || !event_category || !event_time || !event_timezone){
            throw new IllegalArgumentException("You must enter a name, description, location, and start date for the event!")
        }

        User user = User.get(springSecurityService.principal.id)

        // genereate a random id for the event
        int random_id_int = Math.abs(new Random().nextInt() % 15000000) + 1
        String random_id = String.valueOf(random_id_int)

        Event new_event = new Event(name: event_name, description: event_description, venue_address: event_location,
                start_date_local: event_date, start_date_local_time: event_time, start_date_timezone: event_timezone,
                category_name: event_category, img_url: event_img, eventbrite_id: random_id, eventbrite_url: "blahblah", creator: user)

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
    def update_comments(@RequestParameter('q') String q, @RequestParameter('c') String c){
        def target_event = Event.findByEventbrite_id(q)
        def newComment = new Comment()
        newComment.comment_body = c
        target_event.addToComments(newComment)

        // save to database, print errors for debugging if unable to save
        if(!target_event.save(flush:true) ) {
            System.out.println(target_event.errors)
        }

        respond target_event
    }

    @Secured(['ROLE_USER'])
    // obtain parameters from the REST call in update_rating() in event-page.js
    def update_rating(@RequestParameter('q') String q, @RequestParameter('r') int r){
        def target_event = Event.findByEventbrite_id(q)

        User user = User.get(springSecurityService.principal.id)

        int current_num_ratings = target_event.num_ratings

        Rating rating = Rating.findByEventAndRater(target_event, user)

        if (rating){

            target_event.total_rating -= rating.users_rating
            target_event.total_rating += r
            target_event.average_rating = (target_event.total_rating)/(target_event.num_ratings)

            rating.users_rating = r

        } else {

            rating = new Rating(users_rating: r, rater: user, event: target_event,
                    event_name: target_event.name, eventbrite_id: target_event.eventbrite_id)
            user.addToRatings(rating)

            target_event.total_rating += r
            target_event.num_ratings += 1
            target_event.average_rating = (target_event.total_rating)/(target_event.num_ratings)

        }

        rating.save()
        user.save()

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

    @Secured(['ROLE_USER'])
    def get_related_events(String current_event_category, String q){

        def related_events_all = Event.findAllByCategory_name(current_event_category)
        def related_events = related_events_all.subList(0, 4)

        respond related_events
    }

    def get_related_events_public(String current_event_category, String q){

        def related_events_all = Event.findAllByCategory_name(current_event_category)
        def related_events = related_events_all.subList(0, 4)

        respond related_events
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        respond Collections.emptyList(), status: HttpStatus.BAD_REQUEST
    }
}