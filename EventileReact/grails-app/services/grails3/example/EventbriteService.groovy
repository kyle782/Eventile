package grails3.example

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.Query
import org.grails.web.json.JSONObject
import groovy.json.JsonSlurper

@Transactional
class EventbriteService {

    def serviceMethod() {

    }

    ArrayList<Event> search(String q, Boolean date){

        ArrayList<Event> event_results = new ArrayList<Event>()
        def response_eventbrite

        if (q){

            System.out.print(date)
            if (date)
            {
                response_eventbrite = new RestBuilder().get("https://www.eventbriteapi.com/v3/events/search/?q={query}&sort_by=date"){
                    header "Authorization", "Bearer 2S34UCIHKW5MXVP4S5M7" // authenticate with header
                    urlVariables query:q
                }
            }
            else {
                // perform a GET call to Eventbrite's REST API, returns JSON response
                response_eventbrite = new RestBuilder().get("https://www.eventbriteapi.com/v3/events/search/?q={query}") {
                    header "Authorization", "Bearer 2S34UCIHKW5MXVP4S5M7" // authenticate with header
                    urlVariables query: q
                }
            }

        } else {
            throw new IllegalArgumentException("The search cannot be empty!")
        }

        /** parse the JSON response, extract Events **/

        // cast response to JSON object
        JSONObject obj = (JSONObject) response_eventbrite.json

        // create the JSON object to get Eventbrite's categories
        JSONObject eventbrite_categories_dictionary = import_eventbrite_category_dictionary()

        // read through JSON object, create new Events using the JSON properties
        int i=0
        while (obj["events"][i] != null){

            // get relevant properties
            String event_name = obj["events"][i].name.text
            String event_description_full = obj["events"][i].description.text
            String event_date = obj["events"][i].start.local

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
            String eventbrite_category_id = obj["events"][i].category_id
            String eventbrite_category_name
            if (eventbrite_category_id != null){
                eventbrite_category_name = eventbrite_categories_dictionary["categories"][eventbrite_category_id]
            } else {
                eventbrite_category_name = "Unspecified"
            }

            String eventbrite_id = obj["events"][i].id

            String eventbrite_img_url
            if (obj["events"][i].logo != null){
                eventbrite_img_url = obj["events"][i].logo.url
            } else {
                eventbrite_img_url = ""
            }

            /** get the venue id **/
            String eventbrite_venue_id
            if (obj["events"][i].venue_id != null){
                eventbrite_venue_id = obj["events"][i].venue_id
            } else {
                eventbrite_venue_id = ""
            }

            // create new Event object, save to database after
            Event new_event = new Event(name: event_name, description: event_description_trimmed, start_date: event_date ,
                    eventbrite_url: obj["events"][i].url, eventbrite_id: eventbrite_id,
                    category_name: eventbrite_category_name, num_ratings: 0, total_rating: 0, average_rating: 0,
                    img_url: eventbrite_img_url, eventbrite_venue_id: eventbrite_venue_id)

            // save to database, print errors for debugging if unable to save
            if(!new_event.save(flush:true) ) {
                System.out.println(new_event.errors)
            }

            // add to the collection
            event_results.add(new_event)

            i++
        }

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
