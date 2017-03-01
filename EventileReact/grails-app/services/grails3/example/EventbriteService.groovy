package grails3.example

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import net.minidev.json.JSONObject
import org.grails.web.json.JSONElement

@Transactional
class EventbriteService {

    def serviceMethod() {

    }

    Collection<Event> search(String q){

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



    }

}
