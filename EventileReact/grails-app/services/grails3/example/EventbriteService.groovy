package grails3.example

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import org.grails.web.json.JSONObject

import java.util.LinkedList

@Transactional
class EventbriteService {

    def serviceMethod() {

    }

    ArrayList<Event> search(String q){

        ArrayList<Event> event_results = new ArrayList<Event>()
        def response_eventbrite

        if (q){

            // perform a GET call to Eventbrite's REST API, returns JSON response
            response_eventbrite = new RestBuilder().get("https://www.eventbriteapi.com/v3/events/search/?q={query}"){
                header "Authorization", "Bearer 2S34UCIHKW5MXVP4S5M7" // authenticate with header
                urlVariables query:q
            }

        } else {
            throw new IllegalArgumentException("The search cannot be empty!")
        }

        /** parse the JSON response, extract Events **/

        // cast response to JSON object
        JSONObject obj = (JSONObject) response_eventbrite.json

        // read through JSON object, create new Events using the JSON properties
        int i=0
        while (obj["events"][i] != null){

            // get relevant properties
            String event_name = obj["events"][i].name.text
            String event_description_full = obj["events"][i].description.text

            // clean and/or truncate the description (truncate if > 140 characters long)
            String event_description_trimmed = null
            if (event_description_full != "" && event_description_full != null){
                if (event_description_full.length() > 140){
                    event_description_trimmed = event_description_full.replaceAll("\n", " ").substring(0, 140)
                } else {
                    event_description_trimmed = event_description_full.replaceAll("\n", " ")
                }
            }

            // create new Event object
            Event new_event = new Event(name: event_name, description: event_description_trimmed, start_date: null, eventbrite_url: null, eventbrite_id: 0)

            // add to the collection
            event_results.add(new_event)

            i++
        }

        return event_results

    }

}
