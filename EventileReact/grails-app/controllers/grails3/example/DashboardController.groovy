package grails3.example

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class DashboardController {

    static responseFormats = ['json']

    def index() { }

    def DashboardService

    def search(String prefs){

        // perform a GET requestion to Eventbrite's API using WelcomeService class
        def response_eventbrite = DashboardService.search(prefs)

        respond response_eventbrite
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        log.debug("Handling IllegalArgumentException ${ex}... Returning NO_CONTENT.")
        respond Collections.emptyList()
    }


}
