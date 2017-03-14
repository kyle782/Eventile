package grails3.example

import grails.plugin.springsecurity.annotation.Secured
import grails.web.RequestParameter


class EventController {

    static responseFormats = ['json']

    def index() { }

    def show(String q) {

        // since events from the search result were saved to database, we simply find the event and return it
        def target_event = Event.findByEventbrite_id(q)

        respond target_event

    }

    @Secured(['ROLE_USER'])
    // obtain parameters from the REST call in update_rating() in event-page.js
    def update_rating(@RequestParameter('q') String q, @RequestParameter('r') int r){
        def target_event = Event.findByEventbrite_id(q)

        Event.findAllBy

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
}
