package grails3.example

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.http.HttpStatus
import grails.plugins.rest.client.RestBuilder

@Secured(['ROLE_USER'])
class SearchController {

    static responseFormats = ['json']

    def searchService
    def springSecurityService

    def search(String q) {
        // Gets the current user name - can be used to control permissions
        def info = springSecurityService.currentUser.username
        log.debug("Searching by query = ${q}...")

        // perform a GET requestion to Eventbrite's API
        def response = new RestBuilder().get("https://www.eventbriteapi.com/v3/events/search/?q={query}"){
            header "Authorization", "Bearer 2S34UCIHKW5MXVP4S5M7" // authenticate
            urlVariables query:q
        }
        System.out.println(response.json.toString())




        def result = searchService.search(q.trim())
        respond result
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        log.debug("Handling IllegalArgumentException ${ex}... Returning NO_CONTENT.")
        respond Collections.emptyList()
    }
}
