package grails3.example

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import groovy.json.JsonSlurper
import org.grails.web.json.JSONObject

@Transactional
class EventService {

    def serviceMethod() {

    }

    ArrayList<Event> search(String q){

        ArrayList<Event> event_results = new ArrayList<Event>()
        def response_eventbrite

        if (q){
            // perform a GET call to Eventbrite's REST API, returns JSON response
            response_eventbrite = new RestBuilder().get("https://www.eventbriteapi.com/v3/events/{query}"){
                header "Authorization", "Bearer 2S34UCIHKW5MXVP4S5M7" // authenticate with header
                urlVariables query:q
            }

        } else {
            throw new IllegalArgumentException("The search cannot be empty!")
        }

        // cast response to JSON object
        JSONObject obj = (JSONObject) response_eventbrite.json

        // create the JSON object to get Eventbrite's categories
        JSONObject eventbrite_categories_dictionary = import_eventbrite_category_dictionary()

        // read through JSON object, create new Events using the JSON properties
        // get relevant properties
        String event_name = obj.name.text
        String event_description_full = obj.description.text

        // clean and/or truncate the description (truncate if > 140 characters long)
        String event_description_trimmed = null
        if (event_description_full != "" && event_description_full != null){
            if (event_description_full.length() > 140){
                event_description_trimmed = event_description_full.replaceAll("\n", " ").substring(0, 140)
            } else {
                event_description_trimmed = event_description_full.replaceAll("\n", " ")
            }
        }

        /** get the category name from the eventbrite_id **/
        String eventbrite_category_id = obj.category_id
        String eventbrite_category_name
        if (eventbrite_category_id != null){
            eventbrite_category_name = eventbrite_categories_dictionary["categories"][eventbrite_category_id]
        } else {
            eventbrite_category_name = "Unspecified"
        }

        String eventbrite_id = obj.id

        // create new Event object
        Event new_event = new Event(name: event_name, description: event_description_trimmed, start_date: null,
                eventbrite_url: obj.url, eventbrite_id: eventbrite_id, category_name: eventbrite_category_name)


        // add to the collection
        event_results.add(new_event)




        return event_results


    }

    /** Retrieves Eventbrite's categories from their /categories/ endpoint using a GET request
     * No longer needed
     def get_eventbrite_categories(){
     // perform a GET call to Eventbrite's REST API, returns JSON response
     def categories_response = new RestBuilder().get("https://www.eventbriteapi.com/v3/categories/"){
     header "Authorization", "Bearer 2S34UCIHKW5MXVP4S5M7" // authenticate with header
     }
     return categories_response.json
     } **/

    JSONObject import_eventbrite_category_dictionary(){
        def inputFile = new File("eventbrite_categories.txt")
        JSONObject inputJSON = (JSONObject) new JsonSlurper().parseText(inputFile.text)
        return inputJSON
    }

}
