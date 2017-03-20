package grails3.example

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Secured(['ROLE_USER'])
class SearchController extends RestfulController {

    static responseFormats = ['json']

    def searchService
    def EventbriteService
    def springSecurityService

    SearchController() {
        super(SearchController)
    }

    def search(String q, Boolean date) {
        // Gets the current user name - can be used to control permissions
        def info = springSecurityService.currentUser.username
        log.debug("Searching by query = ${q}...")

        // perform a GET requestion to Eventbrite's API using EventbriteService class
        def response_eventbrite = EventbriteService.search(q, date)

        respond response_eventbrite
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        log.debug("Handling IllegalArgumentException ${ex}... Returning NO_CONTENT.")
        respond Collections.emptyList()
    }
}
