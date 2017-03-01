package grails3.example

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import java.util.LinkedList

@Transactional
class EventbriteService {

    def serviceMethod() {

    }

    LinkedList<Event> search(String q){

        LinkedList<Event> event_results = new LinkedList<Event>()

        if (q){
            // perform a GET call to Eventbrite's REST API, returns JSON response
            def response_eventbrite = new RestBuilder().get("https://www.eventbriteapi.com/v3/events/search/?q={query}"){
                header "Authorization", "Bearer 2S34UCIHKW5MXVP4S5M7" // authenticate with header
                urlVariables query:q
            }

        } else {
            throw new IllegalArgumentException("The search cannot be empty!")
        }

        // parse the JSON response, extract Events
        // example:
        event_results.add(new Event(name: "testing title", description: "hello hello", start_date: "thursday", eventbrite_id: 350350, eventbrite_url: "http://www.google.ca"))
        event_results.add(new Event(name: "testing 222", description: "hello 333", start_date: "2DAY", eventbrite_id: 3503550, eventbrite_url: "http://www.test.ca"))

        return event_results


    }

}
